package practica1;

public class Articulo {
    private int codigo;
    private String nombre;
    private double precio;

    public Articulo(int codigo, String nombre, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }
}
