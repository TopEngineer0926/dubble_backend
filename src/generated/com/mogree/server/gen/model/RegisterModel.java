package com.mogree.server.gen.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

@javax.annotation.Generated(value = "class com.mogree.MogreeCodeGen", date = "2021-05-11T13:27:53.093+02:00")

public class RegisterModel   {
  
  private String email = null;
  private String firstname = null;
  private String lastname = null;
  private String companyName = null;
  private String password = null;
  private Integer type = 1;

  /**
   **/
  public RegisterModel email(String email) {
    this.email = email;
    return this;
  }

  
  @ApiModelProperty(example = "example@example.example", value = "")
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   **/
  public RegisterModel firstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  
  @ApiModelProperty(example = "First", value = "")
  @JsonProperty("firstname")
  public String getFirstname() {
    return firstname;
  }
  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  /**
   **/
  public RegisterModel lastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  
  @ApiModelProperty(example = "Last", value = "")
  @JsonProperty("lastname")
  public String getLastname() {
    return lastname;
  }
  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  /**
   **/
  public RegisterModel companyName(String companyName) {
    this.companyName = companyName;
    return this;
  }

  
  @ApiModelProperty(example = "Company Name", value = "")
  @JsonProperty("company_name")
  public String getCompanyName() {
    return companyName;
  }
  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  /**
   * User's password
   **/
  public RegisterModel password(String password) {
    this.password = password;
    return this;
  }

  
  @ApiModelProperty(example = "password", value = "User's password")
  @JsonProperty("password")
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Itemtype=1
   **/
  public RegisterModel type(Integer type) {
    this.type = type;
    return this;
  }

  
  @ApiModelProperty(example = "1", value = "Itemtype=1")
  @JsonProperty("type")
  public Integer getType() {
    return type;
  }
  public void setType(Integer type) {
    this.type = type;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RegisterModel registerModel = (RegisterModel) o;
    return Objects.equals(email, registerModel.email) &&
        Objects.equals(firstname, registerModel.firstname) &&
        Objects.equals(lastname, registerModel.lastname) &&
        Objects.equals(companyName, registerModel.companyName) &&
        Objects.equals(password, registerModel.password) &&
        Objects.equals(type, registerModel.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, firstname, lastname, companyName, password, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RegisterModel {\n");
    
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    firstname: ").append(toIndentedString(firstname)).append("\n");
    sb.append("    lastname: ").append(toIndentedString(lastname)).append("\n");
    sb.append("    companyName: ").append(toIndentedString(companyName)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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

