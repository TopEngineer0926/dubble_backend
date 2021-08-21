package com.mogree.server.gen.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

@javax.annotation.Generated(value = "class com.mogree.MogreeCodeGen", date = "2021-05-11T13:27:53.093+02:00")

public class PasswordUpdateModel   {
  
  private String itemid = null;
  private Integer type = 7;
  private String passwordResetCode = null;
  private String newPassword = null;

  /**
   **/
  public PasswordUpdateModel itemid(String itemid) {
    this.itemid = itemid;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("itemid")
  public String getItemid() {
    return itemid;
  }
  public void setItemid(String itemid) {
    this.itemid = itemid;
  }

  /**
   * Itemtype = 7
   **/
  public PasswordUpdateModel type(Integer type) {
    this.type = type;
    return this;
  }

  
  @ApiModelProperty(value = "Itemtype = 7")
  @JsonProperty("type")
  public Integer getType() {
    return type;
  }
  public void setType(Integer type) {
    this.type = type;
  }

  /**
   * The password reset code from the email
   **/
  public PasswordUpdateModel passwordResetCode(String passwordResetCode) {
    this.passwordResetCode = passwordResetCode;
    return this;
  }

  
  @ApiModelProperty(value = "The password reset code from the email")
  @JsonProperty("passwordResetCode")
  public String getPasswordResetCode() {
    return passwordResetCode;
  }
  public void setPasswordResetCode(String passwordResetCode) {
    this.passwordResetCode = passwordResetCode;
  }

  /**
   * The new password specified by the user
   **/
  public PasswordUpdateModel newPassword(String newPassword) {
    this.newPassword = newPassword;
    return this;
  }

  
  @ApiModelProperty(required = true, value = "The new password specified by the user")
  @JsonProperty("newPassword")
  public String getNewPassword() {
    return newPassword;
  }
  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PasswordUpdateModel passwordUpdateModel = (PasswordUpdateModel) o;
    return Objects.equals(itemid, passwordUpdateModel.itemid) &&
        Objects.equals(type, passwordUpdateModel.type) &&
        Objects.equals(passwordResetCode, passwordUpdateModel.passwordResetCode) &&
        Objects.equals(newPassword, passwordUpdateModel.newPassword);
  }

  @Override
  public int hashCode() {
    return Objects.hash(itemid, type, passwordResetCode, newPassword);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PasswordUpdateModel {\n");
    
    sb.append("    itemid: ").append(toIndentedString(itemid)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    passwordResetCode: ").append(toIndentedString(passwordResetCode)).append("\n");
    sb.append("    newPassword: ").append(toIndentedString(newPassword)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

