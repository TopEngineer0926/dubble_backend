package com.mogree.server.gen.api;

import com.mogree.server.gen.model.UserModel;
import com.mogree.spring.response.ListResponse;
import com.mogree.spring.Executer;
import com.mogree.server.gen.param.ParamGetUserList;
import com.mogree.server.gen.param.ParamPaging;

import java.util.List;
import java.lang.Object;
import com.mogree.spring.exception.APIItemNotFoundException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.mogree.spring.response.IResponse;

/**
 * A delegate to be called by the {@link AdminApiController}}.
 * Should be implemented as a controller but without the {@link org.springframework.stereotype.Controller} annotation.
 * Instead, use spring to autowire this class into the {@link AdminApiController}.
 */
@javax.annotation.Generated(value = "class com.mogree.MogreeCodeGen", date = "2021-05-11T13:27:53.093+02:00")

public interface AdminApiDelegate {

    /**
     * @see AdminApi#getUserList
     */
    public Object getUserList(ParamPaging paramPaging, ParamGetUserList paramGetUserList);

    @Component
    class AdminApiDelegateDefaultImpl implements AdminApiDelegate {
        @Override
        public Object getUserList(ParamPaging paramPaging, ParamGetUserList paramGetUserList){
            if (true){
                throw new APIItemNotFoundException("This API is not implemented yet. Plz be patient or give a present to the backend developer.");
            }
            return null;
        }
    }
}
