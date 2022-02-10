package com.mogree.server.gen.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

@javax.annotation.Generated(value = "class com.mogree.MogreeCodeGen", date = "2021-05-11T13:27:53.093+02:00")

public class CustomerModel   {
  
  private String customerNumber = null;
  private String academicDegreePreceding = null;
  private String itemid = null;
  private String email = null;
  private String firstname = null;
  private String phoneNumber = null;
  private String lastname = null;
  private String academicDegreeSubsequent = null;
  private String category = null;
  private String companyName = null;
  private String domainName = null;
  private Integer type = 5;

  /**
   * Kundennummer
   **/
  public CustomerModel customerNumber(String customerNumber) {
    this.customerNumber = customerNumber;
    return this;
  }

  
  @ApiModelProperty(example = "", value = "Kundennummer")
  @JsonProperty("customer_number")
  public String getCustomerNumber() {
    return customerNumber;
  }
  public void setCustomerNumber(String customerNumber) {
    this.customerNumber = customerNumber;
  }

  /**
   * Vorangestellter Akad. Grad
   **/
  public CustomerModel academicDegreePreceding(String academicDegreePreceding) {
    this.academicDegreePreceding = academicDegreePreceding;
    return this;
  }

  
  @ApiModelProperty(example = "MMag. DDr.", value = "Vorangestellter Akad. Grad")
  @JsonProperty("academic_degree_preceding")
  public String getAcademicDegreePreceding() {
    return academicDegreePreceding;
  }
  public void setAcademicDegreePreceding(String academicDegreePreceding) {
    this.academicDegreePreceding = academicDegreePreceding;
  }

  /**
   * id of the customer
   **/
  public CustomerModel itemid(String itemid) {
    this.itemid = itemid;
    return this;
  }

  
  @ApiModelProperty(example = "1", value = "id of the customer")
  @JsonProperty("itemid")
  public String getItemid() {
    return itemid;
  }
  public void setItemid(String itemid) {
    this.itemid = itemid;
  }

  /**
   **/
  public CustomerModel email(String email) {
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
  public CustomerModel category(String category) {
    this.category = category;
    return this;
  }

  
  @ApiModelProperty(example = "category", value = "")
  @JsonProperty("category")
  public String getCategory() {
    return category;
  }
  public void setCategory(String category) {
    this.category = category;
  }

  /**
   **/
  public CustomerModel companyName(String companyName) {
    this.companyName = companyName;
    return this;
  }


  @ApiModelProperty(example = "company_name", value = "")
  @JsonProperty("company_name")
  public String getCompanyName() {
    return companyName;
  }
  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  /**
   **/
  public CustomerModel firstname(String firstname) {
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
  public CustomerModel phoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  
  @ApiModelProperty(example = "0664123456", value = "")
  @JsonProperty("phone_number")
  public String getPhoneNumber() {
    return phoneNumber;
  }
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  /**
   **/
  public CustomerModel lastname(String lastname) {
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
   * Nachgestellter Akad. Grad
   **/
  public CustomerModel academicDegreeSubsequent(String academicDegreeSubsequent) {
    this.academicDegreeSubsequent = academicDegreeSubsequent;
    return this;
  }

  
  @ApiModelProperty(example = "MSc PhD", value = "Nachgestellter Akad. Grad")
  @JsonProperty("academic_degree_subsequent")
  public String getAcademicDegreeSubsequent() {
    return academicDegreeSubsequent;
  }
  public void setAcademicDegreeSubsequent(String academicDegreeSubsequent) {
    this.academicDegreeSubsequent = academicDegreeSubsequent;
  }

  /**
   * Itemtype=5
   **/
  public CustomerModel type(Integer type) {
    this.type = type;
    return this;
  }

  
  @ApiModelProperty(example = "5", value = "Itemtype=5")
  @JsonProperty("type")
  public Integer getType() {
    return type;
  }
  public void setType(Integer type) {
    this.type = type;
  }

  /**
   **/
  public CustomerModel domainName(String domainName) {
    this.domainName = domainName;
    return this;
  }


  @ApiModelProperty(example = "domain_name", value = "")
  @JsonProperty("domain_name")
  public String getDomainName() {
    return domainName;
  }
  public void setDomainName(String domainName) {
    this.domainName = domainName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CustomerModel customerModel = (CustomerModel) o;
    return Objects.equals(customerNumber, customerModel.customerNumber) &&
        Objects.equals(academicDegreePreceding, customerModel.academicDegreePreceding) &&
        Objects.equals(itemid, customerModel.itemid) &&
        Objects.equals(email, customerModel.email) &&
        Objects.equals(firstname, customerModel.firstname) &&
        Objects.equals(phoneNumber, customerModel.phoneNumber) &&
        Objects.equals(lastname, customerModel.lastname) &&
        Objects.equals(academicDegreeSubsequent, customerModel.academicDegreeSubsequent) &&
        Objects.equals(category, customerModel.category) &&
        Objects.equals(companyName, customerModel.companyName) &&
        Objects.equals(domainName, customerModel.domainName) &&
        Objects.equals(type, customerModel.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customerNumber, academicDegreePreceding, itemid, email, firstname, phoneNumber, lastname, academicDegreeSubsequent, category, companyName, domainName, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CustomerModel {\n");
    
    sb.append("    customerNumber: ").append(toIndentedString(customerNumber)).append("\n");
    sb.append("    academicDegreePreceding: ").append(toIndentedString(academicDegreePreceding)).append("\n");
    sb.append("    itemid: ").append(toIndentedString(itemid)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    firstname: ").append(toIndentedString(firstname)).append("\n");
    sb.append("    phoneNumber: ").append(toIndentedString(phoneNumber)).append("\n");
    sb.append("    lastname: ").append(toIndentedString(lastname)).append("\n");
    sb.append("    academicDegreeSubsequent: ").append(toIndentedString(academicDegreeSubsequent)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    companyName: ").append(toIndentedString(companyName)).append("\n");
    sb.append("    domainName: ").append(toIndentedString(domainName)).append("\n");
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

