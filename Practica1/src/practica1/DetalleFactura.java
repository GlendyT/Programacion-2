package practica1;

public class DetalleFactura {
    private int cantidad;
    private double precio;
    private double subtotal;
    
    // Relación: 0..* DetalleFactura corresponde a 1 Articulo
    private Articulo articulo;

    public DetalleFactura(int cantidad, double precio, Articulo articulo) {
        this.cantidad = cantidad;
        this.precio = precio;
        this.articulo = articulo;
        this.subtotal = calcularSubtotal();
    }

    public double calcularSubtotal() {
        return this.cantidad * this.precio;
    }

    public Articulo getArticulo() {
        return articulo;
    }
    
    public double getSubtotal() {
        return subtotal;
    }
}
