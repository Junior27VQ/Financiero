package com.krakedev.financierotestJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import com.krakedev.financiero.entidades.Cuenta;
import com.krakedev.financiero.servicios.Banco;

public class TestRetirarAI {
	private final double TOLERANCIA = 0.0001;

    @Test
    public void testRetiroExitoso() {
        // 1. Preparar el escenario: Cuenta con 500.0 de saldo
        Cuenta cuenta = new Cuenta("1001");
        cuenta.setSaldoActual(500.0);
        Banco banco = new Banco();
        
        // 2. Ejecutar: Retirar 200.0
        boolean resultado = banco.retirar(200.0, cuenta);
        
        // 3. Validar: Retorno true y saldo restante de 300.0
        assertTrue(resultado, "El retiro debería ser exitoso cuando hay fondos suficientes");
        assertEquals(300.0, cuenta.getSaldoActual(), TOLERANCIA, "El saldo actual debe haber disminuido en 200.0");
    }

    @Test
    public void testRetiroFondosInsuficientes() {
        // 1. Escenario: Cuenta con 100.0
        Cuenta cuenta = new Cuenta("1002");
        cuenta.setSaldoActual(100.0);
        Banco banco = new Banco();
        
        // 2. Ejecutar: Intentar retirar 150.0 (Más de lo que tiene)
        boolean resultado = banco.retirar(150.0, cuenta);
        
        // 3. Validar: Retorno false y el saldo NO debe cambiar
        assertFalse(resultado, "No se debe permitir el retiro si el monto supera el saldo actual");
        assertEquals(100.0, cuenta.getSaldoActual(), TOLERANCIA, "El saldo no debe verse afectado si el retiro falla");
    }

    @Test
    public void testRetiroMontoInvalido() {
        // 1. Escenario: Saldo de 100.0
        Cuenta cuenta = new Cuenta("1003");
        cuenta.setSaldoActual(100.0);
        Banco banco = new Banco();
        
        // 2. Ejecutar: Intentar retirar un valor negativo (-50.0)
        boolean resultado = banco.retirar(-50.0, cuenta);
        
        // 3. Validar: Retorno false y saldo intacto
        assertFalse(resultado, "El retiro de montos negativos no debe ser procesado");
        assertEquals(100.0, cuenta.getSaldoActual(), TOLERANCIA, "El saldo debe mantenerse igual");
    }

    @Test
    public void testRetiroLimiteExacto() {
        // 1. Escenario: Retirar exactamente todo lo que hay en la cuenta
        Cuenta cuenta = new Cuenta("1004");
        cuenta.setSaldoActual(80.0);
        Banco banco = new Banco();
        
        // 2. Ejecutar
        boolean resultado = banco.retirar(80.0, cuenta);
        
        // 3. Validar: Debe ser true y saldo quedar en 0
        assertTrue(resultado, "Debe permitir retirar el total exacto del saldo");
        assertEquals(0.0, cuenta.getSaldoActual(), TOLERANCIA, "La cuenta debería quedar en saldo cero");
    }

}
