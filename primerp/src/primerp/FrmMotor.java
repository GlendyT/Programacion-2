/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package primerp;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author glend
 */
public class FrmMotor extends JFrame {

    JLabel lblTitulo = new JLabel("GESTIÓN DE MOTORES");

    JLabel lblTipo = new JLabel("Tipo:");
    JComboBox<String> cmbTipo = new JComboBox<>(new String[]{
        "Gasolina", "Diesel", "Eléctrico", "Híbrido"
    });

    JLabel lblCilindrada = new JLabel("Cilindrada (cc):");
    JTextField txtCilindrada = new JTextField();

    JButton btnCrear = new JButton("Crear");
    JButton btnActualizar = new JButton("Actualizar");
    JButton btnEliminar = new JButton("Eliminar");
    JButton btnLimpiar = new JButton("Limpiar");

    JTable tablaMotores;
    DefaultTableModel modeloTabla;
    JScrollPane scrollTabla;

    ArrayList<Motor> motores = new ArrayList<>();
    int filaSeleccionada = -1;

    FrmMotor() {
        setTitle("Gestión de Motores");
        setSize(500, 420);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        lblTitulo.setBounds(160, 15, 200, 30);
        add(lblTitulo);

        lblTipo.setBounds(50, 60, 120, 30);
        add(lblTipo);
        cmbTipo.setBounds(180, 60, 200, 30);
        add(cmbTipo);

        lblCilindrada.setBounds(50, 100, 120, 30);
        add(lblCilindrada);
        txtCilindrada.setBounds(180, 100, 200, 30);
        add(txtCilindrada);

        // Botones
        btnCrear.setBounds(30, 150, 100, 35);
        add(btnCrear);

        btnActualizar.setBounds(140, 150, 100, 35);
        add(btnActualizar);

        btnEliminar.setBounds(250, 150, 100, 35);
        add(btnEliminar);

        btnLimpiar.setBounds(360, 150, 100, 35);
        add(btnLimpiar);

        // Tabla
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Tipo");
        modeloTabla.addColumn("Cilindrada (cc)");
        tablaMotores = new JTable(modeloTabla);

        scrollTabla = new JScrollPane(tablaMotores);
        scrollTabla.setBounds(30, 200, 430, 160);
        add(scrollTabla);

        // Eventos de botones
        btnCrear.addActionListener(e -> crearMotor());
        btnActualizar.addActionListener(e -> actualizarMotor());
        btnEliminar.addActionListener(e -> eliminarMotor());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        // Evento de selección en la tabla
        tablaMotores.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                filaSeleccionada = tablaMotores.getSelectedRow();
                if (filaSeleccionada >= 0) {
                    cargarMotorEnCampos(filaSeleccionada);
                }
            }
        });
    }

    private void crearMotor() {
        try {
            String tipo = (String) cmbTipo.getSelectedItem();
            int cilindrada = Integer.parseInt(txtCilindrada.getText());

            Motor motor = new Motor(tipo, cilindrada);
            motores.add(motor);

            modeloTabla.addRow(new Object[]{
                motor.getTipo(),
                motor.getCilindrada()
            });

            motor.mostrarInformacion();
            JOptionPane.showMessageDialog(this, "Motor creado exitosamente.");
            limpiarCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Por favor ingrese un valor numérico válido para la cilindrada.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarMotor() {
        if (filaSeleccionada < 0) {
            JOptionPane.showMessageDialog(this,
                    "Seleccione un motor de la tabla para actualizar.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String tipo = (String) cmbTipo.getSelectedItem();
            int cilindrada = Integer.parseInt(txtCilindrada.getText());

            Motor motor = motores.get(filaSeleccionada);
            motor.setTipo(tipo);
            motor.setCilindrada(cilindrada);

            // Actualizar la tabla
            modeloTabla.setValueAt(tipo, filaSeleccionada, 0);
            modeloTabla.setValueAt(cilindrada, filaSeleccionada, 1);

            JOptionPane.showMessageDialog(this, "Motor actualizado exitosamente.");
            limpiarCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Por favor ingrese un valor numérico válido para la cilindrada.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarMotor() {
        if (filaSeleccionada < 0) {
            JOptionPane.showMessageDialog(this,
                    "Seleccione un motor de la tabla para eliminar.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de eliminar este motor?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            motores.remove(filaSeleccionada);
            modeloTabla.removeRow(filaSeleccionada);
            JOptionPane.showMessageDialog(this, "Motor eliminado exitosamente.");
            limpiarCampos();
            filaSeleccionada = -1;
        }
    }

    private void cargarMotorEnCampos(int fila) {
        Motor motor = motores.get(fila);
        cmbTipo.setSelectedItem(motor.getTipo());
        txtCilindrada.setText(String.valueOf(motor.getCilindrada()));
    }

    private void limpiarCampos() {
        cmbTipo.setSelectedIndex(0);
        txtCilindrada.setText("");
        tablaMotores.clearSelection();
        filaSeleccionada = -1;
    }
}
