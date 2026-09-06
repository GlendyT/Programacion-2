/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package primerp;

/**
 *
 * @author glend
 */
public class Vehiculo {

    String marca;
    String modelo;
    int anio;
    private double precio;
    private Llanta llanta;
    private Motor motor;
    String color;

    /*get: obtener,  set: asignar/colorcar/setear*/
    public String getModelo() {
        return this.modelo;
    }

    public String getMarca() {
        return marca;
    }

    public Llanta getLlanta() {
        return llanta;
    }

    public Motor getMotor() {
        return motor;
    }

    public void setPrecio(double nprecio) {
        if (nprecio > 0) {
            this.precio = nprecio;
        }
    }

    public double GetPrecio() {
        return this.precio;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        if (anio > 2010) {
            this.anio = anio;
        } else {
            this.anio = 0;
        }
    }

    public void setLlanta(Llanta nllanta) {
        this.llanta = nllanta;
    }
    
    

    public void setMotor(Motor nmotor) {
        this.motor = nmotor;
    }

    Vehiculo() {
        System.out.println("hola desde e constructor");
    }

    public Vehiculo(
            String marca,
            String modelo,
            int anio,
            double precio,
            String color,
            Llanta llanta) {

        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.precio = precio;
        this.color = color;
        this.llanta = llanta;
    }

    public Vehiculo(
            String marca,
            String modelo,
            int anio,
            double precio,
            String color,
            Llanta llanta,
            Motor motor) {

        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.precio = precio;
        this.llanta = llanta;
        this.color = color;
        this.motor = motor;
    }

    void mostrarInformacion() {
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Año: " + anio);
        System.out.println("Precio: Q" + precio);
        System.out.println("color" + color);
        System.out.println("Información de la llanta:");
        llanta.mostrarInformacion();
        if (motor != null) {
            System.out.println("Información del motor:");
            motor.mostrarInformacion();
        }

    }

}
