package EjercicioUnidadIntegracion;

// CARRITO
import java.util.*;

public class Carrito {
    private List<Producto> items = new ArrayList<>();
    private final InventarioService inventario;
    private final ProcesadorPago procesador;

    public Carrito(InventarioService inventario, ProcesadorPago procesador) {
        this.inventario = inventario;
        this.procesador = procesador;
    }

    public void agregarProducto(Producto p) {
        if (inventario.hayStock(p.id())) {
            items.add(p);
        } else {
            throw new IllegalStateException("No hay stock del producto: " + p.nombre());
        }
    }

    public double calcularTotalConIVA() {
        double subtotal = items.stream().mapToDouble(Producto::precio).sum();
        return subtotal * 1.21; // Aplicamos 21% de IVA
    }

    public boolean procesarCompra(String tarjeta) {
        if (items.isEmpty()) return false;
        double total = calcularTotalConIVA();
        return procesador.pagar(tarjeta, total);
    }
}

// Producto
public record Producto(String id, String nombre, double precio) {}

//Interfaces
interface InventarioService { boolean hayStock(String productoId); }
interface ProcesadorPago { boolean pagar(String tarjeta, double monto); }
