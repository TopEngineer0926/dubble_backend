/**
 * NOTE: This class is auto generated by the swagger code generator program ().
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.mogree.server.gen.param;

import com.mogree.spring.response.DetailResponse;
import com.mogree.spring.Executer;
import com.mogree.server.gen.model.ContactModel;
import com.mogree.spring.response.StatusResponse;
import com.mogree.spring.response.ListResponse;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

public class ParamDeleteContact {

    private Long contactId;

    public ParamDeleteContact(final Long contactId) {
        this.contactId = contactId;
    }

    public Long getContactId() {
        return this.contactId;
    }

}