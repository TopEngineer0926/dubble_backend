package com.mogree.server.gen.api;

import com.mogree.server.gen.model.CustomerModel;
import com.mogree.spring.response.DetailResponse;
import com.mogree.spring.Executer;
import com.mogree.spring.response.StatusResponse;
import com.mogree.spring.response.ListResponse;
import com.mogree.server.gen.param.ParamCreateCustomer;
import com.mogree.server.gen.param.ParamCustomerModelBody;
import com.mogree.server.gen.param.ParamDeleteCustomer;
import com.mogree.server.gen.param.ParamGetCustomerDetail;
import com.mogree.server.gen.param.ParamGetCustomerList;
import com.mogree.server.gen.param.ParamPaging;
import com.mogree.server.gen.param.ParamUpdateCustomer;

import java.util.List;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;

import com.mogree.spring.validator.IValidator;

@javax.annotation.Generated(value = "class com.mogree.MogreeCodeGen", date = "2021-05-11T13:27:53.093+02:00")

@RestController
public class CustomerApiController implements CustomerApi {

    @Autowired(required=false)
    private List<IValidator<? extends Object>> validators;

    private CustomerApiDelegate delegate;

    @Autowired
    public CustomerApiController(CustomerApiDelegate delegate) {
        this.delegate = delegate;
    }


    public ResponseEntity<DetailResponse<CustomerModel>> createCustomer(@ApiParam(value = ""  ) @RequestBody CustomerModel customerModelBody) {

        ParamCustomerModelBody paramCustomerModelBody = new ParamCustomerModelBody(customerModelBody);
        ParamCreateCustomer paramCreateCustomer = new ParamCreateCustomer();

        return new Executer(validators).validate(paramCustomerModelBody).usecase(() -> delegate.createCustomer(paramCustomerModelBody, paramCreateCustomer)).run();

    }


    public ResponseEntity<StatusResponse> deleteCustomer(@ApiParam(value = "",required=true, example = "0") @PathVariable("customerId") Long customerId) {

        ParamDeleteCustomer paramDeleteCustomer = new ParamDeleteCustomer(customerId);

        return new Executer(validators).validate().usecase(() -> delegate.deleteCustomer(paramDeleteCustomer)).run();

    }


    public ResponseEntity<DetailResponse<CustomerModel>> getCustomerDetail(@ApiParam(value = "",required=true, example = "0") @PathVariable("customerId") Long customerId) {

        ParamGetCustomerDetail paramGetCustomerDetail = new ParamGetCustomerDetail(customerId);

        return new Executer(validators).validate().usecase(() -> delegate.getCustomerDetail(paramGetCustomerDetail)).run();

    }


    public ResponseEntity<ListResponse<CustomerModel>> getCustomerList(@ApiParam(value = "Paging offset", required = true, defaultValue = "0", example = "0")  @RequestParam(value = "offset", required = true, defaultValue="0") Integer offset, @ApiParam(value = "Paging limit", required = true, defaultValue = "-1", example = "-1")  @RequestParam(value = "limit", required = true, defaultValue="-1") Integer limit, @ApiParam(value = "Sort the list", allowableValues = "createdAt")  @RequestParam(value = "sort_column", required = false) String sortColumn, @ApiParam(value = "Sort Order", allowableValues = "asc, desc")  @RequestParam(value = "sort_order", required = false) String sortOrder) {

        ParamPaging paramPaging = new ParamPaging(offset, limit, sortColumn, sortOrder);
        ParamGetCustomerList paramGetCustomerList = new ParamGetCustomerList();

        return new Executer(validators).validate(paramPaging).usecase(() -> delegate.getCustomerList(paramPaging, paramGetCustomerList)).run();

    }


    public ResponseEntity<DetailResponse<CustomerModel>> updateCustomer(@ApiParam(value = ""  ) @RequestBody CustomerModel customerModelBody) {

        ParamCustomerModelBody paramCustomerModelBody = new ParamCustomerModelBody(customerModelBody);
        ParamUpdateCustomer paramUpdateCustomer = new ParamUpdateCustomer();

        return new Executer(validators).validate(paramCustomerModelBody).usecase(() -> delegate.updateCustomer(paramCustomerModelBody, paramUpdateCustomer)).run();

    }


}

