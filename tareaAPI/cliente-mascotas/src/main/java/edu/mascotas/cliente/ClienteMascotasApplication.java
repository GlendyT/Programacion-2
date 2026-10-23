package edu.mascotas.cliente;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class ClienteMascotasApplication {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
                // Se conserva el estilo Swing predeterminado si el del sistema no esta disponible.
            }

            new VentanaMascotas().setVisible(true);
        });
    }
}
