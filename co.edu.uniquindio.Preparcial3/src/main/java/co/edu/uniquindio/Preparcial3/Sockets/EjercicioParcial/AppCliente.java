package co.edu.uniquindio.Preparcial3.Sockets.EjercicioParcial;
import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
public class AppCliente {
    private String host = "localhost";
    private int puerto = 8081;
    private Socket cliente;
    private DataInputStream entradaServidor;
    private DataOutputStream salidaServidor;

    public void iniciarCliente() {
        SwingUtilities.invokeLater(() -> {
            InterfazCliente interfaz = new InterfazCliente(this);
            interfaz.setVisible(true);
        });
    }

    public List<TrabajoGrado> obtenerTrabajos() {
        List<TrabajoGrado> trabajos = new ArrayList<>();
        try {
            cliente = new Socket(host, puerto);
            entradaServidor = new DataInputStream(cliente.getInputStream());
            salidaServidor = new DataOutputStream(cliente.getOutputStream());

            salidaServidor.writeInt(1);
            int numTrabajos = entradaServidor.readInt();

            for (int i = 0; i < numTrabajos; i++) {
                String fecha = entradaServidor.readUTF();
                String titulo = entradaServidor.readUTF();
                String descripcion = entradaServidor.readUTF();
                trabajos.add(new TrabajoGrado(fecha, titulo, descripcion));
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar con el servidor: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return trabajos;
    }

    public List<Autor> obtenerAutores(int index) {
        List<Autor> autores = new ArrayList<>();
        try {
            cliente = new Socket(host, puerto);
            entradaServidor = new DataInputStream(cliente.getInputStream());
            salidaServidor = new DataOutputStream(cliente.getOutputStream());

            salidaServidor.writeInt(2);
            salidaServidor.writeInt(index);

            int numAutores = entradaServidor.readInt();
            for (int i = 0; i < numAutores; i++) {
                String nombre = entradaServidor.readUTF();
                String apellidos = entradaServidor.readUTF();
                String cedula = entradaServidor.readUTF();
                String programa = entradaServidor.readUTF();
                String tituloProf = entradaServidor.readUTF();
                autores.add(new Autor(nombre, apellidos, cedula, programa, tituloProf));
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener autores: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return autores;
    }

    public void cerrarConexiones() {
        try {
            if (entradaServidor != null) entradaServidor.close();
            if (salidaServidor != null) salidaServidor.close();
            if (cliente != null) cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
