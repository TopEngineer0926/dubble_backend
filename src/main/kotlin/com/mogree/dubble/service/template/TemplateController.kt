package com.mogree.dubble.service.template

import com.mogree.dubble.category.payload.ProductListResponse
import com.mogree.dubble.service.template.payload.TemplateListResponse
import com.mogree.server.gen.model.ProductModel
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("template")
class TemplateController (
        private val templateService: TemplateService
) {

    @GetMapping("/template_by_status")
    fun getTemplateByStatus(
            @RequestParam(value = "offset") offset: Int,
            @RequestParam(value = "limit") limit: Int
    ): ResponseEntity<TemplateListResponse> {
        val list: List<ProductModel> = templateService.getTemplateByStatus(offset, limit)
        val response = TemplateListResponse(offset, list, limit, templateService.getSizeByStatus("DRAFT"))
        return ResponseEntity.ok<TemplateListResponse>(response)
    }

    @GetMapping("/template_by_filter")
    fun getTemplateByFilter(
            @RequestParam(value = "offset") offset: Int,
            @RequestParam(value = "limit") limit: Int,
            @RequestParam(value = "filter") filter: String
    ): ResponseEntity<TemplateListResponse> {
        val list: List<ProductModel> = templateService.getTemplateByFilter(offset, limit, filter)
        val response = TemplateListResponse(offset, list, limit, templateService.getSizeByFilter("DRAFT", filter))
        return ResponseEntity.ok<TemplateListResponse>(response)
    }
}