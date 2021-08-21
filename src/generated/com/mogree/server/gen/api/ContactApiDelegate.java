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
import java.lang.Object;
import com.mogree.spring.exception.APIItemNotFoundException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.mogree.spring.response.IResponse;

/**
 * A delegate to be called by the {@link ContactApiController}}.
 * Should be implemented as a controller but without the {@link org.springframework.stereotype.Controller} annotation.
 * Instead, use spring to autowire this class into the {@link ContactApiController}.
 */
@javax.annotation.Generated(value = "class com.mogree.MogreeCodeGen", date = "2021-05-11T13:27:53.093+02:00")

public interface ContactApiDelegate {

    /**
     * @see ContactApi#createContact
     */
    public Object createContact(ParamContactModelBody paramContactModelBody, ParamCreateContact paramCreateContact);

    /**
     * @see ContactApi#deleteContact
     */
    public Object deleteContact(ParamDeleteContact paramDeleteContact);

    /**
     * @see ContactApi#getContactDetail
     */
    public Object getContactDetail(ParamGetContactDetail paramGetContactDetail);

    /**
     * @see ContactApi#getContactList
     */
    public Object getContactList(ParamPaging paramPaging, ParamGetContactList paramGetContactList);

    /**
     * @see ContactApi#updateContact
     */
    public Object updateContact(ParamContactModelBody paramContactModelBody, ParamUpdateContact paramUpdateContact);

    @Component
    class ContactApiDelegateDefaultImpl implements ContactApiDelegate {
        @Override
        public Object createContact(ParamContactModelBody paramContactModelBody, ParamCreateContact paramCreateContact){
            if (true){
                throw new APIItemNotFoundException("This API is not implemented yet. Plz be patient or give a present to the backend developer.");
            }
            return null;
        }
        @Override
        public Object deleteContact(ParamDeleteContact paramDeleteContact){
            if (true){
                throw new APIItemNotFoundException("This API is not implemented yet. Plz be patient or give a present to the backend developer.");
            }
            return null;
        }
        @Override
        public Object getContactDetail(ParamGetContactDetail paramGetContactDetail){
            if (true){
                throw new APIItemNotFoundException("This API is not implemented yet. Plz be patient or give a present to the backend developer.");
            }
            return null;
        }
        @Override
        public Object getContactList(ParamPaging paramPaging, ParamGetContactList paramGetContactList){
            if (true){
                throw new APIItemNotFoundException("This API is not implemented yet. Plz be patient or give a present to the backend developer.");
            }
            return null;
        }
        @Override
        public Object updateContact(ParamContactModelBody paramContactModelBody, ParamUpdateContact paramUpdateContact){
            if (true){
                throw new APIItemNotFoundException("This API is not implemented yet. Plz be patient or give a present to the backend developer.");
            }
            return null;
        }
    }
}
