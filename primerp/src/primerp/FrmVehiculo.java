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
import javax.swing.JTextField;

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
    int contador=0; // variable global o atributo

    ArrayList<Vehiculo> carros = new ArrayList();

    FrmVehiculo() {
        setTitle("Registro de Vehículo");
        setSize(500, 500);
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

        btnGuardar.setBounds(150, 360, 200, 35);
        add(btnGuardar);
        
        this.btnGuardar.addActionListener(e -> {
            functionbtn();
        });

    }

    private void functionbtn() {

        contador++;
        System.out.println("hola"+contador);
        
        String marca = txtMarca.getText();
        String modelo = txtModelo.getText();

        int anio = Integer.parseInt(txtAnio.getText());

        double precio = Double.parseDouble(txtPrecio.getText());

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
                        llanta
                );

        carro.mostrarInformacion();
        this.carros.add(carro);
       

        JOptionPane.showMessageDialog(this, "vehiculo guardado");
        this.txtMarca.setText("");
        this.txtModelo.setText("");
        this.txtAnio.setText("");
        this.txtPrecio.setText("");
    }

}
