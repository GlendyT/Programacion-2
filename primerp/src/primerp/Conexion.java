package primerp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Conexion {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:umg";
    private static final String USUARIO = "system";
    private static final String PASSWORD = "Umg$2026";

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USUARIO, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error de conexion: " + e.getMessage());
            return null;
        }
    }

    public ArrayList<Vehiculo> mostrarVehiculos() {
        String sql = "SELECT ID_VEHICULO, MARCA, MODELO, ANIO, COLOR, PRECIO "
                + "FROM VEHICULO ORDER BY ID_VEHICULO";
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        Connection conexion = conectar();

        if (conexion == null) {
            return vehiculos;
        }

        try (conexion;
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet resultado = ps.executeQuery()) {

            while (resultado.next()) {
                Vehiculo vehiculo = new Vehiculo(
                        resultado.getInt("ID_VEHICULO"),
                        resultado.getString("MARCA"),
                        resultado.getString("MODELO"),
                        resultado.getInt("ANIO"),
                        resultado.getDouble("PRECIO"),
                        resultado.getString("COLOR")
                );
                vehiculos.add(vehiculo);
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar vehiculos: " + e.getMessage());
        }

        return vehiculos;
    }

    public int obtenerSiguienteIdVehiculo() {
        String sql = "SELECT NVL(MAX(ID_VEHICULO), 0) + 1 AS SIGUIENTE_ID FROM VEHICULO";
        Connection conexion = conectar();

        if (conexion == null) {
            return -1;
        }

        try (conexion;
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet resultado = ps.executeQuery()) {
            if (resultado.next()) {
                return resultado.getInt("SIGUIENTE_ID");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el siguiente ID: " + e.getMessage());
        }

        return -1;
    }

    public boolean insertarVehiculo(int id, String marca, String modelo,
            int anio, double precio, String color) {
        String sql = "INSERT INTO VEHICULO "
                + "(ID_VEHICULO, MARCA, MODELO, ANIO, PRECIO, COLOR) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        Connection conexion = conectar();

        if (conexion == null) {
            return false;
        }

        try (conexion; PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, marca);
            ps.setString(3, modelo);
            ps.setInt(4, anio);
            ps.setDouble(5, precio);
            ps.setString(6, color);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println("Error al insertar vehiculo: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizarVehiculo(int id, String marca, String modelo,
            int anio, double precio, String color) {
        String sql = "UPDATE VEHICULO SET MARCA = ?, MODELO = ?, ANIO = ?, "
                + "PRECIO = ?, COLOR = ? WHERE ID_VEHICULO = ?";
        Connection conexion = conectar();

        if (conexion == null) {
            return false;
        }

        try (conexion; PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, marca);
            ps.setString(2, modelo);
            ps.setInt(3, anio);
            ps.setDouble(4, precio);
            ps.setString(5, color);
            ps.setInt(6, id);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println("Error al actualizar vehiculo: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarVehiculo(int id) {
        String sql = "DELETE FROM VEHICULO WHERE ID_VEHICULO = ?";
        Connection conexion = conectar();

        if (conexion == null) {
            return false;
        }

        try (conexion; PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println("Error al eliminar vehiculo: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<Llanta> mostrarLlantasPorVehiculo(int idVehiculo) {
        String sql = "SELECT ID_LLANTA, ID_VEHICULO, MARCA, TAMANIO, PRESION "
                + "FROM LLANTAS WHERE ID_VEHICULO = ? ORDER BY ID_LLANTA";
        ArrayList<Llanta> llantas = new ArrayList<>();
        Connection conexion = conectar();

        if (conexion == null) {
            return llantas;
        }

        try (conexion; PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idVehiculo);

            try (ResultSet resultado = ps.executeQuery()) {
                while (resultado.next()) {
                    Llanta llanta = new Llanta(
                            resultado.getInt("ID_LLANTA"),
                            resultado.getInt("ID_VEHICULO"),
                            resultado.getString("MARCA"),
                            resultado.getInt("TAMANIO"),
                            resultado.getDouble("PRESION")
                    );
                    llantas.add(llanta);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar llantas: " + e.getMessage());
        }

        return llantas;
    }

    public boolean insertarLlanta(Llanta llanta) {
        String sql = "INSERT INTO LLANTAS (ID_VEHICULO, MARCA, TAMANIO, PRESION) "
                + "VALUES (?, ?, ?, ?)";
        Connection conexion = conectar();

        if (conexion == null) {
            return false;
        }

        try (conexion; PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, llanta.getIdVehiculo());
            ps.setString(2, llanta.getMarca());
            ps.setInt(3, llanta.getTamanio());
            ps.setDouble(4, llanta.getPresion());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println("Error al insertar llanta: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizarLlanta(Llanta llanta) {
        String sql = "UPDATE LLANTAS SET MARCA = ?, TAMANIO = ?, PRESION = ? "
                + "WHERE ID_LLANTA = ? AND ID_VEHICULO = ?";
        Connection conexion = conectar();

        if (conexion == null) {
            return false;
        }

        try (conexion; PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, llanta.getMarca());
            ps.setInt(2, llanta.getTamanio());
            ps.setDouble(3, llanta.getPresion());
            ps.setInt(4, llanta.getIdLlanta());
            ps.setInt(5, llanta.getIdVehiculo());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println("Error al actualizar llanta: " + e.getMessage());
            return false;
        }
    }

    public boolean guardarVehiculoConLlanta(int idVehiculo, String marcaVehiculo,
            String modelo, int anio, double precio, String color, Llanta llanta,
            boolean nuevoVehiculo, boolean nuevaLlanta) {
        String sqlVehiculo;
        String sqlLlanta;

        if (nuevoVehiculo) {
            sqlVehiculo = "INSERT INTO VEHICULO "
                    + "(ID_VEHICULO, MARCA, MODELO, ANIO, PRECIO, COLOR) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
        } else {
            sqlVehiculo = "UPDATE VEHICULO SET MARCA = ?, MODELO = ?, ANIO = ?, "
                    + "PRECIO = ?, COLOR = ? WHERE ID_VEHICULO = ?";
        }

        if (nuevaLlanta) {
            sqlLlanta = "INSERT INTO LLANTAS "
                    + "(ID_VEHICULO, MARCA, TAMANIO, PRESION) VALUES (?, ?, ?, ?)";
        } else {
            sqlLlanta = "UPDATE LLANTAS SET MARCA = ?, TAMANIO = ?, PRESION = ? "
                    + "WHERE ID_LLANTA = ? AND ID_VEHICULO = ?";
        }

        Connection conexion = conectar();
        if (conexion == null) {
            return false;
        }

        try (conexion) {
            conexion.setAutoCommit(false);

            try (PreparedStatement psVehiculo = conexion.prepareStatement(sqlVehiculo);
                    PreparedStatement psLlanta = conexion.prepareStatement(sqlLlanta)) {

                if (nuevoVehiculo) {
                    psVehiculo.setInt(1, idVehiculo);
                    psVehiculo.setString(2, marcaVehiculo);
                    psVehiculo.setString(3, modelo);
                    psVehiculo.setInt(4, anio);
                    psVehiculo.setDouble(5, precio);
                    psVehiculo.setString(6, color);
                } else {
                    psVehiculo.setString(1, marcaVehiculo);
                    psVehiculo.setString(2, modelo);
                    psVehiculo.setInt(3, anio);
                    psVehiculo.setDouble(4, precio);
                    psVehiculo.setString(5, color);
                    psVehiculo.setInt(6, idVehiculo);
                }

                if (psVehiculo.executeUpdate() != 1) {
                    throw new SQLException("No se guardo el vehiculo.");
                }

                if (nuevaLlanta) {
                    psLlanta.setInt(1, idVehiculo);
                    psLlanta.setString(2, llanta.getMarca());
                    psLlanta.setInt(3, llanta.getTamanio());
                    psLlanta.setDouble(4, llanta.getPresion());
                } else {
                    psLlanta.setString(1, llanta.getMarca());
                    psLlanta.setInt(2, llanta.getTamanio());
                    psLlanta.setDouble(3, llanta.getPresion());
                    psLlanta.setInt(4, llanta.getIdLlanta());
                    psLlanta.setInt(5, idVehiculo);
                }

                if (psLlanta.executeUpdate() != 1) {
                    throw new SQLException("No se guardo la llanta.");
                }

                conexion.commit();
                return true;
            } catch (SQLException e) {
                conexion.rollback();
                System.out.println("Error al guardar vehiculo y llanta: " + e.getMessage());
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error en la transaccion: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarVehiculoConLlantas(int idVehiculo) {
        String sqlLlantas = "DELETE FROM LLANTAS WHERE ID_VEHICULO = ?";
        String sqlVehiculo = "DELETE FROM VEHICULO WHERE ID_VEHICULO = ?";
        Connection conexion = conectar();

        if (conexion == null) {
            return false;
        }

        try (conexion) {
            conexion.setAutoCommit(false);

            try (PreparedStatement psLlantas = conexion.prepareStatement(sqlLlantas);
                    PreparedStatement psVehiculo = conexion.prepareStatement(sqlVehiculo)) {
                psLlantas.setInt(1, idVehiculo);
                psLlantas.executeUpdate();

                psVehiculo.setInt(1, idVehiculo);
                if (psVehiculo.executeUpdate() != 1) {
                    throw new SQLException("No se encontro el vehiculo que se desea eliminar.");
                }

                conexion.commit();
                return true;
            } catch (SQLException e) {
                conexion.rollback();
                System.out.println("Error al eliminar vehiculo y llantas: " + e.getMessage());
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error en la transaccion de eliminacion: " + e.getMessage());
            return false;
        }
    }
}
