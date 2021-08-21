package com.mogree.server.gen.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

@javax.annotation.Generated(value = "class com.mogree.MogreeCodeGen", date = "2021-05-11T13:27:53.093+02:00")

public class CustomSectionModel   {
  
  private String itemid = null;
  private Integer type = 10;
  private String headLine = null;
  private String link = null;
  private String buttonText = null;
  private String customText = null;

  /**
   * Id of the custom section
   **/
  public CustomSectionModel itemid(String itemid) {
    this.itemid = itemid;
    return this;
  }

  
  @ApiModelProperty(example = "1", value = "Id of the custom section")
  @JsonProperty("itemid")
  public String getItemid() {
    return itemid;
  }
  public void setItemid(String itemid) {
    this.itemid = itemid;
  }

  /**
   * Itemtype = 10
   **/
  public CustomSectionModel type(Integer type) {
    this.type = type;
    return this;
  }

  
  @ApiModelProperty(example = "10", value = "Itemtype = 10")
  @JsonProperty("type")
  public Integer getType() {
    return type;
  }
  public void setType(Integer type) {
    this.type = type;
  }

  /**
   **/
  public CustomSectionModel headLine(String headLine) {
    this.headLine = headLine;
    return this;
  }

  
  @ApiModelProperty(example = "Head Line", value = "")
  @JsonProperty("head_line")
  public String getHeadLine() {
    return headLine;
  }
  public void setHeadLine(String headLine) {
    this.headLine = headLine;
  }

  /**
   **/
  public CustomSectionModel link(String link) {
    this.link = link;
    return this;
  }

  
  @ApiModelProperty(example = "http://example.ex/some-link", value = "")
  @JsonProperty("link")
  public String getLink() {
    return link;
  }
  public void setLink(String link) {
    this.link = link;
  }

  /**
   **/
  public CustomSectionModel buttonText(String buttonText) {
    this.buttonText = buttonText;
    return this;
  }

  
  @ApiModelProperty(example = "Click", value = "")
  @JsonProperty("button_text")
  public String getButtonText() {
    return buttonText;
  }
  public void setButtonText(String buttonText) {
    this.buttonText = buttonText;
  }

  /**
   **/
  public CustomSectionModel customText(String customText) {
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CustomSectionModel customSectionModel = (CustomSectionModel) o;
    return Objects.equals(itemid, customSectionModel.itemid) &&
        Objects.equals(type, customSectionModel.type) &&
        Objects.equals(headLine, customSectionModel.headLine) &&
        Objects.equals(link, customSectionModel.link) &&
        Objects.equals(buttonText, customSectionModel.buttonText) &&
        Objects.equals(customText, customSectionModel.customText);
  }

  @Override
  public int hashCode() {
    return Objects.hash(itemid, type, headLine, link, buttonText, customText);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CustomSectionModel {\n");
    
    sb.append("    itemid: ").append(toIndentedString(itemid)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    headLine: ").append(toIndentedString(headLine)).append("\n");
    sb.append("    link: ").append(toIndentedString(link)).append("\n");
    sb.append("    buttonText: ").append(toIndentedString(buttonText)).append("\n");
    sb.append("    customText: ").append(toIndentedString(customText)).append("\n");
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

