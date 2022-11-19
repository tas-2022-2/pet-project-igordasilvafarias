package com.example.demo.app.services.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CargaRequest {
    
    @ApiModelProperty(
        name = "descricao",
        example = "Frango Congelado",
        required = true
    )
    String descricao;

    @ApiModelProperty(
        name = "idTipo",
        hidden = true
    )
    Integer IdTipo;

}
