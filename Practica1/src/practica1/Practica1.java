package practica1;

public class Practica1 {
    public static void main(String[] args) {
        Proveedor proveedor = new Proveedor(1, "Distribuidora XYZ", "1234567-8", "555-1234");
        
        Factura factura1 = new Factura(101, "08/08/2026", proveedor);
        proveedor.agregarFactura(factura1);
        
        Articulo articulo1 = new Articulo(10, "Laptop", 1500.00);
        Articulo articulo2 = new Articulo(11, "Mouse", 25.50);
        
        DetalleFactura detalle1 = new DetalleFactura(2, articulo1.getPrecio(), articulo1);
        DetalleFactura detalle2 = new DetalleFactura(5, articulo2.getPrecio(), articulo2);
        
        factura1.agregarDetalle(detalle1);
        factura1.agregarDetalle(detalle2);
        
        System.out.println("Proveedor: " + proveedor.getNombre());
        System.out.println("Total de Factura: $" + factura1.getTotal());
    }
}
