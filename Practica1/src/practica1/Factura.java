package practica1;

import java.util.ArrayList;
import java.util.List;

public class Factura {
    private int numero;
    private String fecha;
    private double total;
    
    // Relación de composición: 1 Factura contiene 1..* DetalleFactura
    private List<DetalleFactura> detalles;
    
    // Relación inversa (opcional dependiendo del diseño, pero útil): 1 Proveedor tiene 0..* Facturas
    private Proveedor proveedor;

    public Factura(int numero, String fecha, Proveedor proveedor) {
        this.numero = numero;
        this.fecha = fecha;
        this.proveedor = proveedor;
        this.detalles = new ArrayList<>();
        this.total = 0.0;
    }

    public void agregarDetalle(DetalleFactura detalle) {
        this.detalles.add(detalle);
        this.total = calcularTotal();
    }

    public double calcularTotal() {
        double sumaTotal = 0;
        for (DetalleFactura detalle : detalles) {
            sumaTotal += detalle.calcularSubtotal();
        }
        return sumaTotal;
    }
    
    public double getTotal() {
        return total;
    }
}
