/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

/**
 *
 * @author glend
 */
public class FrmVehiculo extends JFrame {
    
    JLabel lblTitulo = new JLabel("REGISTRO DE VEHÍCULO");
    
    JLabel lblMarca = new JLabel("Marca:");
    JTextField txtMarca = new JTextField();
    
    JLabel lblModelo = new JLabel("Modelo:");
    JTextField txtModelo = new JTextField();
    
    JLabel lblAnio = new JLabel("Año:");
    JTextField txtAnio = new JTextField();
    
    JLabel lblPrecio = new JLabel("Precio:");
    JTextField txtPrecio = new JTextField();
    
    JLabel lblMarcaLlanta = new JLabel("Marca de llanta:");
    JTextField txtMarcaLlanta = new JTextField();
    
    JLabel lblTamanioLlanta = new JLabel("Tamaño de llanta:");
    JTextField txtTamanioLlanta = new JTextField();
    
    JLabel lblPresionLlanta = new JLabel("Presión de llanta:");
    JTextField txtPresionLlanta = new JTextField();
    
    JButton btnGuardar = new JButton("Guardar");
    JButton btnActualizar = new JButton("Actualizar");
    JButton btnEliminar = new JButton("Eliminar");
    JButton btnMotor = new JButton("Gestionar Motor");
    int contador = 0; // variable global o atributo

    JTable tablaVehiculos;
    DefaultTableModel modeloTabla;
    JScrollPane scrollTabla;
    
    JTextField txtColor = new JTextField();
    JLabel lblColor = new JLabel("Color:");
    
    ArrayList<Vehiculo> carros = new ArrayList();
    ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();
    
    FrmVehiculo() {
        setTitle("Registro de Vehículo");
        setSize(700, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        lblTitulo.setBounds(150, 20, 200, 30);
        add(lblTitulo);
        
        lblMarca.setBounds(50, 70, 100, 30);
        add(lblMarca);
        
        txtMarca.setBounds(150, 70, 200, 30);
        add(txtMarca);
        
        lblModelo.setBounds(50, 110, 100, 30);
        add(lblModelo);
        
        txtModelo.setBounds(150, 110, 200, 30);
        add(txtModelo);
        
        lblAnio.setBounds(50, 150, 100, 30);
        add(lblAnio);
        
        txtAnio.setBounds(150, 150, 200, 30);
        add(txtAnio);
        
        lblPrecio.setBounds(50, 190, 100, 30);
        add(lblPrecio);
        
        txtPrecio.setBounds(150, 190, 200, 30);
        add(txtPrecio);
        
        lblMarcaLlanta.setBounds(50, 230, 120, 30);
        add(lblMarcaLlanta);
        
        txtMarcaLlanta.setBounds(180, 230, 200, 30);
        add(txtMarcaLlanta);
        
        lblTamanioLlanta.setBounds(50, 270, 130, 30);
        add(lblTamanioLlanta);
        
        txtTamanioLlanta.setBounds(180, 270, 200, 30);
        add(txtTamanioLlanta);
        
        lblPresionLlanta.setBounds(50, 310, 130, 30);
        add(lblPresionLlanta);
        
        txtPresionLlanta.setBounds(180, 310, 200, 30);
        add(txtPresionLlanta);
        
        lblColor.setBounds(50, 350, 130, 30);
        add(lblColor);
        
        txtColor.setBounds(180, 350, 200, 30);
        add(txtColor);
        
        btnGuardar.setBounds(50, 395, 120, 35);
        add(btnGuardar);

        btnActualizar.setBounds(310, 395, 120, 35);
        add(btnActualizar);

        btnEliminar.setBounds(440, 395, 120, 35);
        add(btnEliminar);
        
        modeloTabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modeloTabla.addColumn("id");
        modeloTabla.addColumn("Marca");
        modeloTabla.addColumn("Modelo");
        modeloTabla.addColumn("Año");
        modeloTabla.addColumn("color");
        modeloTabla.addColumn("precio");
        tablaVehiculos = new JTable(modeloTabla);
        
        scrollTabla = new JScrollPane(tablaVehiculos);
        scrollTabla.setBounds(80, 440, 560, 290);
        add(scrollTabla);
        
        Conexion c = new Conexion();
        listaVehiculos = c.mostrarVehiculos();
        System.out.println("ya tengo mis vehiculos" + listaVehiculos.size());
        
        for (int i = 0; i < listaVehiculos.size(); i++) {
            Vehiculo carro = listaVehiculos.get(i);
            this.modeloTabla.addRow(new Object[]{carro.id, carro.getMarca(), carro.getModelo(), carro.getAnio(), carro.color, carro.GetPrecio()});
        }
        
        this.tablaVehiculos.getSelectionModel().addListSelectionListener(e -> {
            functionfila();
        });
        
        btnMotor.setBounds(180, 395, 120, 35);
        add(btnMotor);
        
        this.btnGuardar.addActionListener(e -> {
            functionbtn();
        });
        
        this.btnMotor.addActionListener(e -> {
            FrmMotor ventanaMotor = new FrmMotor();
            ventanaMotor.setVisible(true);
        });

        this.btnActualizar.addActionListener(e -> {
            functionActualizar();
        });

        this.btnEliminar.addActionListener(e -> {
            functionEliminar();
        });

    }
    
    private void functionfila() {
        int fila = this.tablaVehiculos.getSelectedRow();
        if (fila >= 0) {
            System.out.println("fila seleccionada" + fila);
            Vehiculo c = this.listaVehiculos.get(fila);
            c.mostrarInformacion();
            
            this.txtMarca.setText(c.getMarca());
            this.txtModelo.setText(c.getModelo());
            this.txtAnio.setText(String.valueOf(c.getAnio()));
            this.txtPrecio.setText(String.valueOf(c.GetPrecio()));
            this.txtColor.setText(c.color);
        }
    }
    
    private void functionActualizar() {
        int fila = this.tablaVehiculos.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un vehículo para actualizar");
            return;
        }
        Vehiculo carro = this.listaVehiculos.get(fila);
        String marca = txtMarca.getText();
        String modelo = txtModelo.getText();
        int anio = Integer.parseInt(txtAnio.getText());
        double precio = Double.parseDouble(txtPrecio.getText());
        String color = txtColor.getText();

        Conexion c = new Conexion();
        c.actualizarVehiculo(carro.id, marca, modelo, anio, precio);

        carro.marca = marca;
        carro.modelo = modelo;
        carro.setAnio(anio);
        carro.setPrecio(precio);
        carro.color = color;

        this.modeloTabla.setValueAt(carro.id, fila, 0);
        this.modeloTabla.setValueAt(marca, fila, 1);
        this.modeloTabla.setValueAt(modelo, fila, 2);
        this.modeloTabla.setValueAt(anio, fila, 3);
        this.modeloTabla.setValueAt(color, fila, 4);
        this.modeloTabla.setValueAt(precio, fila, 5);

        JOptionPane.showMessageDialog(this, "Vehículo actualizado");
    }

