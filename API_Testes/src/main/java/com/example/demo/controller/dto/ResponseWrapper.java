package com.example.demo.controller.dto;

//import org.hibernate.event.internal.WrapVisitor;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "ServerResponse")
public class ResponseWrapper<T, M> {
  
  @ApiModelProperty(value = "data")
  private final T data;

  @ApiModelProperty(value = "meta")
  private final M meta;

  public static <T, M> ResponseWrapper<T, M> wrap(T response, M meta) {
    return new ResponseWrapper<T,M>(response, meta);
  }

  public static <T, M> ResponseWrapper<T, M> wrap(T response) {
    return new ResponseWrapper<T,M>(response, null);
  }

  /*
   * class PageResponse<T> extends ResponseWrapper<T, Page> {
   * public PageResponse(T data, Page meta) {
   * super(data, meta);
   * }
   * }
   * class Page {
   * Integer offset;
   * Integer limit;
   * Integer count;
   * boolean isLastPage;
   * }
   */

}
