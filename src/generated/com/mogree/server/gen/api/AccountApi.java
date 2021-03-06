/**
 * NOTE: This class is auto generated by the swagger code generator program ().
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.mogree.server.gen.api;

import com.mogree.spring.response.StatusResponse;
import com.mogree.spring.Executer;
import com.mogree.server.gen.model.UserModel;
import com.mogree.spring.response.DetailResponse;
import com.mogree.server.gen.model.UserAuthModel;
import com.mogree.server.gen.model.PasswordResetModel;
import com.mogree.server.gen.model.PasswordUpdateModel;
import com.mogree.server.gen.model.RegisterModel;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@javax.annotation.Generated(value = "class com.mogree.MogreeCodeGen", date = "2021-05-11T13:27:53.093+02:00")

@RestController
@Api(value = "account", description = "the account API")

public interface AccountApi {

    @ApiOperation(value = "Activate the user by the code", nickname = "activate", notes = "")
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK"  ),
        @ApiResponse(code = 401, message = "Unauthorized"  ),
        @ApiResponse(code = 403, message = "Forbidden"  ) })
    @RequestMapping(value = "/account/activate",
        method = RequestMethod.PUT)
    ResponseEntity<StatusResponse> activate(@ApiParam(value = "The code the user received by mail", required = true)  @RequestParam(value = "activationCode", required = true) String activationCode);


    @ApiOperation(value = "Get the own user data", nickname = "getUserData", notes = "", authorizations = {
        @Authorization(value = "BasicAuth")
    })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "DetailResponse<UserModel>"  ),
        @ApiResponse(code = 401, message = "Unauthorized"  ),
        @ApiResponse(code = 403, message = "Forbidden"  ),
        @ApiResponse(code = 404, message = "Not Found"  ) })
    @RequestMapping(value = "/account",
        method = RequestMethod.GET)
    ResponseEntity<DetailResponse<UserModel>> getUserData();


    @ApiOperation(value = "User login via username and sha256 password", nickname = "login", notes = "")
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "DetailResponse<UserAuthModel>"  ),
        @ApiResponse(code = 401, message = "Unauthorized"  ),
        @ApiResponse(code = 403, message = "Forbidden"  ) })
    @RequestMapping(value = "/account/login",
        method = RequestMethod.PUT)
    ResponseEntity<DetailResponse<UserAuthModel>> login(@ApiParam(value = "The user mail" ,required=true) @RequestHeader(value="mogree-Mail", required=true) String mogreeMail,@ApiParam(value = "The password hashed via sha256" ,required=true) @RequestHeader(value="mogree-Password", required=true) String mogreePassword);


    @ApiOperation(value = "Logout for the user", nickname = "logout", notes = "", authorizations = {
        @Authorization(value = "BasicAuth")
    })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK"  ),
        @ApiResponse(code = 401, message = "Unauthorized"  ) })
    @RequestMapping(value = "/account/logout",
        method = RequestMethod.DELETE)
    ResponseEntity<StatusResponse> logout();


    @ApiOperation(value = "sends an email with the password reset link to a user", nickname = "passwordReset", notes = "")
    @ApiResponses(value = { 
        @ApiResponse(code = 202, message = "Accepted - email will be sent if it exists (no 404, when it does not exist, because of security"  ),
        @ApiResponse(code = 400, message = "Bad Request - invalid request body"  ),
        @ApiResponse(code = 422, message = "Unprocessable Entity - invalid mail address (regex)"  ),
        @ApiResponse(code = 500, message = "Internal Server Error"  ) })
    @RequestMapping(value = "/account/password/reset",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<StatusResponse> passwordReset(@ApiParam(value = "" ,required=true ) @RequestBody PasswordResetModel resetPasswordBody);


    @ApiOperation(value = "updates the password of a user", nickname = "passwordResetUpdate", notes = "")
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK - password was updated"  ),
        @ApiResponse(code = 400, message = "Bad Request - invalid request body"  ),
        @ApiResponse(code = 403, message = "Forbidden - invalid token"  ),
        @ApiResponse(code = 409, message = "Conflict - password was already updated and token is now invalid"  ),
        @ApiResponse(code = 500, message = "Internal Server Error"  ) })
    @RequestMapping(value = "/account/password/reset/update",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<StatusResponse> passwordResetUpdate(@ApiParam(value = "" ,required=true ) @RequestBody PasswordUpdateModel passwordResetUpdateBody);


    @ApiOperation(value = "Creates a new unverfied account", nickname = "register", notes = "")
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "DetailResponse<UserModel>"  ) })
    @RequestMapping(value = "/account",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<DetailResponse<UserModel>> register(@ApiParam(value = ""  ) @RequestBody RegisterModel registerBody);


    @ApiOperation(value = "Update the user with the current settings", nickname = "update", notes = "", authorizations = {
        @Authorization(value = "BasicAuth")
    })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "DetailResponse<UserModel>"  ) })
    @RequestMapping(value = "/account",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<DetailResponse<UserModel>> update(@ApiParam(value = ""  ) @RequestBody UserModel updateBody);


    @ApiOperation(value = "set a new sha256 password and checks the old password", nickname = "updatePassword", notes = "")
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Ok"  ),
        @ApiResponse(code = 401, message = "Unauthorized"  ),
        @ApiResponse(code = 403, message = "Forbidden"  ) })
    @RequestMapping(value = "/account/password",
        method = RequestMethod.PUT)
    ResponseEntity<StatusResponse> updatePassword(@ApiParam(value = "The current password hashed via sha256" ,required=true) @RequestHeader(value="mogree-Password-old", required=true) String mogreePasswordOld,@ApiParam(value = "The new password hashed via sha256" ,required=true) @RequestHeader(value="mogree-Password", required=true) String mogreePassword);

}
