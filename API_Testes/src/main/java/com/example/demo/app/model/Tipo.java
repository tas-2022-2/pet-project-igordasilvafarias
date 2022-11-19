package com.example.demo.app.model;

public enum Tipo {
    GERAL,      // 0
    CARNES,     // 1
    FRUTAS,     // 2
    SEMENTES,   // 3
    MADEIRAS;   // 4

    public static Tipo fromInteger(Integer idTipo) {
        try {
            return Tipo.values()[idTipo];
        } catch (Exception e) {
            return null;
        }
    }   

}
