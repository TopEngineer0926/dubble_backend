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
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;

import com.mogree.spring.validator.IValidator;

@javax.annotation.Generated(value = "class com.mogree.MogreeCodeGen", date = "2021-05-11T13:27:53.093+02:00")

@RestController
public class AccountApiController implements AccountApi {

    @Autowired(required=false)
    private List<IValidator<? extends Object>> validators;

    private AccountApiDelegate delegate;

    @Autowired
    public AccountApiController(AccountApiDelegate delegate) {
        this.delegate = delegate;
    }


    public ResponseEntity<StatusResponse> activate(@ApiParam(value = "The code the user received by mail", required = true)  @RequestParam(value = "activationCode", required = true) String activationCode) {

        ParamActivateBody paramActivateBody = new ParamActivateBody(activationCode);
        ParamActivate paramActivate = new ParamActivate();

        return new Executer(validators).validate(paramActivateBody).usecase(() -> delegate.activate(paramActivateBody, paramActivate)).run();

    }


    public ResponseEntity<DetailResponse<UserModel>> getUserData() {

        ParamGetUserData paramGetUserData = new ParamGetUserData();

        return new Executer(validators).validate().usecase(() -> delegate.getUserData(paramGetUserData)).run();

    }


    public ResponseEntity<DetailResponse<UserAuthModel>> login(@ApiParam(value = "The user mail" ,required=true) @RequestHeader(value="mogree-Mail", required=true) String mogreeMail, @ApiParam(value = "The password hashed via sha256" ,required=true) @RequestHeader(value="mogree-Password", required=true) String mogreePassword) {

        ParamLogin paramLogin = new ParamLogin(mogreeMail, mogreePassword);

        return new Executer(validators).validate().usecase(() -> delegate.login(paramLogin)).run();

    }


    public ResponseEntity<StatusResponse> logout() {

        ParamLogout paramLogout = new ParamLogout();

        return new Executer(validators).validate().usecase(() -> delegate.logout(paramLogout)).run();

    }


    public ResponseEntity<StatusResponse> passwordReset(@ApiParam(value = "" ,required=true ) @RequestBody PasswordResetModel resetPasswordBody) {

        ParamPasswordResetBody paramPasswordResetBody = new ParamPasswordResetBody(resetPasswordBody);
        ParamPasswordReset paramPasswordReset = new ParamPasswordReset();

        return new Executer(validators).validate(paramPasswordResetBody).usecase(() -> delegate.passwordReset(paramPasswordResetBody, paramPasswordReset)).run();

    }


    public ResponseEntity<StatusResponse> passwordResetUpdate(@ApiParam(value = "" ,required=true ) @RequestBody PasswordUpdateModel passwordResetUpdateBody) {

        ParamPasswordResetUpdateBody paramPasswordResetUpdateBody = new ParamPasswordResetUpdateBody(passwordResetUpdateBody);
        ParamPasswordResetUpdate paramPasswordResetUpdate = new ParamPasswordResetUpdate();

        return new Executer(validators).validate(paramPasswordResetUpdateBody).usecase(() -> delegate.passwordResetUpdate(paramPasswordResetUpdateBody, paramPasswordResetUpdate)).run();

    }


    public ResponseEntity<DetailResponse<UserModel>> register(@ApiParam(value = ""  ) @RequestBody RegisterModel registerBody) {

        ParamRegisterBody paramRegisterBody = new ParamRegisterBody(registerBody);
        ParamRegister paramRegister = new ParamRegister();

        return new Executer(validators).validate(paramRegisterBody).usecase(() -> delegate.register(paramRegisterBody, paramRegister)).run();

    }


    public ResponseEntity<DetailResponse<UserModel>> update(@ApiParam(value = ""  ) @RequestBody UserModel updateBody) {

        ParamUpdateBody paramUpdateBody = new ParamUpdateBody(updateBody);
        ParamUpdate paramUpdate = new ParamUpdate();

        return new Executer(validators).validate(paramUpdateBody).usecase(() -> delegate.update(paramUpdateBody, paramUpdate)).run();

    }


    public ResponseEntity<StatusResponse> updatePassword(@ApiParam(value = "The current password hashed via sha256" ,required=true) @RequestHeader(value="mogree-Password-old", required=true) String mogreePasswordOld, @ApiParam(value = "The new password hashed via sha256" ,required=true) @RequestHeader(value="mogree-Password", required=true) String mogreePassword) {

        ParamPasswordUpdate paramPasswordUpdate = new ParamPasswordUpdate(mogreePasswordOld, mogreePassword);
        ParamUpdatePassword paramUpdatePassword = new ParamUpdatePassword();

        return new Executer(validators).validate(paramPasswordUpdate).usecase(() -> delegate.updatePassword(paramPasswordUpdate, paramUpdatePassword)).run();

    }


}

