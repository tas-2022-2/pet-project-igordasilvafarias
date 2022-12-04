package com.example.demo.app.model;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

@Getter @Setter @ToString(onlyExplicitlyIncluded = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cargas")
public class Carga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Include
    Integer id;

    @Column(name = "descricao", nullable = false)
    @ToString.Include
    String descricao;

    @Column(name = "tipo", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    Tipo tipo;

    @Column(name = "condicao", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    Condicao condicao;

    @JoinColumn(name = "containers_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    Container armazenado;

}
