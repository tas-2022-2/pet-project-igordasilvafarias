package com.example.demo.negocio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.demo.MaquinaAgua;

class MaquinaAguaTest {
  @Test
  @DisplayName("Nova instância deve conter 0ml para os copos de 200ml, 300ml e quantidade de agua.")
  void testeNovaInstanciaSemArgumentosDeveConsiderarZeroMlParaOsCoposQtdAgua() {
    MaquinaAgua maquina = new MaquinaAgua();

    Assertions.assertTrue(maquina != null);
    Assertions.assertNotNull(maquina);

    Assertions.assertEquals(0, maquina.agua(), "quantidade de agua é zero");
    Assertions.assertEquals(0, maquina.copos200(), "quantidade de copos de 200 é zero");
    Assertions.assertEquals(0, maquina.copos300(), "quantidade de copos de 300 é zero");

    System.out.println(maquina.agua() == 0); 
    System.out.println(maquina.copos200() == 0);
    System.out.println(maquina.copos300() == 0);
  }

  @Test
  @DisplayName("Servir um copo de 200ml sem copos na maquina retorna exceção")
  void testeServirCopo200mlComMaquinaVaziaNaoDeveServirAguaDeveLancarIllegalArgumentException() {
    MaquinaAgua maquina = new MaquinaAgua();
    try {
      maquina.servirCopo200();
    } catch (IllegalArgumentException e) {
      Assertions.assertEquals("nao há copo de 200 ml para atender o pedido, tente com o de 300 ml.\n", e.getMessage());
    }
  }

  @Test
  @DisplayName("Abastecer maquina com água, depois deve conter 20000ml de água")
  void testeAbastecerMaquinaDepoisDeveConter20000mlAgua() {
    MaquinaAgua maquina = new MaquinaAgua();

    maquina.abastecerAgua();

    Assertions.assertEquals(20000, maquina.agua(), "quantidade de agua é 20000");
    Assertions.assertEquals(0, maquina.copos200(), "quantidade de copos de 200 é zero");
    Assertions.assertEquals(0, maquina.copos300(), "quantidade de copos de 300 é zero");

    System.out.println(maquina.agua() == 20000);
    System.out.println(maquina.copos200() == 0);
    System.out.println(maquina.copos300() == 0);
  }

  @Test
  @DisplayName("Servir um copo de 200ml sem agua na maquina retorna exceção")
  void testeServirCopo200mlComMaquinaMasSemAguaNaoDeveServirAguaDeveLancarIllegalArgumentException() {
    MaquinaAgua maquina = new MaquinaAgua();
    maquina.abastecerCopo200();
    try {
      maquina.servirCopo200();
    } catch (IllegalArgumentException e) {
      Assertions.assertEquals("agua insuficiente!\n", e.getMessage());
    }
  }

