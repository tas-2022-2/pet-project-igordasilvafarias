package com.example.demo.app.model;

import java.util.UUID;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
@Table(name = "containers")

public class Container {
    
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "numero", length = 11, nullable = false, unique = true)
    private String numero; 

    @Column(name = "tipo", length = 100, nullable = false)
    private String tipo;

    @Column(name = "tamanho", length = 4, nullable = false)
    private String tamanho;

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

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
        return "Conteiner: id = " + id + ", Numero = " + numero + ", Tipo = " + tipo + ", Tamanho = " + tamanho; 
    }

}
