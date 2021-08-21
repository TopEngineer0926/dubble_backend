package com.mogree.server.gen.api;

import com.mogree.spring.response.StatusResponse;
import com.mogree.spring.Executer;
import com.mogree.server.gen.model.UserModel;
import com.mogree.spring.response.DetailResponse;
import com.mogree.server.gen.model.UserAuthModel;
import com.mogree.server.gen.model.PasswordResetModel;
import com.mogree.server.gen.model.PasswordUpdateModel;
import com.mogree.server.gen.model.RegisterModel;
import com.mogree.server.gen.param.ParamActivate;
import com.mogree.server.gen.param.ParamActivateBody;
import com.mogree.server.gen.param.ParamGetUserData;
import com.mogree.server.gen.param.ParamLogin;
import com.mogree.server.gen.param.ParamLogout;
import com.mogree.server.gen.param.ParamPasswordResetBody;
import com.mogree.server.gen.param.ParamPasswordReset;
import com.mogree.server.gen.param.ParamPasswordResetUpdate;
import com.mogree.server.gen.param.ParamPasswordResetUpdateBody;
import com.mogree.server.gen.param.ParamRegisterBody;
import com.mogree.server.gen.param.ParamRegister;
import com.mogree.server.gen.param.ParamUpdate;
import com.mogree.server.gen.param.ParamUpdateBody;
import com.mogree.server.gen.param.ParamUpdatePassword;
import com.mogree.server.gen.param.ParamPasswordUpdate;

import java.util.List;
import java.lang.Object;
import com.mogree.spring.exception.APIItemNotFoundException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.mogree.spring.response.IResponse;

/**
 * A delegate to be called by the {@link AccountApiController}}.
 * Should be implemented as a controller but without the {@link org.springframework.stereotype.Controller} annotation.
 * Instead, use spring to autowire this class into the {@link AccountApiController}.
 */
@javax.annotation.Generated(value = "class com.mogree.MogreeCodeGen", date = "2021-05-11T13:27:53.093+02:00")

public interface AccountApiDelegate {

    /**
     * @see AccountApi#activate
     */
    public Object activate(ParamActivateBody paramActivateBody, ParamActivate paramActivate);

    /**
     * @see AccountApi#getUserData
     */
    public Object getUserData(ParamGetUserData paramGetUserData);

    /**
     * @see AccountApi#login
     */
    public Object login(ParamLogin paramLogin);

    /**
     * @see AccountApi#logout
     */
    public Object logout(ParamLogout paramLogout);

    /**
     * @see AccountApi#passwordReset
     */
    public Object passwordReset(ParamPasswordResetBody paramPasswordResetBody, ParamPasswordReset paramPasswordReset);

    /**
     * @see AccountApi#passwordResetUpdate
     */
    public Object passwordResetUpdate(ParamPasswordResetUpdateBody paramPasswordResetUpdateBody, ParamPasswordResetUpdate paramPasswordResetUpdate);

    /**
     * @see AccountApi#register
     */
    public Object register(ParamRegisterBody paramRegisterBody, ParamRegister paramRegister);

    /**
     * @see AccountApi#update
     */
    public Object update(ParamUpdateBody paramUpdateBody, ParamUpdate paramUpdate);

    /**
     * @see AccountApi#updatePassword
     */
    public Object updatePassword(ParamPasswordUpdate paramPasswordUpdate, ParamUpdatePassword paramUpdatePassword);

    @Component
    class AccountApiDelegateDefaultImpl implements AccountApiDelegate {
        @Override
        public Object activate(ParamActivateBody paramActivateBody, ParamActivate paramActivate){
            if (true){
                throw new APIItemNotFoundException("This API is not implemented yet. Plz be patient or give a present to the backend developer.");
            }
            return null;
        }
        @Override
        public Object getUserData(ParamGetUserData paramGetUserData){
            if (true){
                throw new APIItemNotFoundException("This API is not implemented yet. Plz be patient or give a present to the backend developer.");
            }
            return null;
        }
        @Override
        public Object login(ParamLogin paramLogin){
            if (true){
                throw new APIItemNotFoundException("This API is not implemented yet. Plz be patient or give a present to the backend developer.");
            }
            return null;
        }
        @Override
        public Object logout(ParamLogout paramLogout){
            if (true){
                throw new APIItemNotFoundException("This API is not implemented yet. Plz be patient or give a present to the backend developer.");
            }
            return null;
        }
        @Override
        public Object passwordReset(ParamPasswordResetBody paramPasswordResetBody, ParamPasswordReset paramPasswordReset){
            if (true){
                throw new APIItemNotFoundException("This API is not implemented yet. Plz be patient or give a present to the backend developer.");
            }
            return null;
        }
        @Override
        public Object passwordResetUpdate(ParamPasswordResetUpdateBody paramPasswordResetUpdateBody, ParamPasswordResetUpdate paramPasswordResetUpdate){
            if (true){
                throw new APIItemNotFoundException("This API is not implemented yet. Plz be patient or give a present to the backend developer.");
            }
            return null;
        }
        @Override
        public Object register(ParamRegisterBody paramRegisterBody, ParamRegister paramRegister){
            if (true){
                throw new APIItemNotFoundException("This API is not implemented yet. Plz be patient or give a present to the backend developer.");
            }
            return null;
        }
        @Override
        public Object update(ParamUpdateBody paramUpdateBody, ParamUpdate paramUpdate){
            if (true){
                throw new APIItemNotFoundException("This API is not implemented yet. Plz be patient or give a present to the backend developer.");
            }
            return null;
        }
        @Override
        public Object updatePassword(ParamPasswordUpdate paramPasswordUpdate, ParamUpdatePassword paramUpdatePassword){
            if (true){
                throw new APIItemNotFoundException("This API is not implemented yet. Plz be patient or give a present to the backend developer.");
            }
            return null;
        }
    }
}
