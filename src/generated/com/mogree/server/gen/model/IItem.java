package com.mogree.server.gen.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

@javax.annotation.Generated(value = "class com.mogree.MogreeCodeGen", date = "2021-05-11T13:27:53.093+02:00")

public class IItem   {
  
  private String itemid = null;
  private Integer type = 0;

  /**
   **/
  public IItem itemid(String itemid) {
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
   * Itemtype=0
   **/
  public IItem type(Integer type) {
    this.type = type;
    return this;
  }

  
  @ApiModelProperty(example = "0", value = "Itemtype=0")
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
    IItem iItem = (IItem) o;
    return Objects.equals(itemid, iItem.itemid) &&
        Objects.equals(type, iItem.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(itemid, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IItem {\n");
    
    sb.append("    itemid: ").append(toIndentedString(itemid)).append("\n");
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

