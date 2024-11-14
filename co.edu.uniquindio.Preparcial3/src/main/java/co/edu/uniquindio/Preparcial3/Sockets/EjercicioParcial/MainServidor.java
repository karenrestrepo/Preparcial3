package co.edu.uniquindio.Preparcial3.Sockets.EjercicioParcial;

import javax.swing.*;

public class MainServidor {
    public static void main(String[] args) {
        // Iniciar el servidor en un hilo separado
        new Thread(() -> {
            AppServidor servidor = new AppServidor();
            servidor.iniciarServidor();
        }).start();

        // Iniciar la interfaz gráfica del cliente
        SwingUtilities.invokeLater(() -> {
            InterfazCliente cliente = new InterfazCliente();
            cliente.setVisible(true);
        });
    }
}
