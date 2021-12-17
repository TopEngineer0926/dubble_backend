package com.mogree.server.gen.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

@javax.annotation.Generated(value = "class com.mogree.MogreeCodeGen", date = "2021-05-11T13:27:53.093+02:00")

public class UserModel   {
  
  private String email = null;
  private String firstname = null;
  private String itemid = null;
  private String lastname = null;
  private String companyName = null;
  private String mailHeadline = null;
  private String mailTextline = null;
  private String mainColor = null;
  private String secondaryColor = null;
  private String contactButtonColor = null;


  public enum LogoPositionEnum {
    LEFT("left"),
    CENTER("center"),
    RIGHT("right");

    private String value;

    LogoPositionEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return value;
    }
  }

  private LogoPositionEnum logoPosition = null;
  private Integer type = 3;

  /**
   **/
  public UserModel email(String email) {
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
  public UserModel firstname(String firstname) {
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
  public UserModel itemid(String itemid) {
    this.itemid = itemid;
    return this;
  }

  
  @ApiModelProperty(example = "0", value = "")
  @JsonProperty("itemid")
  public String getItemid() {
    return itemid;
  }
  public void setItemid(String itemid) {
    this.itemid = itemid;
  }

  /**
   **/
  public UserModel lastname(String lastname) {
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
  public UserModel companyName(String companyName) {
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
   **/
  public UserModel mailHeadline(String mailHeadline) {
    this.mailHeadline = mailHeadline;
    return this;
  }


  @ApiModelProperty(example = "Mail Headline", value = "")
  @JsonProperty("mail_headline")
  public String getMailHeadline() {
    return mailHeadline;
  }
  public void setMailHeadline(String mailHeadline) {
    this.mailHeadline = mailHeadline;
  }

  /**
   **/
  public UserModel mailTextline(String mailTextline) {
    this.mailTextline = mailTextline;
    return this;
  }


  @ApiModelProperty(example = "Mail Textline", value = "")
  @JsonProperty("mail_textline")
  public String getMailTextline() {
    return mailTextline;
  }
  public void setMailTextline(String mailTextline) {
    this.mailTextline = mailTextline;
  }

  /**
   **/
  public UserModel mainColor(String mainColor) {
    this.mainColor = mainColor;
    return this;
  }

  
  @ApiModelProperty(example = "#000000", value = "")
  @JsonProperty("main_color")
  public String getMainColor() {
    return mainColor;
  }
  public void setMainColor(String mainColor) {
    this.mainColor = mainColor;
  }

  /**
   **/
  public UserModel secondaryColor(String secondaryColor) {
    this.secondaryColor = secondaryColor;
    return this;
  }

  
  @ApiModelProperty(example = "#000000", value = "")
  @JsonProperty("secondary_color")
  public String getSecondaryColor() {
    return secondaryColor;
  }
  public void setSecondaryColor(String secondaryColor) {
    this.secondaryColor = secondaryColor;
  }

  /**
   **/
  public UserModel contactButtonColor(String contactButtonColor) {
    this.contactButtonColor = contactButtonColor;
    return this;
  }

  
  @ApiModelProperty(example = "#000000", value = "")
  @JsonProperty("contact_button_color")
  public String getContactButtonColor() {
    return contactButtonColor;
  }
  public void setContactButtonColor(String contactButtonColor) {
    this.contactButtonColor = contactButtonColor;
  }

  /**
   **/
  public UserModel logoPosition(LogoPositionEnum logoPosition) {
    this.logoPosition = logoPosition;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("logo_position")
  public LogoPositionEnum getLogoPosition() {
    return logoPosition;
  }
  public void setLogoPosition(LogoPositionEnum logoPosition) {
    this.logoPosition = logoPosition;
  }

  /**
   * Itemtype=3
   **/
  public UserModel type(Integer type) {
    this.type = type;
    return this;
  }

  
  @ApiModelProperty(example = "3", value = "Itemtype=3")
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
    UserModel userModel = (UserModel) o;
    return Objects.equals(email, userModel.email) &&
        Objects.equals(firstname, userModel.firstname) &&
        Objects.equals(itemid, userModel.itemid) &&
        Objects.equals(lastname, userModel.lastname) &&
        Objects.equals(companyName, userModel.companyName) &&
        Objects.equals(mailHeadline, userModel.mailHeadline) &&
        Objects.equals(mailTextline, userModel.mailTextline) &&
        Objects.equals(mainColor, userModel.mainColor) &&
        Objects.equals(secondaryColor, userModel.secondaryColor) &&
        Objects.equals(contactButtonColor, userModel.contactButtonColor) &&
        Objects.equals(logoPosition, userModel.logoPosition) &&
        Objects.equals(type, userModel.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, firstname, itemid, lastname, companyName, mainColor, secondaryColor, contactButtonColor, logoPosition, type, mailHeadline, mailTextline);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserModel {\n");
    
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    firstname: ").append(toIndentedString(firstname)).append("\n");
    sb.append("    itemid: ").append(toIndentedString(itemid)).append("\n");
    sb.append("    lastname: ").append(toIndentedString(lastname)).append("\n");
    sb.append("    companyName: ").append(toIndentedString(companyName)).append("\n");
    sb.append("    mailHeadline: ").append(toIndentedString(mailHeadline)).append("\n");
    sb.append("    mailTextline: ").append(toIndentedString(mailTextline)).append("\n");
    sb.append("    mainColor: ").append(toIndentedString(mainColor)).append("\n");
    sb.append("    secondaryColor: ").append(toIndentedString(secondaryColor)).append("\n");
    sb.append("    contactButtonColor: ").append(toIndentedString(contactButtonColor)).append("\n");
    sb.append("    logoPosition: ").append(toIndentedString(logoPosition)).append("\n");
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

