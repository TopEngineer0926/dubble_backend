package com.mogree.server.gen.api;

import com.mogree.spring.response.DetailResponse;
import com.mogree.spring.Executer;
import com.mogree.server.gen.model.ContactModel;
import com.mogree.spring.response.StatusResponse;
import com.mogree.spring.response.ListResponse;
import com.mogree.server.gen.param.ParamCreateContact;
import com.mogree.server.gen.param.ParamContactModelBody;
import com.mogree.server.gen.param.ParamDeleteContact;
import com.mogree.server.gen.param.ParamGetContactDetail;
import com.mogree.server.gen.param.ParamPaging;
import com.mogree.server.gen.param.ParamGetContactList;
import com.mogree.server.gen.param.ParamUpdateContact;

import java.util.List;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;

import com.mogree.spring.validator.IValidator;

@javax.annotation.Generated(value = "class com.mogree.MogreeCodeGen", date = "2021-05-11T13:27:53.093+02:00")

@RestController
public class ContactApiController implements ContactApi {

    @Autowired(required=false)
    private List<IValidator<? extends Object>> validators;

    private ContactApiDelegate delegate;

    @Autowired
    public ContactApiController(ContactApiDelegate delegate) {
        this.delegate = delegate;
    }


    public ResponseEntity<DetailResponse<ContactModel>> createContact(@ApiParam(value = ""  ) @RequestBody ContactModel contactModelBody) {

        ParamContactModelBody paramContactModelBody = new ParamContactModelBody(contactModelBody);
        ParamCreateContact paramCreateContact = new ParamCreateContact();

        return new Executer(validators).validate(paramContactModelBody).usecase(() -> delegate.createContact(paramContactModelBody, paramCreateContact)).run();

    }


    public ResponseEntity<StatusResponse> deleteContact(@ApiParam(value = "",required=true, example = "0") @PathVariable("contactId") Long contactId) {

        ParamDeleteContact paramDeleteContact = new ParamDeleteContact(contactId);

        return new Executer(validators).validate().usecase(() -> delegate.deleteContact(paramDeleteContact)).run();

    }


    public ResponseEntity<DetailResponse<ContactModel>> getContactDetail(@ApiParam(value = "",required=true, example = "0") @PathVariable("contactId") Long contactId) {

        ParamGetContactDetail paramGetContactDetail = new ParamGetContactDetail(contactId);

        return new Executer(validators).validate().usecase(() -> delegate.getContactDetail(paramGetContactDetail)).run();

    }


    public ResponseEntity<ListResponse<ContactModel>> getContactList(@ApiParam(value = "Paging offset", required = true, defaultValue = "0", example = "0")  @RequestParam(value = "offset", required = true, defaultValue="0") Integer offset, @ApiParam(value = "Paging limit", required = true, defaultValue = "-1", example = "-1")  @RequestParam(value = "limit", required = true, defaultValue="-1") Integer limit, @ApiParam(value = "Sort the list", allowableValues = "createdAt")  @RequestParam(value = "sort_column", required = false) String sortColumn, @ApiParam(value = "Sort Order", allowableValues = "asc, desc")  @RequestParam(value = "sort_order", required = false) String sortOrder) {

        ParamPaging paramPaging = new ParamPaging(offset, limit, sortColumn, sortOrder);
        ParamGetContactList paramGetContactList = new ParamGetContactList();

        return new Executer(validators).validate(paramPaging).usecase(() -> delegate.getContactList(paramPaging, paramGetContactList)).run();

    }


    public ResponseEntity<DetailResponse<ContactModel>> updateContact(@ApiParam(value = ""  ) @RequestBody ContactModel contactModelBody) {

        ParamContactModelBody paramContactModelBody = new ParamContactModelBody(contactModelBody);
        ParamUpdateContact paramUpdateContact = new ParamUpdateContact();

        return new Executer(validators).validate(paramContactModelBody).usecase(() -> delegate.updateContact(paramContactModelBody, paramUpdateContact)).run();

    }


}

