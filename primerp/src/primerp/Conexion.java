/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package primerp;

/*
import java.sql.Connection;  //nos sirve para conectar 
import java.sql.DriverManager;//se encarga de solicitar la conexión al driver JDBC. 
import java.sql.SQLException; //permite manejar errores relacionados con la base de datos.
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;*/

import java.sql.*;
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

                Vehiculo carro = new Vehiculo(id, marca, modelo, anio, precio, color);
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

    public void insertarVehiculo(
            int id,
            String marca,
            String modelo,
            int anio,
            double precio, String color) {

        String sql = "INSERT INTO vehiculo "
                + "(ID_VEHICULO, MARCA, MODELO, ANIO, PRECIO, COLOR) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try {

            Connection conexion = Conexion.conectar();

            PreparedStatement ps
                    = conexion.prepareStatement(sql);

            ps.setInt(1, id);
            ps.setString(2, marca);
            ps.setString(3, modelo);
            ps.setInt(4, anio);
            ps.setDouble(5, precio);
            ps.setString(6, color);

            ps.executeUpdate();

            System.out.println(
                    "Vehículo insertado correctamente"
            );

        } catch (SQLException e) {

            System.out.println(
                    "Error al insertar: " + e.getMessage()
            );
        }
    }

    public void actualizarVehiculo(
            int id,
            String marca,
            String modelo,
            int anio,
            double precio) {

        String sql = "UPDATE vehiculo "
                + "SET MARCA = ?, "
                + "MODELO = ?, "
                + "ANIO = ?, "
                + "PRECIO = ? "
                + "WHERE ID_VEHICULO = ?";

        try {

            Connection conexion = Conexion.conectar();

            PreparedStatement ps
                    = conexion.prepareStatement(sql);

            ps.setString(1, marca);
            ps.setString(2, modelo);
            ps.setInt(3, anio);
            ps.setDouble(4, precio);
            ps.setInt(5, id);

            ps.executeUpdate();

            System.out.println(
                    "Vehículo actualizado correctamente"
            );

        } catch (SQLException e) {

            System.out.println(
                    "Error al actualizar: " + e.getMessage()
            );
        }
    }

    public void eliminarVehiculo(int id) {

        String sql = "DELETE FROM vehiculo "
                + "WHERE ID_VEHICULO = ?";

        try {

            Connection conexion = Conexion.conectar();

            PreparedStatement ps
                    = conexion.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

            System.out.println(
                    "Vehículo eliminado correctamente"
            );

        } catch (SQLException e) {

            System.out.println(
                    "Error al eliminar: " + e.getMessage()
            );
        }
    }

}
