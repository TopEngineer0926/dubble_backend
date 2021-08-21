package com.mogree.server.gen.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

@javax.annotation.Generated(value = "class com.mogree.MogreeCodeGen", date = "2021-05-11T13:27:53.093+02:00")

public class MediaModel   {
  
  private String itemid = null;
  private Integer type = null;
  private String url = null;
  private String modelName = null;
  private Integer modelId = null;
  private Integer mediaType = null;
  private String title = null;
  private Integer order = null;
  private String status = null;

  /**
   * mediaId of the image/video for the delete request
   **/
  public MediaModel itemid(String itemid) {
    this.itemid = itemid;
    return this;
  }

  
  @ApiModelProperty(example = "1", value = "mediaId of the image/video for the delete request")
  @JsonProperty("itemid")
  public String getItemid() {
    return itemid;
  }
  public void setItemid(String itemid) {
    this.itemid = itemid;
  }

  /**
   * itemtype=4
   **/
  public MediaModel type(Integer type) {
    this.type = type;
    return this;
  }

  
  @ApiModelProperty(example = "4", value = "itemtype=4")
  @JsonProperty("type")
  public Integer getType() {
    return type;
  }
  public void setType(Integer type) {
    this.type = type;
  }

  /**
   * url of the media item
   **/
  public MediaModel url(String url) {
    this.url = url;
    return this;
  }

  
  @ApiModelProperty(example = "dubble.at/video/id.mp4", value = "url of the media item")
  @JsonProperty("url")
  public String getUrl() {
    return url;
  }
  public void setUrl(String url) {
    this.url = url;
  }

  /**
   * name of the mapping model
   **/
  public MediaModel modelName(String modelName) {
    this.modelName = modelName;
    return this;
  }

  
  @ApiModelProperty(example = "user", value = "name of the mapping model")
  @JsonProperty("model_name")
  public String getModelName() {
    return modelName;
  }
  public void setModelName(String modelName) {
    this.modelName = modelName;
  }

  /**
   * Id of the model name
   **/
  public MediaModel modelId(Integer modelId) {
    this.modelId = modelId;
    return this;
  }

  
  @ApiModelProperty(example = "1", value = "Id of the model name")
  @JsonProperty("model_id")
  public Integer getModelId() {
    return modelId;
  }
  public void setModelId(Integer modelId) {
    this.modelId = modelId;
  }

  /**
   * Media type of the file
   **/
  public MediaModel mediaType(Integer mediaType) {
    this.mediaType = mediaType;
    return this;
  }

  
  @ApiModelProperty(example = "1", value = "Media type of the file")
  @JsonProperty("media_type")
  public Integer getMediaType() {
    return mediaType;
  }
  public void setMediaType(Integer mediaType) {
    this.mediaType = mediaType;
  }

  /**
   * Media title / headline / button label
   **/
  public MediaModel title(String title) {
    this.title = title;
    return this;
  }

  
  @ApiModelProperty(example = "Title", value = "Media title / headline / button label")
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Order used to display on Client
   **/
  public MediaModel order(Integer order) {
    this.order = order;
    return this;
  }

  
  @ApiModelProperty(example = "1", value = "Order used to display on Client")
  @JsonProperty("order")
  public Integer getOrder() {
    return order;
  }
  public void setOrder(Integer order) {
    this.order = order;
  }

  /**
   * Media upload/conversion status
   **/
  public MediaModel status(String status) {
    this.status = status;
    return this;
  }

  
  @ApiModelProperty(example = "processing", value = "Media upload/conversion status")
  @JsonProperty("status")
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MediaModel mediaModel = (MediaModel) o;
    return Objects.equals(itemid, mediaModel.itemid) &&
        Objects.equals(type, mediaModel.type) &&
        Objects.equals(url, mediaModel.url) &&
        Objects.equals(modelName, mediaModel.modelName) &&
        Objects.equals(modelId, mediaModel.modelId) &&
        Objects.equals(mediaType, mediaModel.mediaType) &&
        Objects.equals(title, mediaModel.title) &&
        Objects.equals(order, mediaModel.order) &&
        Objects.equals(status, mediaModel.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(itemid, type, url, modelName, modelId, mediaType, title, order, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MediaModel {\n");
    
    sb.append("    itemid: ").append(toIndentedString(itemid)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    modelName: ").append(toIndentedString(modelName)).append("\n");
    sb.append("    modelId: ").append(toIndentedString(modelId)).append("\n");
    sb.append("    mediaType: ").append(toIndentedString(mediaType)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    order: ").append(toIndentedString(order)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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

