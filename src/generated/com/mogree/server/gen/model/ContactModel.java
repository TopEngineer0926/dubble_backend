package com.mogree.server.gen.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

@javax.annotation.Generated(value = "class com.mogree.MogreeCodeGen", date = "2021-05-11T13:27:53.093+02:00")

public class ContactModel   {
  
  private String itemid = null;
  private String email = null;
  private String firstname = null;
  private String phoneNumber = null;
  private String lastname = null;
  private String abbreviation = null;
  private Integer inviteStatus = null;
  private Integer type = 8;

  /**
   * Id of the contact
   **/
  public ContactModel itemid(String itemid) {
    this.itemid = itemid;
    return this;
  }

  
  @ApiModelProperty(example = "1", value = "Id of the contact")
  @JsonProperty("itemid")
  public String getItemid() {
    return itemid;
  }
  public void setItemid(String itemid) {
    this.itemid = itemid;
  }

  /**
   **/
  public ContactModel email(String email) {
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
  public ContactModel firstname(String firstname) {
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
  public ContactModel phoneNumber(String phoneNumber) {
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
  public ContactModel lastname(String lastname) {
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
   * Kurzzeichen
   **/
  public ContactModel abbreviation(String abbreviation) {
    this.abbreviation = abbreviation;
    return this;
  }


  @ApiModelProperty(example = "FL", value = "Kurzzeichen")
  @JsonProperty("abbreviation")
  public String getAbbreviation() {
    return abbreviation;
  }
  public void setAbbreviation(String abbreviation) {
    this.abbreviation = abbreviation;
  }

  /**
   **/
  public ContactModel inviteStatus(Integer inviteStatus) {
    this.inviteStatus = inviteStatus;
    return this;
  }


  @ApiModelProperty(example = "0", value = "0")
  @JsonProperty("invite_status")
  public Integer getInviteStatus() {
    return inviteStatus;
  }
  public void setInviteStatus(Integer inviteStatus) {
    this.inviteStatus = inviteStatus;
  }

  /**
   * Itemtype = 8
   **/
  public ContactModel type(Integer type) {
    this.type = type;
    return this;
  }

  
  @ApiModelProperty(example = "8", value = "Itemtype = 8")
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
    ContactModel contactModel = (ContactModel) o;
    return Objects.equals(itemid, contactModel.itemid) &&
        Objects.equals(email, contactModel.email) &&
        Objects.equals(firstname, contactModel.firstname) &&
        Objects.equals(phoneNumber, contactModel.phoneNumber) &&
        Objects.equals(lastname, contactModel.lastname) &&
        Objects.equals(abbreviation, contactModel.abbreviation) &&
        Objects.equals(type, contactModel.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(itemid, email, firstname, phoneNumber, lastname, abbreviation, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ContactModel {\n");
    
    sb.append("    itemid: ").append(toIndentedString(itemid)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    firstname: ").append(toIndentedString(firstname)).append("\n");
    sb.append("    phoneNumber: ").append(toIndentedString(phoneNumber)).append("\n");
    sb.append("    lastname: ").append(toIndentedString(lastname)).append("\n");
    sb.append("    abbreviation: ").append(toIndentedString(abbreviation)).append("\n");
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

