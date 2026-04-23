package com.krakedev.financierotestJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import com.krakedev.financiero.entidades.Cliente;
import com.krakedev.financiero.entidades.Cuenta;
import com.krakedev.financiero.servicios.Banco;

public class TestCrearCuentaAI {
	@Test
    public void testCrearPrimeraCuenta() {
        // 1. Preparar el escenario
        Banco banco = new Banco();
        Cliente cliente = new Cliente("1722334455", "Juan", "Perez");
        
        // 2. Ejecutar el método: El código inicial es 1000, la primera cuenta debe ser 1001
        Cuenta cuentaObtenida = banco.crearCuenta(cliente);
        
        // 3. Validaciones
        assertNotNull(cuentaObtenida, "La cuenta retornada no debe ser nula");
        assertEquals("1001", cuentaObtenida.getId(), "El primer código generado debe ser 1001");
        assertEquals(1001, banco.getUltimoCodigo(), "El estado del banco debe actualizarse a 1001");
        assertEquals(cliente, cuentaObtenida.getPropietario(), "El cliente debe ser el propietario de la cuenta");
    }

    @Test
    public void testCrearCuentasConsecutivas() {
        // 1. Preparar banco y clientes
        Banco banco = new Banco();
        Cliente cliente1 = new Cliente("1234567890", "Ana", "Díaz");
        Cliente cliente2 = new Cliente("0987654321", "Luis", "Mera");
        
        // 2. Crear dos cuentas seguidas
        Cuenta cuenta1 = banco.crearCuenta(cliente1);
        Cuenta cuenta2 = banco.crearCuenta(cliente2);
        
        // 3. Validar consecutividad
        assertEquals("1001", cuenta1.getId(), "La primera cuenta debe ser 1001");
        assertEquals("1002", cuenta2.getId(), "La segunda cuenta debe ser 1002");
        assertEquals(1002, banco.getUltimoCodigo(), "El último código en el banco debe ser 1002");
    }

    @Test
    public void testCrearCuentaConCodigoModificado() {
        // 1. Preparar escenario
        Banco banco = new Banco();
        Cliente cliente = new Cliente("1122334455", "Maria", "Lopez");
        
        // 2. Forzar el inicio del contador mediante el setter (si está disponible)
        banco.setUltimoCodigo(5000);
        
        // 3. Crear cuenta
        Cuenta cuenta = banco.crearCuenta(cliente);
        
        // 4. Validar que se sumó 1 al valor actual
        assertEquals("5001", cuenta.getId(), "Si el último era 5000, el nuevo debe ser 5001");
        assertEquals(5001, banco.getUltimoCodigo());
    }

    @Test
    public void testIntegridadDatosClienteEnCuenta() {
        // Validar que los datos del cliente se mantienen correctamente en la cuenta generada
        Banco banco = new Banco();
        Cliente cliente = new Cliente("1755667788", "Pedro", "Navaja");
        
        Cuenta cuenta = banco.crearCuenta(cliente);
        
        assertNotNull(cuenta.getPropietario());
        assertEquals("1755667788", cuenta.getPropietario().getCedula());
        assertEquals("Pedro", cuenta.getPropietario().getNombre());
    }

}
