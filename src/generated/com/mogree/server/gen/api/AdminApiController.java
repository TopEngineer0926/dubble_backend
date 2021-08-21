package com.mogree.server.gen.api;

import com.mogree.server.gen.model.UserModel;
import com.mogree.spring.response.ListResponse;
import com.mogree.spring.Executer;
import com.mogree.server.gen.param.ParamGetUserList;
import com.mogree.server.gen.param.ParamPaging;

import java.util.List;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;

import com.mogree.spring.validator.IValidator;

@javax.annotation.Generated(value = "class com.mogree.MogreeCodeGen", date = "2021-05-11T13:27:53.093+02:00")

@RestController
public class AdminApiController implements AdminApi {

    @Autowired(required=false)
    private List<IValidator<? extends Object>> validators;

    private AdminApiDelegate delegate;

    @Autowired
    public AdminApiController(AdminApiDelegate delegate) {
        this.delegate = delegate;
    }


    public ResponseEntity<ListResponse<UserModel>> getUserList(@ApiParam(value = "Paging offset", required = true, defaultValue = "0", example = "0")  @RequestParam(value = "offset", required = true, defaultValue="0") Integer offset, @ApiParam(value = "Paging limit", required = true, defaultValue = "-1", example = "-1")  @RequestParam(value = "limit", required = true, defaultValue="-1") Integer limit, @ApiParam(value = "Sort the list", allowableValues = "createdAt")  @RequestParam(value = "sort_column", required = false) String sortColumn, @ApiParam(value = "Sort Order", allowableValues = "asc, desc")  @RequestParam(value = "sort_order", required = false) String sortOrder) {

        ParamPaging paramPaging = new ParamPaging(offset, limit, sortColumn, sortOrder);
        ParamGetUserList paramGetUserList = new ParamGetUserList();

        return new Executer(validators).validate(paramPaging).usecase(() -> delegate.getUserList(paramPaging, paramGetUserList)).run();

    }


}