  @Test
  @DisplayName("Servir um copo de 300ml sem agua na maquina retorna exceção")
  void testeServirCopo300mlComMaquinaMasSemAguaNaoDeveServirAguaDeveLancarIllegalArgumentException() {
    MaquinaAgua maquina = new MaquinaAgua();
    maquina.abastecerCopo300();

    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      maquina.servirCopo300();
    });

    Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> {
      maquina.servirCopo200();
    }, () -> {
      return "agua insuficiente!\n";
    });
  }

  @Test
  @DisplayName("Abastecer maquina com copo de 200ml, depois deve conter 100 copos de 200 ml")
  void testeAbastecerMaquinaComCopos200mlDepoisDeveConter100Copos200ml() {
    MaquinaAgua maquina = new MaquinaAgua();

    maquina.abastecerCopo200();

    Assertions.assertEquals(0, maquina.agua(), "quantidade de agua é zero");
    Assertions.assertEquals(100, maquina.copos200(), "quantidade de copos de 200 é 100");
    Assertions.assertEquals(0, maquina.copos300(), "quantidade de copos de 300 é zero");

    System.out.println(maquina.agua() == 0);
    System.out.println(maquina.copos200() == 100);
    System.out.println(maquina.copos300() == 0);
  }

  @Test
  @DisplayName("Abastecer maquina com copo de 300ml, depois deve conter 100 copos de 300 ml")
  void testeAbastecerMaquinaComCopos00mlDepoisDeveConter100Copos300ml() {
    MaquinaAgua maquina = new MaquinaAgua();

    maquina.abastecerCopo300();

    Assertions.assertEquals(0, maquina.agua(), "quantidade de agua é zero");
    Assertions.assertEquals(0, maquina.copos200(), "quantidade de copos de 200 é 0");
    Assertions.assertEquals(100, maquina.copos300(), "quantidade de copos de 300 é 100");

    System.out.println(maquina.agua() == 0);
    System.out.println(maquina.copos200() == 0);
    System.out.println(maquina.copos300() == 100);
  }

  @Test
  @DisplayName("Abastecer maquina com todas opções, depois deve conter 100un de cada copo 200 e 300 ml e 20000ml de agua")
  void testeAbastecerMaquinaComTodasOpcoesDepoisDeveConter100Copos300mlE200mlE20000mlAgua() {
    MaquinaAgua maquina = new MaquinaAgua();

    maquina.abastecerAgua();
    maquina.abastecerCopo200();
    maquina.abastecerCopo300();

    Assertions.assertEquals(20000, maquina.agua(), "quantidade de agua é 20000");
    Assertions.assertEquals(100, maquina.copos200(), "quantidade de copos de 200 é 100");
    Assertions.assertEquals(100, maquina.copos300(), "quantidade de copos de 300 é 100");

    System.out.println(maquina.agua() == 20000);
    System.out.println(maquina.copos200() == 100);
    System.out.println(maquina.copos300() == 100);
  }

  @Test
  @DisplayName("Servir 5 copos de 200ml, deve conter apos servir 19000ml de agua e 95 copos de 200ml")
  void testeServirCincoCopos200mlDeveConterDepois19000mlAgua95Copos200ml() {
    MaquinaAgua maquina = new MaquinaAgua();

    maquina.abastecerAgua();
    maquina.abastecerCopo200();
    maquina.abastecerCopo300();

    for (int i = 0; i < 5; i++) maquina.servirCopo200();

    Assertions.assertEquals(19000, maquina.agua(), "quantidade de agua é 19000");
    Assertions.assertEquals(95, maquina.copos200(), "quantidade de copos de 200 é 95");
    Assertions.assertEquals(100, maquina.copos300(), "quantidade de copos de 300 é 100");

    System.out.println(maquina.agua() == 19000);
    System.out.println(maquina.copos200() == 95);
    System.out.println(maquina.copos300() == 100);
  }

  @Test
  @DisplayName("Servir 99 copos de 200ml e servir um de 300ml, deve retorna exceção que nao possui agua sufuciente")
  void testeServir99Copos200mlEum300mlDeveRetornarExcecaoAguaInsuficiente() {
    MaquinaAgua maquina = new MaquinaAgua();

    maquina.abastecerAgua();
    maquina.abastecerCopo200();
    maquina.abastecerCopo300();
      
      try {
        for (int i = 0; i < 99; i++)
          maquina.servirCopo200();
        maquina.servirCopo300();
    } catch (IllegalArgumentException e) {
      Assertions.assertEquals("agua insuficiente!\n", e.getMessage());
    }

    Assertions.assertEquals(200, maquina.agua(), "quantidade de agua é 200");
    Assertions.assertEquals(1, maquina.copos200(), "quantidade de copos de 200 é 1");
    Assertions.assertEquals(100, maquina.copos300(), "quantidade de copos de 300 é 100");

    System.out.println(maquina.agua() == 200);
    System.out.println(maquina.copos200() == 1);
    System.out.println(maquina.copos300() == 100);
  }

  @Test
  @DisplayName("Servir 66 copos de 300ml e servir um de 300ml, deve retorna 0 de agua 99 copos de 200ml e 34 300ml")
  void testeServir66Copos300mlEum200mlDeveConterZeroAgua99Copos200e34De300() {
    MaquinaAgua maquina = new MaquinaAgua();

    maquina.abastecerAgua();
    maquina.abastecerCopo200();
    maquina.abastecerCopo300();

    try {
      for (int i = 0; i < 66; i++)
        maquina.servirCopo300();
      maquina.servirCopo200();
    } catch (IllegalArgumentException e) {
      Assertions.assertEquals("agua insuficiente!\n", e.getMessage());
    }

    Assertions.assertEquals(0, maquina.agua(), "quantidade de agua é 0");
    Assertions.assertEquals(99, maquina.copos200(), "quantidade de copos de 200 é 99");
    Assertions.assertEquals(34, maquina.copos300(), "quantidade de copos de 300 é 34");

    System.out.println(maquina.agua() == 0);
    System.out.println(maquina.copos200() == 99);
    System.out.println(maquina.copos300() == 34);
  }

}
