package com.example.demo.app.services.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.validation.annotation.Validated;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Validated
@ApiModel(value = "Novo Container")
public class ContainerRequest {

    @ApiModelProperty(
        name = "numero",
        example = "MSCU7327327",
        notes = "Deve ter os 4 primeiros digitos caracteres de letras, e os ultimos 7 sao numeros.",
        required = true
    )
    @NotNull(message = "O numero eh obrigatorio")
    @Pattern(regexp = "^\\w{4}\\d{7}$", message = "Numero Formato invalido, ex:MSCU7327327")
    private String numero;

    @ApiModelProperty(
        name = "tipo", 
        example = "Refrigerated ISO Containers (Reefer), Dry Storage Container", 
        notes = "Deve pelo menos conter 4 caracteres",
        required = true
    )
    @NotNull(message = "O tipo eh obrigatorio")
    @Pattern(regexp = "^\\w{4,}.*", message = "Tipo invalido: ter no minimo mais de 4 letras.")
    private String tipo;
    
    @ApiModelProperty(name = "tamanho", 
        example = "40RH, 20RH, 40HC, 20HC", 
        notes = "Deve ter os 2 primeiros digitos com numeros, e os ultimos 2 sao letras.", 
        required = true)
    @NotNull(message = "O tamanho eh obrigatorio")
    @Pattern(regexp = "^\\d{2}\\w{2}$", message = "Tamanho Formato invalido, ex:40RH")
    private String tamanho;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    @Override
    public String toString() {
        return "Conteiner: Numero = " + numero + ", Tipo = " + tipo + ", Tamanho = " + tamanho;
    }

}
