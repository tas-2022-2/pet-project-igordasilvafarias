package com.example.demo.app.services.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class CondicaoRequest {

  @ApiModelProperty(allowableValues = "SUSPENSA, CANCELADA, RESERVADA, CONCLUIDA, DISPONIVEL")
  String condicao;
  
}
