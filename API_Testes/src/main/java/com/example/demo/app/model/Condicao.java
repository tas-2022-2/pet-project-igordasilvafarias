package com.example.demo.app.model;

public enum Condicao {
    SUSPENSA,   // 0
    CANCELADA,  // 1
    RESERVADA,  // 2
    CONCLUIDA,  // 3
    DISPONIVEL; // 4

    public static Condicao inicial() {
        return Condicao.DISPONIVEL;
    }

}
