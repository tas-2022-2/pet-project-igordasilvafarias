package com.example.demo.controller.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(value = "Container", description = "Representaçao de um Container")
public class ContainerResponse {

  @ApiModelProperty
  UUID id;

  @ApiModelProperty(example = "MSCU7312323")
  String numero;

  @ApiModelProperty
  @JsonInclude(content = Include.NON_NULL)
  String tipo;

  @ApiModelProperty
  String tamanho;

}
