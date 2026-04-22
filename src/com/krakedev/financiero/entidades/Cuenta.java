package com.krakedev.financiero.entidades;

public class Cuenta {
	private String id;
	private double saldoActual;
	private String tipo;
	
	public Cuenta(String id) {
		super();
		this.id = id;
		this.saldoActual = 0;
		this.tipo = "A";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getSaldoActual() {
		return saldoActual;
	}

	public void setSaldoActual(double saldoActual) {
		this.saldoActual = saldoActual;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public void imprimir() {
		System.out.printf( " Id: %s"+
							" Saldo Actual: %.2f"+
							" Tipo:%s ",
							id,saldoActual,tipo);
	}
	

}
