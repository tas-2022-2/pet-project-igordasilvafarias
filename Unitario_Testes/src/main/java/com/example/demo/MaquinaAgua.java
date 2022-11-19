package com.example.demo;

public class MaquinaAgua {

	private int copos200;
	private int copos300;
	private int contador;

	public MaquinaAgua() {
		this.copos200 = 0;
		this.copos300 = 0;
		this.contador = 0;
	}

	// comandos

	public void abastecerAgua() {
		this.contador = 20000;
	}

	public void abastecerCopo200() {
		this.copos200 = 100;
	}

	public void abastecerCopo300() {
		this.copos300 = 100;
	}

	public void servirCopo200() {
		if (this.copos200 == 0) throw new IllegalArgumentException("nao há copo de 200 ml para atender o pedido, tente com o de 300 ml.\n");
		if (this.contador < 200) throw new IllegalArgumentException("agua insuficiente!\n");
		this.copos200--;
		this.contador -= 200;
	}

	public void servirCopo300() {
		if (this.copos300 == 0) throw new IllegalArgumentException("nao há copo de 300 ml para atender o pedido, tente com o de 200 ml.\n");
		if (this.contador < 300) throw new IllegalArgumentException("agua insuficiente!\n");
		this.copos300--;
		this.contador -= 300;
	}

	// consultas

	public int copos200() {
		return this.copos200;
	}

	public int copos300() {
		return this.copos300;
	}

	public int agua() {
		return this.contador;
	}

	
}