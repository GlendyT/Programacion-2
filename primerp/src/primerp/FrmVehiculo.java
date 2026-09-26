package primerp;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FrmVehiculo extends JFrame {

    JLabel lblTitulo = new JLabel("REGISTRO DE VEHICULO");

    JLabel lblMarca = new JLabel("Marca:");
    JTextField txtMarca = new JTextField();
    JLabel lblModelo = new JLabel("Modelo:");
    JTextField txtModelo = new JTextField();
    JLabel lblAnio = new JLabel("Año:");
    JTextField txtAnio = new JTextField();
    JLabel lblPrecio = new JLabel("Precio:");
    JTextField txtPrecio = new JTextField();
    JLabel lblColor = new JLabel("Color:");
    JTextField txtColor = new JTextField();

    JButton btnGuardar = new JButton("Guardar vehículo");
    JButton btnActualizar = new JButton("Actualizar");
    JButton btnEliminar = new JButton("Eliminar");
    JButton btnMotor = new JButton("Gestionar motor");
    JButton btnLimpiar = new JButton("Limpiar");

    JTable tablaVehiculos;
    DefaultTableModel modeloTabla;

    JLabel lblTituloLlantas = new JLabel("LLANTAS DEL VEHICULO SELECCIONADO");
    JLabel lblVehiculoSeleccionado = new JLabel("Seleccione un vehículo en la tabla");
    JLabel lblMarcaLlanta = new JLabel("Marca:");
    JTextField txtMarcaLlanta = new JTextField();
    JLabel lblTamanioLlanta = new JLabel("Tamaño:");
    JTextField txtTamanioLlanta = new JTextField();
    JLabel lblPresionLlanta = new JLabel("Presión:");
    JTextField txtPresionLlanta = new JTextField();
    JButton btnGuardarLlanta = new JButton("Guardar llanta");
    JButton btnNuevaLlanta = new JButton("Nueva llanta");
    JLabel lblEstadoLlantas = new JLabel(" ");

    JTable tablaLlantas;
    DefaultTableModel modeloTablaLlantas;

    ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();
    ArrayList<Llanta> listaLlantas = new ArrayList<>();
    Llanta llantaSeleccionada;
    private final Conexion conexion = new Conexion();

    FrmVehiculo() {
        setTitle("Registro de vehículo y gestión de llantas");
        setSize(1080, 790);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        construirSeccionVehiculos();
        construirSeccionLlantas();
        registrarEventos();
        cargarVehiculos();
    }

    private void construirSeccionVehiculos() {
        lblTitulo.setBounds(220, 15, 240, 30);
        add(lblTitulo);

        agregarCampo(lblMarca, txtMarca, 55);
        agregarCampo(lblModelo, txtModelo, 95);
        agregarCampo(lblAnio, txtAnio, 135);
        agregarCampo(lblPrecio, txtPrecio, 175);
        agregarCampo(lblColor, txtColor, 215);

        btnGuardar.setBounds(30, 265, 145, 35);
        btnActualizar.setBounds(185, 265, 115, 35);
        btnEliminar.setBounds(310, 265, 100, 35);
        btnMotor.setBounds(420, 265, 140, 35);
        btnLimpiar.setBounds(570, 265, 90, 35);
        add(btnGuardar);
        add(btnActualizar);
        add(btnEliminar);
        add(btnMotor);
        add(btnLimpiar);

        modeloTabla = crearModeloNoEditable(
                "ID", "Marca", "Modelo", "Año", "Color", "Precio",
                "Marca llanta", "Tamaño llanta", "Presión llanta");
        tablaVehiculos = new JTable(modeloTabla);
        tablaVehiculos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        int[] anchos = {45, 90, 100, 55, 80, 85, 140, 120, 120};
        for (int i = 0; i < anchos.length; i++) {
            tablaVehiculos.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        JScrollPane scrollVehiculos = new JScrollPane(tablaVehiculos);
        scrollVehiculos.setBounds(30, 320, 630, 400);
        add(scrollVehiculos);
    }

    private void agregarCampo(JLabel etiqueta, JTextField campo, int y) {
        etiqueta.setBounds(50, y, 100, 30);
        campo.setBounds(150, y, 240, 30);
        add(etiqueta);
        add(campo);
    }

    private void construirSeccionLlantas() {
        lblTituloLlantas.setBounds(715, 15, 300, 30);
        lblVehiculoSeleccionado.setBounds(700, 50, 330, 30);
        add(lblTituloLlantas);
        add(lblVehiculoSeleccionado);

        agregarCampoLlanta(lblMarcaLlanta, txtMarcaLlanta, 95);
        agregarCampoLlanta(lblTamanioLlanta, txtTamanioLlanta, 135);
        agregarCampoLlanta(lblPresionLlanta, txtPresionLlanta, 175);

        btnGuardarLlanta.setBounds(700, 225, 160, 35);
        btnNuevaLlanta.setBounds(875, 225, 145, 35);
        add(btnGuardarLlanta);
        add(btnNuevaLlanta);

        modeloTablaLlantas = crearModeloNoEditable(
                "ID", "Marca", "Tamaño", "Presión");
        tablaLlantas = new JTable(modeloTablaLlantas);
        JScrollPane scrollLlantas = new JScrollPane(tablaLlantas);
        scrollLlantas.setBounds(690, 285, 350, 350);
        add(scrollLlantas);

        lblEstadoLlantas.setBounds(700, 650, 340, 30);
        add(lblEstadoLlantas);
    }

    private void agregarCampoLlanta(JLabel etiqueta, JTextField campo, int y) {
        etiqueta.setBounds(700, y, 90, 30);
        campo.setBounds(790, y, 230, 30);
        add(etiqueta);
        add(campo);
    }

    private DefaultTableModel crearModeloNoEditable(String... columnas) {
        return new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    private void registrarEventos() {
        tablaVehiculos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarVehiculoSeleccionado();
            }
        });

        tablaLlantas.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarLlantaSeleccionada();
            }
        });

        btnGuardar.addActionListener(e -> guardarVehiculo());
        btnActualizar.addActionListener(e -> actualizarVehiculo());
        btnEliminar.addActionListener(e -> eliminarVehiculo());
        btnLimpiar.addActionListener(e -> limpiarFormularioCompleto());
        btnMotor.addActionListener(e -> new FrmMotor().setVisible(true));
        btnGuardarLlanta.addActionListener(e -> guardarOActualizarLlanta());
        btnNuevaLlanta.addActionListener(e -> prepararNuevaLlanta());
    }

    private void cargarVehiculos() {
        listaVehiculos = conexion.mostrarVehiculos();
        modeloTabla.setRowCount(0);

        for (Vehiculo vehiculo : listaVehiculos) {
            ArrayList<Llanta> llantasVehiculo
                    = conexion.mostrarLlantasPorVehiculo(vehiculo.id);
            modeloTabla.addRow(new Object[]{
                vehiculo.id,
                vehiculo.getMarca(),
                vehiculo.getModelo(),
                vehiculo.getAnio(),
                vehiculo.color,
                vehiculo.GetPrecio(),
                resumirMarcas(llantasVehiculo),
                resumirTamanios(llantasVehiculo),
                resumirPresiones(llantasVehiculo)
            });
        }
    }

    private Vehiculo obtenerVehiculoSeleccionado() {
        int fila = tablaVehiculos.getSelectedRow();
        if (fila < 0 || fila >= listaVehiculos.size()) {
            return null;
        }
        return listaVehiculos.get(fila);
    }

    private void cargarVehiculoSeleccionado() {
        Vehiculo vehiculo = obtenerVehiculoSeleccionado();
        if (vehiculo == null) {
            return;
        }

        txtMarca.setText(vehiculo.getMarca());
        txtModelo.setText(vehiculo.getModelo());
        txtAnio.setText(String.valueOf(vehiculo.getAnio()));
        txtPrecio.setText(String.valueOf(vehiculo.GetPrecio()));
        txtColor.setText(vehiculo.color);
        lblVehiculoSeleccionado.setText(
                "Vehículo " + vehiculo.id + ": " + vehiculo.getMarca() + " " + vehiculo.getModelo());
        cargarLlantasDelVehiculo(vehiculo.id);
    }

    private void cargarLlantasDelVehiculo(int idVehiculo) {
        listaLlantas = conexion.mostrarLlantasPorVehiculo(idVehiculo);
        modeloTablaLlantas.setRowCount(0);

        for (Llanta llanta : listaLlantas) {
            modeloTablaLlantas.addRow(new Object[]{
                llanta.getIdLlanta(),
                llanta.getMarca(),
                llanta.getTamanio(),
                llanta.getPresion()
            });
        }

        limpiarCamposLlanta();
        actualizarVistaPreviaLlantas();
        if (listaLlantas.isEmpty()) {
            lblEstadoLlantas.setText("El vehículo no tiene llantas. Puede agregar una.");
        } else {
            tablaLlantas.setRowSelectionInterval(0, 0);
            cargarLlantaSeleccionada();
            lblEstadoLlantas.setText(
                    "Llanta ID " + llantaSeleccionada.getIdLlanta()
                    + " cargada. Puede modificar sus datos.");
        }
    }

    private void actualizarVistaPreviaLlantas() {
        int fila = tablaVehiculos.getSelectedRow();
        if (fila < 0) {
            return;
        }

        modeloTabla.setValueAt(resumirMarcas(listaLlantas), fila, 6);
        modeloTabla.setValueAt(resumirTamanios(listaLlantas), fila, 7);
        modeloTabla.setValueAt(resumirPresiones(listaLlantas), fila, 8);
    }

    private String resumirMarcas(ArrayList<Llanta> llantas) {
        StringBuilder resumen = new StringBuilder();
        for (Llanta llanta : llantas) {
            agregarAlResumen(resumen, llanta.getMarca());
        }
        return resumen.toString();
    }

    private String resumirTamanios(ArrayList<Llanta> llantas) {
        StringBuilder resumen = new StringBuilder();
        for (Llanta llanta : llantas) {
            agregarAlResumen(resumen, String.valueOf(llanta.getTamanio()));
        }
        return resumen.toString();
    }

    private String resumirPresiones(ArrayList<Llanta> llantas) {
        StringBuilder resumen = new StringBuilder();
        for (Llanta llanta : llantas) {
            agregarAlResumen(resumen, String.valueOf(llanta.getPresion()));
        }
        return resumen.toString();
    }

    private void agregarAlResumen(StringBuilder resumen, String valor) {
        if (resumen.length() > 0) {
            resumen.append(", ");
        }
        resumen.append(valor);
    }

    private void cargarLlantaSeleccionada() {
        int fila = tablaLlantas.getSelectedRow();
        if (fila < 0 || fila >= listaLlantas.size()) {
            return;
        }

        llantaSeleccionada = listaLlantas.get(fila);
        txtMarcaLlanta.setText(llantaSeleccionada.getMarca());
        txtTamanioLlanta.setText(String.valueOf(llantaSeleccionada.getTamanio()));
        txtPresionLlanta.setText(String.valueOf(llantaSeleccionada.getPresion()));
        btnGuardarLlanta.setText("Actualizar llanta");
        lblEstadoLlantas.setText("Editando llanta ID " + llantaSeleccionada.getIdLlanta());
    }

    private void guardarOActualizarLlanta() {
        Vehiculo vehiculo = obtenerVehiculoSeleccionado();
        if (vehiculo == null) {
            JOptionPane.showMessageDialog(this,
                    "Primero seleccione un vehículo en la tabla.",
                    "Vehículo requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String marca = textoRequerido(txtMarcaLlanta, "la marca de la llanta");
            int tamanio = enteroPositivo(txtTamanioLlanta, "el tamaño de la llanta");
            double presion = decimalPositivo(txtPresionLlanta, "la presión de la llanta");
            boolean operacionExitosa;
            String mensaje;

            if (llantaSeleccionada == null) {
                Llanta nuevaLlanta = new Llanta(0, vehiculo.id, marca, tamanio, presion);
                operacionExitosa = conexion.insertarLlanta(nuevaLlanta);
                mensaje = "Llanta insertada correctamente.";
            } else {
                llantaSeleccionada.setMarca(marca);
                llantaSeleccionada.setTamanio(tamanio);
                llantaSeleccionada.setPresion(presion);
                operacionExitosa = conexion.actualizarLlanta(llantaSeleccionada);
                mensaje = "Llanta actualizada correctamente.";
            }

            if (operacionExitosa) {
                cargarLlantasDelVehiculo(vehiculo.id);
                JOptionPane.showMessageDialog(this, mensaje);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Oracle no pudo guardar la llanta. Revise la consola y la tabla LLANTAS.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Datos no válidos", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void prepararNuevaLlanta() {
        if (obtenerVehiculoSeleccionado() == null) {
            JOptionPane.showMessageDialog(this, "Primero seleccione un vehículo.");
            return;
        }
        limpiarCamposLlanta();
        lblEstadoLlantas.setText("Ingrese los datos de la nueva llanta.");
        txtMarcaLlanta.requestFocus();
    }

    private void guardarVehiculo() {
        try {
            String marca = textoRequerido(txtMarca, "la marca");
            String modelo = textoRequerido(txtModelo, "el modelo");
            int anio = enteroPositivo(txtAnio, "el año");
            double precio = decimalPositivo(txtPrecio, "el precio");
            String color = textoRequerido(txtColor, "el color");
            int id = conexion.obtenerSiguienteIdVehiculo();

            if (id < 1) {
                JOptionPane.showMessageDialog(this, "No fue posible generar el ID del vehículo.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (conexion.insertarVehiculo(id, marca, modelo, anio, precio, color)) {
                cargarVehiculos();
                limpiarFormularioCompleto();
                JOptionPane.showMessageDialog(this, "Vehículo guardado correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "No fue posible guardar el vehículo.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Datos no válidos", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void actualizarVehiculo() {
        Vehiculo vehiculo = obtenerVehiculoSeleccionado();
        if (vehiculo == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un vehículo para actualizar.");
            return;
        }

        try {
            String marca = textoRequerido(txtMarca, "la marca");
            String modelo = textoRequerido(txtModelo, "el modelo");
            int anio = enteroPositivo(txtAnio, "el año");
            double precio = decimalPositivo(txtPrecio, "el precio");
            String color = textoRequerido(txtColor, "el color");

            if (conexion.actualizarVehiculo(vehiculo.id, marca, modelo, anio, precio, color)) {
                cargarVehiculos();
                limpiarFormularioCompleto();
                JOptionPane.showMessageDialog(this, "Vehículo actualizado correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "No fue posible actualizar el vehículo.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Datos no válidos", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void eliminarVehiculo() {
        Vehiculo vehiculo = obtenerVehiculoSeleccionado();
        if (vehiculo == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un vehículo para eliminar.");
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(this,
                "¿Eliminar el vehículo seleccionado?",
                "Confirmar", JOptionPane.YES_NO_OPTION);
        if (respuesta != JOptionPane.YES_OPTION) {
            return;
        }

        if (conexion.eliminarVehiculo(vehiculo.id)) {
            cargarVehiculos();
            limpiarFormularioCompleto();
            JOptionPane.showMessageDialog(this, "Vehículo eliminado correctamente.");
        } else {
            JOptionPane.showMessageDialog(this,
                    "No se pudo eliminar. Si tiene llantas asociadas, la llave foránea lo impide.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String textoRequerido(JTextField campo, String nombre) {
        String valor = campo.getText().trim();
        if (valor.isEmpty()) {
            throw new IllegalArgumentException("Debe ingresar " + nombre + ".");
        }
        return valor;
    }

    private int enteroPositivo(JTextField campo, String nombre) {
        try {
            int valor = Integer.parseInt(campo.getText().trim());
            if (valor <= 0) {
                throw new NumberFormatException();
            }
            return valor;
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Debe ingresar un número entero positivo para " + nombre + ".");
        }
    }

    private double decimalPositivo(JTextField campo, String nombre) {
        try {
            double valor = Double.parseDouble(campo.getText().trim());
            if (valor <= 0) {
                throw new NumberFormatException();
            }
            return valor;
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Debe ingresar un número positivo para " + nombre + ".");
        }
    }

    private void limpiarCamposLlanta() {
        txtMarcaLlanta.setText("");
        txtTamanioLlanta.setText("");
        txtPresionLlanta.setText("");
        tablaLlantas.clearSelection();
        llantaSeleccionada = null;
        btnGuardarLlanta.setText("Guardar llanta");
    }

    private void limpiarFormularioCompleto() {
        txtMarca.setText("");
        txtModelo.setText("");
        txtAnio.setText("");
        txtPrecio.setText("");
        txtColor.setText("");
        tablaVehiculos.clearSelection();
        listaLlantas.clear();
        modeloTablaLlantas.setRowCount(0);
        limpiarCamposLlanta();
        lblVehiculoSeleccionado.setText("Seleccione un vehículo en la tabla");
        lblEstadoLlantas.setText(" ");
    }
}
