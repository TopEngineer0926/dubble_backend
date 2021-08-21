package com.mogree.server.gen.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

@javax.annotation.Generated(value = "class com.mogree.MogreeCodeGen", date = "2021-05-11T13:27:53.093+02:00")

public class PasswordResetModel   {
  
  private String itemid = null;
  private Integer type = 6;
  private String email = null;

  /**
   **/
  public PasswordResetModel itemid(String itemid) {
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
   * Itemtype = 6
   **/
  public PasswordResetModel type(Integer type) {
    this.type = type;
    return this;
  }

  
  @ApiModelProperty(value = "Itemtype = 6")
  @JsonProperty("type")
  public Integer getType() {
    return type;
  }
  public void setType(Integer type) {
    this.type = type;
  }

  /**
   * The email of the account, to which the reset email will be sent
   **/
  public PasswordResetModel email(String email) {
    this.email = email;
    return this;
  }

  
  @ApiModelProperty(required = true, value = "The email of the account, to which the reset email will be sent")
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PasswordResetModel passwordResetModel = (PasswordResetModel) o;
    return Objects.equals(itemid, passwordResetModel.itemid) &&
        Objects.equals(type, passwordResetModel.type) &&
        Objects.equals(email, passwordResetModel.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(itemid, type, email);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PasswordResetModel {\n");
    
    sb.append("    itemid: ").append(toIndentedString(itemid)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
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