    private void functionEliminar() {
        int fila = this.tablaVehiculos.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un vehículo para eliminar");
            return;
        }
        Vehiculo carro = this.listaVehiculos.get(fila);
        int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar el vehículo seleccionado?");
        if (confirm != JOptionPane.YES_OPTION) return;

        Conexion c = new Conexion();
        c.eliminarVehiculo(carro.id);

        this.listaVehiculos.remove(fila);
        this.modeloTabla.removeRow(fila);

        JOptionPane.showMessageDialog(this, "Vehículo eliminado");
    }

    private void functionbtn() {
        
        contador++;
        System.out.println("hola" + contador);
        
        String marca = txtMarca.getText();
        String modelo = txtModelo.getText();
        
        int anio = Integer.parseInt(txtAnio.getText());
        
        double precio = Double.parseDouble(txtPrecio.getText());
        String color = this.txtColor.getText();
        
        Llanta llanta = new Llanta(
                txtMarcaLlanta.getText(),
                Integer.parseInt(txtTamanioLlanta.getText()),
                Double.parseDouble(txtPresionLlanta.getText())
        );
        
        Vehiculo carro = new Vehiculo(
                marca,
                modelo,
                anio,
                precio,
                "",
                llanta
        );
        
        this.modeloTabla.addRow(new Object[]{carro.getMarca(), carro.getModelo(), carro.getAnio()});
        
        int id = this.listaVehiculos.size() + 1;
        Conexion c = new Conexion();
        c.insertarVehiculo(id, marca, modelo, anio, precio, color);
        this.listaVehiculos.add(carro);
        
        this.carros.add(carro);
        carro.mostrarInformacion();
        JOptionPane.showMessageDialog(this, "vehiculo guardado");
        this.txtMarca.setText("");
        this.txtModelo.setText("");
        this.txtAnio.setText("");
        this.txtPrecio.setText("");
    }
    
}
