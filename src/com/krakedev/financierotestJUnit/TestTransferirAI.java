package com.krakedev.financierotestJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import com.krakedev.financiero.entidades.Cuenta;
import com.krakedev.financiero.servicios.Banco;

public class TestTransferirAI {
	private final double TOLERANCIA = 0.0001;

    @Test
    public void testTransferenciaExitosa() {
        // 1. Escenario: Cuenta origen con 200.0 y destino con 50.0
        Banco banco = new Banco();
        Cuenta origen = new Cuenta("1001");
        origen.setSaldoActual(200.0);
        
        Cuenta destino = new Cuenta("1002");
        destino.setSaldoActual(50.0);
        
        // 2. Ejecutar: Transferir 120.0
        boolean resultado = banco.transferir(origen, destino, 120.0);
        
        // 3. Validar: Retorno true, origen queda con 80 y destino con 170
        assertTrue(resultado, "La transferencia debería ser exitosa");
        assertEquals(80.0, origen.getSaldoActual(), TOLERANCIA, "La cuenta de origen debe haber restado el monto");
        assertEquals(170.0, destino.getSaldoActual(), TOLERANCIA, "La cuenta de destino debe haber sumado el monto");
    }

    @Test
    public void testTransferenciaFondosInsuficientes() {
        // 1. Escenario: Origen tiene menos de lo que quiere transferir
        Banco banco = new Banco();
        Cuenta origen = new Cuenta("1001");
        origen.setSaldoActual(30.0);
        
        Cuenta destino = new Cuenta("1002");
        destino.setSaldoActual(100.0);
        
        // 2. Ejecutar: Intentar transferir 50.0
        boolean resultado = banco.transferir(origen, destino, 50.0);
        
        // 3. Validar: Retorno false y los saldos permanecen intactos
        assertFalse(resultado, "La transferencia no debe realizarse por falta de fondos");
        assertEquals(30.0, origen.getSaldoActual(), TOLERANCIA, "El saldo de origen no debe cambiar");
        assertEquals(100.0, destino.getSaldoActual(), TOLERANCIA, "El saldo de destino no debe cambiar");
    }

    @Test
    public void testTransferenciaMontoInvalido() {
        // 1. Escenario: Intentar transferir un monto negativo
        Banco banco = new Banco();
        Cuenta origen = new Cuenta("1001");
        origen.setSaldoActual(100.0);
        
        Cuenta destino = new Cuenta("1002");
        destino.setSaldoActual(100.0);
        
        // 2. Ejecutar: Transferir -10.0
        boolean resultado = banco.transferir(origen, destino, -10.0);
        
        // 3. Validar
        assertFalse(resultado, "No se deben permitir transferencias de montos negativos");
        assertEquals(100.0, origen.getSaldoActual(), TOLERANCIA);
        assertEquals(100.0, destino.getSaldoActual(), TOLERANCIA);
    }

    @Test
    public void testTransferenciaLimiteExacto() {
        // 1. Escenario: Transferir exactamente todo el saldo disponible
        Banco banco = new Banco();
        Cuenta origen = new Cuenta("1001");
        origen.setSaldoActual(75.50);
        
        Cuenta destino = new Cuenta("1002");
        destino.setSaldoActual(0.0);
        
        // 2. Ejecutar
        boolean resultado = banco.transferir(origen, destino, 75.50);
        
        // 3. Validar: Origen debe quedar en 0 y destino con el total
        assertTrue(resultado);
        assertEquals(0.0, origen.getSaldoActual(), TOLERANCIA);
        assertEquals(75.50, destino.getSaldoActual(), TOLERANCIA);
    }

}
