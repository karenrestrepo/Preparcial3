package co.edu.uniquindio.Preparcial3.Sockets.Ejercicio2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class AppCliente {
    private String host;
    private int puerto;
    private Socket socketComunicacion;
    private DataOutputStream flujoSalida;
    private DataInputStream flujoEntrada;
    private Scanner scanner;

    public AppCliente(String host, int puerto) {
        this.puerto = puerto;
        this.host = host;
        this.scanner = new Scanner(System.in);
    }

    public void iniciarCliente() {
        try {
            crearConexion();
            flujoEntrada = new DataInputStream(socketComunicacion.getInputStream());
            flujoSalida = new DataOutputStream(socketComunicacion.getOutputStream());

            enviarDatosTanqueo();
            recibirConfirmacion();

            flujoEntrada.close();
            flujoSalida.close();
            socketComunicacion.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void enviarDatosTanqueo() throws IOException {
        System.out.println("Ingrese identificación del cliente:");
        String id = scanner.nextLine();

        System.out.println("Ingrese tipo de vehículo:");
        String tipo = scanner.nextLine();

        System.out.println("Ingrese cantidad de galones:");
        double galones = scanner.nextDouble();

        flujoSalida.writeUTF(id);
        flujoSalida.writeUTF(tipo);
        flujoSalida.writeDouble(galones);
    }

    private void recibirConfirmacion() throws IOException {
        String confirmacion = flujoEntrada.readUTF();
        System.out.println("Respuesta del servidor: " + confirmacion);
    }

    private void crearConexion() throws IOException {
        socketComunicacion = new Socket(host, puerto);
    }
}
