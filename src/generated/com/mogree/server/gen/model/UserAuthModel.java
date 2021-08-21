package com.mogree.server.gen.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mogree.server.gen.model.UserModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

@javax.annotation.Generated(value = "class com.mogree.MogreeCodeGen", date = "2021-05-11T13:27:53.093+02:00")

public class UserAuthModel   {
  
  private String token = null;
  private String itemid = null;
  private Integer type = 2;
  private UserModel userModel = null;

  /**
   **/
  public UserAuthModel token(String token) {
    this.token = token;
    return this;
  }

  
  @ApiModelProperty(example = "token", value = "")
  @JsonProperty("token")
  public String getToken() {
    return token;
  }
  public void setToken(String token) {
    this.token = token;
  }

  /**
   **/
  public UserAuthModel itemid(String itemid) {
    this.itemid = itemid;
    return this;
  }

  
  @ApiModelProperty(example = "2", value = "")
  @JsonProperty("itemid")
  public String getItemid() {
    return itemid;
  }
  public void setItemid(String itemid) {
    this.itemid = itemid;
  }

  /**
   * Itemtype=2
   **/
  public UserAuthModel type(Integer type) {
    this.type = type;
    return this;
  }

  
  @ApiModelProperty(example = "2", value = "Itemtype=2")
  @JsonProperty("type")
  public Integer getType() {
    return type;
  }
  public void setType(Integer type) {
    this.type = type;
  }

  /**
   **/
  public UserAuthModel userModel(UserModel userModel) {
    this.userModel = userModel;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("userModel")
  public UserModel getUserModel() {
    return userModel;
  }
  public void setUserModel(UserModel userModel) {
    this.userModel = userModel;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserAuthModel userAuthModel = (UserAuthModel) o;
    return Objects.equals(token, userAuthModel.token) &&
        Objects.equals(itemid, userAuthModel.itemid) &&
        Objects.equals(type, userAuthModel.type) &&
        Objects.equals(userModel, userAuthModel.userModel);
  }

  @Override
  public int hashCode() {
    return Objects.hash(token, itemid, type, userModel);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserAuthModel {\n");
    
    sb.append("    token: ").append(toIndentedString(token)).append("\n");
    sb.append("    itemid: ").append(toIndentedString(itemid)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    userModel: ").append(toIndentedString(userModel)).append("\n");
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

