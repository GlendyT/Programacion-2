package edu.mascotas.cliente;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONObject;

public class VentanaMascotas extends JFrame {

    private static final URI API_URI = URI.create("http://localhost:8080/api/mascotas");

    private final DefaultTableModel modelo = new DefaultTableModel(
            new Object[]{"ID", "Nombre", "Especie", "Raza", "Años", "Peso (lb)",
                "Vacunado", "Estatus", "Fecha de registro"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private final JButton botonCargar = new JButton("Cargar mascotas");
    private final JLabel etiquetaEstado = new JLabel("Presiona el botón para consultar la API.");

    public VentanaMascotas() {
        super("Mascotas en adopción");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 500);
        setLocationRelativeTo(null);

        JTable tabla = new JTable(modelo);
        tabla.setAutoCreateRowSorter(true);
        tabla.setFillsViewportHeight(true);

        JPanel barraSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        barraSuperior.add(botonCargar);
        barraSuperior.add(etiquetaEstado);

        add(barraSuperior, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        botonCargar.addActionListener(evento -> cargarMascotas());
    }

    private void cargarMascotas() {
        botonCargar.setEnabled(false);
        etiquetaEstado.setText("Consultando la API...");

        SwingWorker<JSONArray, Void> trabajador = new SwingWorker<>() {
            @Override
            protected JSONArray doInBackground() throws Exception {
                HttpClient cliente = HttpClient.newBuilder()
                        .connectTimeout(Duration.ofSeconds(10))
                        .build();

                HttpRequest solicitud = HttpRequest.newBuilder()
                        .uri(API_URI)
                        .timeout(Duration.ofSeconds(30))
                        .header("Accept", "application/json")
                        .GET()
                        .build();

                HttpResponse<String> respuesta = cliente.send(
                        solicitud, HttpResponse.BodyHandlers.ofString());

                if (respuesta.statusCode() < 200 || respuesta.statusCode() >= 300) {
                    throw new IllegalStateException(
                            "La API respondió HTTP " + respuesta.statusCode() + ": " + respuesta.body());
                }

                return new JSONArray(respuesta.body());
            }

            @Override
            protected void done() {
                try {
                    JSONArray mascotas = get();
                    modelo.setRowCount(0);

                    for (int i = 0; i < mascotas.length(); i++) {
                        JSONObject mascota = mascotas.getJSONObject(i);
                        String raza = mascota.isNull("raza") ? "" : mascota.getString("raza");
                        String vacunado = mascota.getBoolean("esta_vacunado") ? "Sí" : "No";

                        modelo.addRow(new Object[]{
                            mascota.getString("id"),
                            mascota.getString("nombre"),
                            mascota.getString("especie"),
                            raza,
                            mascota.getInt("anios"),
                            mascota.getBigDecimal("peso"),
                            vacunado,
                            mascota.getString("estatus"),
                            mascota.getString("timestamp")
                        });
                    }

                    etiquetaEstado.setText(mascotas.length() + " mascota(s) cargada(s).");
                } catch (Exception exception) {
                    String detalle = exception.getCause() == null
                            ? exception.getMessage()
                            : exception.getCause().getMessage();
                    etiquetaEstado.setText("No se pudieron cargar las mascotas.");
                    javax.swing.JOptionPane.showMessageDialog(
                            VentanaMascotas.this,
                            "Verifica que la API esté activa en localhost:8080.\n" + detalle,
                            "Error al consultar la API",
                            javax.swing.JOptionPane.ERROR_MESSAGE);
                } finally {
                    botonCargar.setEnabled(true);
                }
            }
        };

        trabajador.execute();
    }
}
