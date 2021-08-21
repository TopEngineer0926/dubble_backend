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
import java.lang.Object;
import com.mogree.spring.exception.APIItemNotFoundException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.mogree.spring.response.IResponse;

/**
 * A delegate to be called by the {@link CustomerApiController}}.
 * Should be implemented as a controller but without the {@link org.springframework.stereotype.Controller} annotation.
 * Instead, use spring to autowire this class into the {@link CustomerApiController}.
 */
@javax.annotation.Generated(value = "class com.mogree.MogreeCodeGen", date = "2021-05-11T13:27:53.093+02:00")

public interface CustomerApiDelegate {

    /**
     * @see CustomerApi#createCustomer
     */
    public Object createCustomer(ParamCustomerModelBody paramCustomerModelBody, ParamCreateCustomer paramCreateCustomer);

    /**
     * @see CustomerApi#deleteCustomer
     */
    public Object deleteCustomer(ParamDeleteCustomer paramDeleteCustomer);

    /**
     * @see CustomerApi#getCustomerDetail
     */
    public Object getCustomerDetail(ParamGetCustomerDetail paramGetCustomerDetail);

    /**
     * @see CustomerApi#getCustomerList
     */
    public Object getCustomerList(ParamPaging paramPaging, ParamGetCustomerList paramGetCustomerList);

    /**
     * @see CustomerApi#updateCustomer
     */
    public Object updateCustomer(ParamCustomerModelBody paramCustomerModelBody, ParamUpdateCustomer paramUpdateCustomer);

    @Component
    class CustomerApiDelegateDefaultImpl implements CustomerApiDelegate {
        @Override
        public Object createCustomer(ParamCustomerModelBody paramCustomerModelBody, ParamCreateCustomer paramCreateCustomer){
            if (true){
                throw new APIItemNotFoundException("This API is not implemented yet. Plz be patient or give a present to the backend developer.");
            }
            return null;
        }
        @Override
        public Object deleteCustomer(ParamDeleteCustomer paramDeleteCustomer){
            if (true){
                throw new APIItemNotFoundException("This API is not implemented yet. Plz be patient or give a present to the backend developer.");
            }
            return null;
        }
        @Override
        public Object getCustomerDetail(ParamGetCustomerDetail paramGetCustomerDetail){
            if (true){
                throw new APIItemNotFoundException("This API is not implemented yet. Plz be patient or give a present to the backend developer.");
            }
            return null;
        }
        @Override
        public Object getCustomerList(ParamPaging paramPaging, ParamGetCustomerList paramGetCustomerList){
            if (true){
                throw new APIItemNotFoundException("This API is not implemented yet. Plz be patient or give a present to the backend developer.");
            }
            return null;
        }
        @Override
        public Object updateCustomer(ParamCustomerModelBody paramCustomerModelBody, ParamUpdateCustomer paramUpdateCustomer){
            if (true){
                throw new APIItemNotFoundException("This API is not implemented yet. Plz be patient or give a present to the backend developer.");
            }
            return null;
        }
    }
}
