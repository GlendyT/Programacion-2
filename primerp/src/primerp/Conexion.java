/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package primerp;

import java.sql.Connection;  //nos sirve para conectar 
import java.sql.DriverManager;//se encarga de solicitar la conexión al driver JDBC. 
import java.sql.SQLException; //permite manejar errores relacionados con la base de datos.
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

/**
 *
 * @author glend
 */
public class Conexion {

    public static Connection conectar() {
        Connection conexion = null;

        String url = "jdbc:oracle:thin:@localhost:1521:umg";
        String usuario = "system";
        String password = "Umg$2026";

        try {

            conexion = DriverManager.getConnection(
                    url,
                    usuario,
                    password
            );
            System.out.println("Conexión realizada correctamente");

        } catch (SQLException e) {

            System.out.println(
                    "Error de conexión: " + e.getMessage()
            );
        }

        return conexion;
    }

    public ArrayList<Vehiculo> mostrarVehiculos() {

        String sql = "select * from vehiculo ";
        ArrayList<Vehiculo> listVehiculos = new ArrayList<>();

        try {
            Connection c = conectar();
            Statement statment = c.createStatement();
            ResultSet resultado = statment.executeQuery(sql);

            // DEBUG: imprimir nombres de columnas reales
            ResultSetMetaData meta = resultado.getMetaData();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                System.out.println("Columna " + i + ": " + meta.getColumnName(i));
            }

            while (resultado.next()) {

                int id = resultado.getInt("ID_VEHICULO");
                String marca = resultado.getString("MARCA");
                String modelo = resultado.getString("MODELO");
                int anio = resultado.getInt("ANIO");
                String color = resultado.getString("color");
                double precio = resultado.getDouble("precio");

                Vehiculo carro = new Vehiculo(marca, modelo, anio, precio, color);
                listVehiculos.add(carro);
                
                
                System.out.println("------------------------");
                System.out.println("ID: " + id);
                System.out.println("Marca: " + marca);
                System.out.println("Modelo: " + modelo);
                System.out.println("Año: " + anio);
                System.out.println("Color: " + color);
            }

            System.out.println("r:" + resultado.getString("Modelo"));
            System.out.println("r:" + resultado.getString("Marca"));

        } catch (SQLException e) {
            System.out.println("error" + e.getMessage());
        }
        return listVehiculos;
    }
}
