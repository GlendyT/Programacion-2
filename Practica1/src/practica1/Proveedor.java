package practica1;

import java.util.ArrayList;
import java.util.List;

public class Proveedor {
    private int codigo;
    private String nombre;
    private String nit;
    private String telefono;
    
    // Relación: 1 Proveedor tiene 0..* Facturas
    private List<Factura> facturas;

    public Proveedor(int codigo, String nombre, String nit, String telefono) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.nit = nit;
        this.telefono = telefono;
        this.facturas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getNit() {
        return nit;
    }

    public void agregarFactura(Factura factura) {
        this.facturas.add(factura);
    }
    
    public List<Factura> getFacturas() {
        return facturas;
    }
}
