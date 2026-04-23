package com.krakedev.financiero.servicios;

import com.krakedev.financiero.entidades.Cliente;
import com.krakedev.financiero.entidades.Cuenta;

public class Banco {
	private int ultimoCodigo=1000;

	public int getUltimoCodigo() {
		return ultimoCodigo;
	}

	public void setUltimoCodigo(int ultimoCodigo) {
		this.ultimoCodigo = ultimoCodigo;
	}
	
	public Cuenta crearCuenta(Cliente cliente) {
		this.ultimoCodigo++;
		String codigoStr=ultimoCodigo+"";
		Cuenta nuevaCuenta=new Cuenta(codigoStr);
		nuevaCuenta.setPropietario(cliente);
		return nuevaCuenta;
	}

}
