package com.mogree.dubble.excel

import com.mogree.dubble.config.security.getCurrentUserId
import com.mogree.dubble.mapper.toModels
import com.mogree.dubble.service.customer.CustomerService
import com.mogree.dubble.storage.repository.CustomerRepository
import com.mogree.dubble.storage.repository.UserCategoryRepository
import com.mogree.server.gen.model.CustomerModel
import org.apache.poi.ss.usermodel.DataFormatter
import org.apache.poi.xssf.usermodel.XSSFCell
import org.apache.poi.xssf.usermodel.XSSFRow
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.json.JSONObject
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayOutputStream
import java.util.*


@RestController
@RequestMapping("customer/excel")
class ExcelService(
    private val customerService: CustomerService,
    private val userCategoryRepository: UserCategoryRepository,
    private val customerRepository: CustomerRepository
) {

    @PostMapping("/download_example")
    fun downloadExample(): ResponseEntity<ByteArray> {
        val input: Resource = ClassPathResource("templates/excel/Beispiel.xlsx")
        val tempData = readExcelFile(input)
        return createResponseEntity(tempData, "Beispiel.xlsx")
    }

    @PostMapping("/upload_xlsx")
    fun uploadXLSX(@RequestParam("file") file: MultipartFile): ResponseEntity<JSONObject> {
        val excelFile = file.inputStream
        val workbook = XSSFWorkbook(excelFile)

        val sheet = workbook.getSheetAt(0)

        // Create a DataFormatter to format and get each cell's value as String
        val dataFormatter = DataFormatter()

        // Assuming "column headers" are in the first row
        val header_row: XSSFRow = sheet.getRow(0)

        val headers: MutableList<String> = ArrayList()
        // Assuming 9 columns
        for (i in 0..8) {
            val header_cell: XSSFCell = header_row.getCell(i)
            val header = header_cell.getStringCellValue()

            if (header.toLowerCase().contains("kundennummer")) // Kundennummer
                headers.add("customer_number")
            if (header.toLowerCase().contains("titel_vorangestellt")) // Titel_Vorangestellt
                headers.add("academic_degree_preceding")
            if (header.toLowerCase().contains("vorname")) // Vorname
                headers.add("first_name")
            if (header.toLowerCase().contains("nachname")) // Nachname
                headers.add("last_name")
            if (header.toLowerCase().contains("titel_nachgestellt")) // Titel_Nachgestellt
                headers.add("academic_degree_subsequent")
            if (header.toLowerCase().contains("email")) // eMail
                headers.add("email")
            if (header.toLowerCase().contains("mobilnummer")) // Mobilnummer(+43…)
                headers.add("phone_number")
            if (header.toLowerCase().contains("unternehmen")) // Unternehmen
                headers.add("company")
            if (header.toLowerCase().contains("kunden_interessen")) // Kunden Interessen
                headers.add("category")
        }

        val all_datas: MutableList<JSONObject> = ArrayList()
        for (row in 1..sheet.lastRowNum) {
            val data_row: XSSFRow = sheet.getRow(row)
            val data = JSONObject()
            for (cell in 0..8) {
                val data_cell = data_row.getCell(cell)
                data.put(headers[cell], dataFormatter.formatCellValue(data_cell))
                print(dataFormatter.formatCellValue(data_cell))
                print("  ")
            }
            print("\n")
            all_datas.add(data)
        }
        // Insert datas into Customer table
        createCustomer(all_datas)

        excelFile.close()
        val out = ByteArrayOutputStream()
        workbook.write(out)

        out.close()
        workbook.close()

        val obj =  JSONObject()
        obj.put("status", 200)
        return         ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(obj)
    }

    // Read the excel data from resource file.
    fun readExcelFile(input: Resource): ByteArray {
        val excelFile = input.inputStream
        val workbook = XSSFWorkbook(excelFile)

        excelFile.close()

        val out = ByteArrayOutputStream()
        workbook.write(out)

        out.close()
        workbook.close()

        return out.toByteArray()
    }

    fun createCustomer(
            datas: MutableList<JSONObject>
    ) {

        for (i in 0 until datas.size) {
            if (datas.get(i).getString("first_name").isEmpty() || datas.get(i).getString("last_name").isEmpty())
                continue
            val model = CustomerModel()
            model.customerNumber(datas.get(i).getString("customer_number"))
            model.firstname(datas.get(i).getString("first_name"))
            model.lastname(datas.get(i).getString("last_name"))
            model.academicDegreePreceding(datas.get(i).getString("academic_degree_preceding"))
            model.academicDegreeSubsequent(datas.get(i).getString("academic_degree_subsequent"))
            model.email(datas.get(i).getString("email"))
            model.phoneNumber(validateNumber(datas.get(i).getString("phone_number")))
            model.companyName(datas.get(i).getString("company"))
            var category = datas.get(i).getString("category").split("|")
            var structuredCategory = "|"
            for (j in 0 until category.size) {
                if (category[j].isEmpty())
                    continue
                structuredCategory += category[j] + "|"
            }
            model.category(structuredCategory)
            customerService.createCustomer(model)

            if (datas.get(i).getString("category") != "") {
                insertNewCategory(datas.get(i).getString("category"))
            }
        }
    }

    fun validateNumber(str: String) : String {

        // Remove all other Chars, leave only Numbers 0,1,2,3,4,5,6,7,8,9, and a plus „+“ sign
        var temp = str.replace(Regex("[^\\d+]"), "")

        temp = temp.replace(Regex("^0043"), "+43")

        temp = temp.replace(Regex("^043"),"+43")

        temp = temp.replace(Regex("^0"), "+43")

        return temp
    }

    fun insertNewCategory(
            category: String
    ) {
        var userCategoryList = userCategoryRepository.getCategory(getCurrentUserId())
        val newCategoryList = category.split("|")

        if (userCategoryList != null) {
            for (i in 0 until newCategoryList.size) {
                if (newCategoryList.isNotEmpty() && !userCategoryList!!.contains(newCategoryList[i])) {
                    if (!userCategoryList.isNotEmpty())
                        userCategoryList = "|"
                    val temp = userCategoryList + newCategoryList[i] + "|"
                    userCategoryRepository.updateCategory(temp, getCurrentUserId())
                }
            }
        } else {
            for (i in 0 until newCategoryList.size) {
                if (newCategoryList.isNotEmpty()) {
                    val temp = "|" + newCategoryList[i] + "|"
                    userCategoryRepository.updateCategory(temp, getCurrentUserId())
                }
            }
        }
    }

    private fun createResponseEntity(
        report: ByteArray,
        fileName: String
    ): ResponseEntity<ByteArray> =
        ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"$fileName\"")
            .body(report)

    @PostMapping("/download_customers")
    fun downloadCustomers(): ResponseEntity<ByteArray> {
        val list: List<CustomerModel> = customerRepository.getCustomers(getCurrentUserId()).toModels()
        val tempData = writeToExcelFile(list)
        return createResponseEntity(tempData, "Kunden_Interessen.xlsx")
    }

    // Writes the values.
    fun writeToExcelFile(data: List<CustomerModel>): ByteArray {
        //Instantiate Excel workbook:
        val xlWb = XSSFWorkbook()
        //Instantiate Excel worksheet:
        val xlWs = xlWb.createSheet("Kunden_Interessenten")

        //Row index specifies the row in the worksheet (starting at 0):
        var rowNumber = 0

        //Write text value to cell located at ROW_NUMBER / COLUMN_NUMBER:
        // Header of excel
        var row = xlWs.createRow(rowNumber)
        row.createCell(0).setCellValue("kundennummer")
        row.createCell(1).setCellValue("Titel_Vorangestellt")
        row.createCell(2).setCellValue("Vorname")
        row.createCell(3).setCellValue("Nachname")
        row.createCell(4).setCellValue("Titel_Nachgestellt")
        row.createCell(5).setCellValue("eMail")
        row.createCell(6).setCellValue("Mobilnummer(+43…)")
        row.createCell(7).setCellValue("Unternehmen")
        row.createCell(8).setCellValue("Kunden_Interessen")

        for (i in 0 until data.size) {
            rowNumber++
            row = xlWs.createRow(rowNumber)

            row.createCell(0).setCellValue(data[i].customerNumber)
            row.createCell(1).setCellValue(data[i].academicDegreePreceding)
            row.createCell(2).setCellValue(data[i].firstname)
            row.createCell(3).setCellValue(data[i].lastname)
            row.createCell(4).setCellValue(data[i].academicDegreeSubsequent)
            row.createCell(5).setCellValue(data[i].email)
            row.createCell(6).setCellValue(data[i].phoneNumber)
            row.createCell(7).setCellValue(data[i].companyName)
            row.createCell(8).setCellValue(data[i].category)
        }

        //Write file:
        val out = ByteArrayOutputStream()
        xlWb.write(out)
        out.close()
        xlWb.close()

        return out.toByteArray()
    }

}
