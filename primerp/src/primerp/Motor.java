/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package primerp;

/**
 *
 * @author glend
 */
public class Motor {

    private String tipo;        // Gasolina, Diesel, Electrico, Hibrido
    private int cilindrada;     // en centimetros cubicos (cc)

    // Constructor vacío
    public Motor() {
    }

    // Constructor con parámetros
    public Motor(String tipo, int cilindrada) {
        this.tipo = tipo;
        this.cilindrada = cilindrada;
    }

    // Getters
    public String getTipo() {
        return tipo;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    // Setters
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCilindrada(int cilindrada) {
        if (cilindrada > 0) {
            this.cilindrada = cilindrada;
        }
    }

    // Mostrar información del motor
    public void mostrarInformacion() {
        System.out.println("Tipo de motor: " + tipo);
        System.out.println("Cilindrada: " + cilindrada + " cc");
    }
}
