/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package primerp;

/**
 *
 * @author glend
 */
public class Primerp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Vehiculo carro1 = new Vehiculo();

        Llanta llantaToyota = new Llanta("Michelin", 17, 35);
        carro1.marca = "Toyota";
        carro1.modelo = "Corolla";
        carro1.anio = 2025;
        //carro1.precio = 185000;
        carro1.setPrecio(1000);
        carro1.setLlanta(llantaToyota);
        carro1.mostrarInformacion();
        

        Llanta llantaJeep = new Llanta("BFGoodrich", 31, 32);
        Vehiculo carro2 = new Vehiculo(
         "Jeep", "Wrangler TJ", 2001, 75000,llantaJeep);
        //carro2.marca = "Jeep";
        //carro2.modelo = "Wrangler TJ";
        //carro2.anio = 2001;
        //carro2.precio = 75000;
        //carro2.setPrecio(45000);

        System.out.println("----------------");

        carro2.mostrarInformacion();

        Llanta llantaFord = new Llanta("Goodyear", 18, 34);
        Vehiculo carro3 = new Vehiculo("ford", "150", 2010, 75000, llantaFord);
        carro3.mostrarInformacion();
    }

}
