import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculadoraEnviosTest {

    CalculadoraEnvios calculadora = new CalculadoraEnvios();

    // --- CAJA NEGRA (Basados en requisitos) ---

    @Test
    void testCajaNegra_CortoAlcance() {
        // Requisito: Base 10€
        assertEquals(10, calculadora.calcularCoste(20, false));
    }

    @Test
    void testCajaNegra_LargoAlcance() {
        // Requisito: Base 10€ + 5€ por distancia > 50
        assertEquals(15, calculadora.calcularCoste(100, false));
    }

    @Test
    void testCajaNegra_Frontera() {
        // Probamos el límite exacto. 
        // 50km NO debería sumar 5€ (es > 50, no >= 50)
        assertEquals(10, calculadora.calcularCoste(50, false), "Frontera 50km fallida");
        
        // 51km SÍ debería sumar 5€
        assertEquals(15, calculadora.calcularCoste(51, false), "Frontera 51km fallida");
    }

    // --- CAJA BLANCA (Basados en estructura del código) ---

    @Test
    void testCajaBlanca_RamaUrgente() {
        // Objetivo: línea 27 (coste * 2)
        // 10€ base * 2 = 20€
        assertEquals(20, calculadora.calcularCoste(10, true));
    }

    @Test
    void testCajaBlanca_Excepcion() {
        // Objetivo: Cubrir la línea 10 (throw exception)
        assertThrows(IllegalArgumentException.class, () -> {
            calculadora.calcularCoste(-5, false);
        });
    }

    @Test
    void testCajaBlanca_Complejo_CodigoOculto() {
        // Un usuario normal no sabría que el 777 es gratis.
        // Esto no tiene sentido es por probar.
        assertEquals(0, calculadora.calcularCoste(777, false), "El código oculto 777 ");
    }

    /* 
        Camino 1: Cerca + Normal (Falso, Falso)
        Camino 2: Lejos + Normal (Verdadero, Falso)
        Camino 3: Cerca + Urgente (Falso, Verdadero)
        Camino 4: Lejos + Urgente (Verdadero, Verdadero)
    */

    @Test
    void testCajaBlanca_Complejo_CoberturaTotal() {
        // Entrar en el IF de distancia (>50) Y entrar en el IF de urgencia (true).
        
        // Coste esperado: 
        // 1. Base = 10€
        // 2. +5€ por distancia = 15€
        // 3. * 2 por urgencia = 30€
        
        double resultado = calculadora.calcularCoste(100, true);
        
        assertEquals(30, resultado, "Fallo al combinar distancia y urgencia (Camino 4)");
    }

}