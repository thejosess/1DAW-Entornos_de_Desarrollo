import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CarritoSolucionTest {

    // --- TEST DE UNIDAD ---
    @Test
    @DisplayName("Unidad: El IVA (21%) se calcula correctamente sobre el subtotal")
    void testCalculoIVA() {
        InventarioService invMock = mock(InventarioService.class);
        ProcesadorPago pagoMock = mock(ProcesadorPago.class);
        when(invMock.hayStock(anyString())).thenReturn(true); // Forzamos stock disponible
        
        Carrito carrito = new Carrito(invMock, pagoMock);
        carrito.agregarProducto(new Producto("P1", "Monitor", 100.0));
        carrito.agregarProducto(new Producto("P2", "Teclado", 50.0));

        double totalConIVA = carrito.calcularTotalConIVA();

        // Subtotal: 150.0 -> IVA (21%): 31.5 -> Total: 181.5
        assertEquals(181.5, totalConIVA, "El cálculo del IVA debería ser exacto");
    }

    // --- TEST DE INTEGRACIÓN ---
    @Test
    @DisplayName("Integración: Carrito + Procesador de Pago colaboran en pago fallido")
    void testFlujoPagoRechazado() {
        InventarioService inventarioReal = id -> true; 
        ProcesadorPago procesadorFalla = (tarjeta, monto) -> false; // Simula tarjeta rechazada

        Carrito carrito = new Carrito(inventarioReal, procesadorFalla);
        carrito.agregarProducto(new Producto("P1", "Ratón", 20.0));

        boolean resultado = carrito.procesarCompra("4545-0000-0000-0000");

        assertFalse(resultado, "La compra debería fallar si el procesador de pago rechaza la tarjeta");
    }
}

// --- TEST EDGE CASE ---
@Test
    @DisplayName("Edge Case: No se puede procesar una compra si el carrito no tiene productos")
    void testCompraCarritoVacio() {
        InventarioService invMock = mock(InventarioService.class);
        ProcesadorPago pagoMock = mock(ProcesadorPago.class);
        Carrito carrito = new Carrito(invMock, pagoMock);

        boolean resultado = carrito.procesarCompra("1234-5678");

        // Assert
        assertFalse(resultado, "No se debe permitir procesar una compra sin ítems");
        
        //El procesador de pago nunca debió ser llamado
        verify(pagoMock, never()).pagar(anyString(), anyDouble());
    }