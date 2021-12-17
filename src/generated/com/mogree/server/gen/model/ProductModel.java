package com.mogree.server.gen.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mogree.server.gen.model.ContactModel;
import com.mogree.server.gen.model.CustomSectionModel;
import com.mogree.server.gen.model.CustomerModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

@javax.annotation.Generated(value = "class com.mogree.MogreeCodeGen", date = "2021-05-11T13:27:53.093+02:00")

public class ProductModel   {
  
  private String itemid = null;
  private Integer type = 9;
  private String headline = null;
  private String greeting = null;
  private String customText = null;
  private String template = null;

  public enum PublicationStatusEnum {
    DRAFT("DRAFT"),
    PUBLISHED("PUBLISHED");

    private String value;

    PublicationStatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return value;
    }
  }

  private PublicationStatusEnum publicationStatus = null;
  private String reviewSectionName = null;
  private String pressInfoSectionName = null;
  private String shareCode = null;
  private List<CustomSectionModel> reviews = new ArrayList<CustomSectionModel>();
  private List<CustomSectionModel> pressInfos = new ArrayList<CustomSectionModel>();
  private Long userId = null;
  private ContactModel contact = null;
  private CustomerModel customer = null;
  private OffsetDateTime createdAt = null;
  private String internalPageTitle = null;
  private String videoSectionHeadline = null;
  private String mailHeadline = null;
  private String mailTextline = null;

  /**
   * Id of the contact
   **/
  public ProductModel itemid(String itemid) {
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
   * Itemtype = 9
   **/
  public ProductModel type(Integer type) {
    this.type = type;
    return this;
  }

  
  @ApiModelProperty(example = "9", value = "Itemtype = 9")
  @JsonProperty("type")
  public Integer getType() {
    return type;
  }
  public void setType(Integer type) {
    this.type = type;
  }

  /**
   **/
  public ProductModel headline(String headline) {
    this.headline = headline;
    return this;
  }

  
  @ApiModelProperty(example = "Ihre digital...", value = "")
  @JsonProperty("headline")
  public String getHeadline() {
    return headline;
  }
  public void setHeadline(String headline) {
    this.headline = headline;
  }

  /**
   **/
  public ProductModel greeting(String greeting) {
    this.greeting = greeting;
    return this;
  }

  
  @ApiModelProperty(example = "Sehr geehrter...", value = "")
  @JsonProperty("greeting")
  public String getGreeting() {
    return greeting;
  }
  public void setGreeting(String greeting) {
    this.greeting = greeting;
  }

  /**
   **/
  public ProductModel customText(String customText) {
    this.customText = customText;
    return this;
  }

  
  @ApiModelProperty(example = "Lorem ipsum dolor...", value = "")
  @JsonProperty("custom_text")
  public String getCustomText() {
    return customText;
  }
  public void setCustomText(String customText) {
    this.customText = customText;
  }

  /**
   **/
  public ProductModel publicationStatus(PublicationStatusEnum publicationStatus) {
    this.publicationStatus = publicationStatus;
    return this;
  }

  
  @ApiModelProperty(example = "DRAFT", value = "")
  @JsonProperty("publication_status")
  public PublicationStatusEnum getPublicationStatus() {
    return publicationStatus;
  }
  public void setPublicationStatus(PublicationStatusEnum publicationStatus) {
    this.publicationStatus = publicationStatus;
  }

  /**
   **/
  public ProductModel reviewSectionName(String reviewSectionName) {
    this.reviewSectionName = reviewSectionName;
    return this;
  }

  
  @ApiModelProperty(example = "Testberichte", value = "")
  @JsonProperty("review_section_name")
  public String getReviewSectionName() {
    return reviewSectionName;
  }
  public void setReviewSectionName(String reviewSectionName) {
    this.reviewSectionName = reviewSectionName;
  }

  /**
   **/
  public ProductModel pressInfoSectionName(String pressInfoSectionName) {
    this.pressInfoSectionName = pressInfoSectionName;
    return this;
  }

  
  @ApiModelProperty(example = "Pressestimmen", value = "")
  @JsonProperty("press_info_section_name")
  public String getPressInfoSectionName() {
    return pressInfoSectionName;
  }
  public void setPressInfoSectionName(String pressInfoSectionName) {
    this.pressInfoSectionName = pressInfoSectionName;
  }

  /**
   **/
  public ProductModel shareCode(String shareCode) {
    this.shareCode = shareCode;
    return this;
  }

  
  @ApiModelProperty(example = "7C215451E96A08C86532C", value = "")
  @JsonProperty("share_code")
  public String getShareCode() {
    return shareCode;
  }
  public void setShareCode(String shareCode) {
    this.shareCode = shareCode;
  }

  /**
   **/
  public ProductModel reviews(List<CustomSectionModel> reviews) {
    this.reviews = reviews;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("reviews")
  public List<CustomSectionModel> getReviews() {
    return reviews;
  }
  public void setReviews(List<CustomSectionModel> reviews) {
    this.reviews = reviews;
  }

  /**
   **/
  public ProductModel pressInfos(List<CustomSectionModel> pressInfos) {
    this.pressInfos = pressInfos;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("press_infos")
  public List<CustomSectionModel> getPressInfos() {
    return pressInfos;
  }
  public void setPressInfos(List<CustomSectionModel> pressInfos) {
    this.pressInfos = pressInfos;
  }

  /**
   **/
  public ProductModel userId(Long userId) {
    this.userId = userId;
    return this;
  }

  
  @ApiModelProperty(example = "1", value = "")
  @JsonProperty("user_id")
  public Long getUserId() {
    return userId;
  }
  public void setUserId(Long userId) {
    this.userId = userId;
  }

  /**
   **/
  public ProductModel contact(ContactModel contact) {
    this.contact = contact;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("contact")
  public ContactModel getContact() {
    return contact;
  }
  public void setContact(ContactModel contact) {
    this.contact = contact;
  }

  /**
   **/
  public ProductModel customer(CustomerModel customer) {
    this.customer = customer;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("customer")
  public CustomerModel getCustomer() {
    return customer;
  }
  public void setCustomer(CustomerModel customer) {
    this.customer = customer;
  }

  /**
   * DateTime when object was created
   **/
  public ProductModel createdAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  
  @ApiModelProperty(example = "2021-03-25T08:57:46+0100", value = "DateTime when object was created")
  @JsonProperty("created_at")
  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }
  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  /**
   * Seiten Titel (interne Bezeichnung)
   **/
  public ProductModel internalPageTitle(String internalPageTitle) {
    this.internalPageTitle = internalPageTitle;
    return this;
  }

  
  @ApiModelProperty(example = "Page Title", value = "Seiten Titel (interne Bezeichnung)")
  @JsonProperty("internal_page_title")
  public String getInternalPageTitle() {
    return internalPageTitle;
  }
  public void setInternalPageTitle(String internalPageTitle) {
    this.internalPageTitle = internalPageTitle;
  }

  /**
   * headline for video section on product page
   **/
  public ProductModel videoSectionHeadline(String videoSectionHeadline) {
    this.videoSectionHeadline = videoSectionHeadline;
    return this;
  }

  
  @ApiModelProperty(example = "Video Section Headline", value = "headline for video section on product page")
  @JsonProperty("video_section_headline")
  public String getVideoSectionHeadline() {
    return videoSectionHeadline;
  }
  public void setVideoSectionHeadline(String videoSectionHeadline) {
    this.videoSectionHeadline = videoSectionHeadline;
  }

  /**
   * template field
   **/
  public ProductModel template(String template) {
    this.template = template;
    return this;
  }

  @ApiModelProperty(example = "template", value = "")
  @JsonProperty("template")
  public String getTemplate() {
    return template;
  }
  public void setTemplate(String template) {
    this.template = template;
  }

  /**
   **/
  public ProductModel mailHeadline(String mailHeadline) {
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
  public ProductModel mailTextline(String mailTextline) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductModel productModel = (ProductModel) o;
    return Objects.equals(itemid, productModel.itemid) &&
        Objects.equals(type, productModel.type) &&
        Objects.equals(headline, productModel.headline) &&
        Objects.equals(greeting, productModel.greeting) &&
        Objects.equals(customText, productModel.customText) &&
        Objects.equals(publicationStatus, productModel.publicationStatus) &&
        Objects.equals(reviewSectionName, productModel.reviewSectionName) &&
        Objects.equals(pressInfoSectionName, productModel.pressInfoSectionName) &&
        Objects.equals(shareCode, productModel.shareCode) &&
        Objects.equals(reviews, productModel.reviews) &&
        Objects.equals(pressInfos, productModel.pressInfos) &&
        Objects.equals(userId, productModel.userId) &&
        Objects.equals(contact, productModel.contact) &&
        Objects.equals(customer, productModel.customer) &&
        Objects.equals(createdAt, productModel.createdAt) &&
        Objects.equals(internalPageTitle, productModel.internalPageTitle) &&
        Objects.equals(template, productModel.template) &&
        Objects.equals(mailHeadline, productModel.mailHeadline) &&
        Objects.equals(mailTextline, productModel.mailTextline) &&
        Objects.equals(videoSectionHeadline, productModel.videoSectionHeadline);
  }

  @Override
  public int hashCode() {
    return Objects.hash(itemid, type, headline, greeting, customText, publicationStatus, reviewSectionName, pressInfoSectionName, shareCode, reviews, pressInfos, userId, contact, customer, createdAt, internalPageTitle, videoSectionHeadline, template, mailHeadline, mailTextline);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductModel {\n");
    
    sb.append("    itemid: ").append(toIndentedString(itemid)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    headline: ").append(toIndentedString(headline)).append("\n");
    sb.append("    greeting: ").append(toIndentedString(greeting)).append("\n");
    sb.append("    customText: ").append(toIndentedString(customText)).append("\n");
    sb.append("    publicationStatus: ").append(toIndentedString(publicationStatus)).append("\n");
    sb.append("    reviewSectionName: ").append(toIndentedString(reviewSectionName)).append("\n");
    sb.append("    pressInfoSectionName: ").append(toIndentedString(pressInfoSectionName)).append("\n");
    sb.append("    shareCode: ").append(toIndentedString(shareCode)).append("\n");
    sb.append("    reviews: ").append(toIndentedString(reviews)).append("\n");
    sb.append("    pressInfos: ").append(toIndentedString(pressInfos)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    contact: ").append(toIndentedString(contact)).append("\n");
    sb.append("    customer: ").append(toIndentedString(customer)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    internalPageTitle: ").append(toIndentedString(internalPageTitle)).append("\n");
    sb.append("    videoSectionHeadline: ").append(toIndentedString(videoSectionHeadline)).append("\n");
    sb.append("    template: ").append(toIndentedString(template)).append("\n");
    sb.append("    mailHeadline: ").append(toIndentedString(mailHeadline)).append("\n");
    sb.append("    mailTextline: ").append(toIndentedString(mailTextline)).append("\n");
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

