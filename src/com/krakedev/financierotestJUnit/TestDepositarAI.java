package com.krakedev.financierotestJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import com.krakedev.financiero.entidades.Cuenta;
import com.krakedev.financiero.servicios.Banco;

public class TestDepositarAI {
	private final double TOLERANCIA = 0.0001;

    @Test
    public void testDepositarExitoso() {
        // 1. Preparar cuenta con saldo inicial 100.0
        Cuenta cuenta = new Cuenta("1001");
        cuenta.setSaldoActual(100.0);
        Banco banco = new Banco();
        
        // 2. Depositar 50.0
        boolean resultado = banco.depositar(50.0, cuenta);
        
        // 3. Validar: Debe retornar true y el saldo final debe ser 150.0
        assertTrue(resultado, "El depósito de un monto positivo debe ser exitoso");
        assertEquals(150.0, cuenta.getSaldoActual(), TOLERANCIA, "El saldo debería ser la suma del anterior más el depósito");
    }

    @Test
    public void testDepositarMontoCero() {
        // 1. Preparar cuenta con saldo inicial 100.0
        Cuenta cuenta = new Cuenta("1001");
        cuenta.setSaldoActual(100.0);
        Banco banco = new Banco();
        
        // 2. Intentar depositar 0
        boolean resultado = banco.depositar(0.0, cuenta);
        
        // 3. Validar: Debe retornar false y el saldo no debe cambiar
        assertFalse(resultado, "El depósito de 0 no debe ser permitido");
        assertEquals(100.0, cuenta.getSaldoActual(), TOLERANCIA, "El saldo no debe cambiar si el depósito falla");
    }

    @Test
    public void testDepositarMontoNegativo() {
        // 1. Preparar cuenta con saldo inicial 100.0
        Cuenta cuenta = new Cuenta("1001");
        cuenta.setSaldoActual(100.0);
        Banco banco = new Banco();
        
        // 2. Intentar depositar un valor negativo
        boolean resultado = banco.depositar(-20.0, cuenta);
        
        // 3. Validar: Debe retornar false y el saldo debe permanecer intacto
        assertFalse(resultado, "El depósito de un monto negativo no debe ser permitido");
        assertEquals(100.0, cuenta.getSaldoActual(), TOLERANCIA, "El saldo debe mantenerse sin cambios tras un intento fallido");
    }

    @Test
    public void testDepositarDesdeSaldoCero() {
        // 1. Preparar cuenta vacía
        Cuenta cuenta = new Cuenta("1002");
        cuenta.setSaldoActual(0.0);
        Banco banco = new Banco();
        
        // 2. Realizar depósito
        boolean resultado = banco.depositar(25.50, cuenta);
        
        // 3. Validar
        assertTrue(resultado);
        assertEquals(25.50, cuenta.getSaldoActual(), TOLERANCIA);
    }

}
