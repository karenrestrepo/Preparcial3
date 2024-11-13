package co.edu.uniquindio.Preparcial3.Sockets.Ejercicio2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class AppServidor {
    private int puerto = 8081;
    private ServerSocket server;
    private GestorArchivos gestorArchivos;

    public AppServidor() {
        gestorArchivos = new GestorArchivos();
    }

    public void iniciarServidor() {
        try {
            server = new ServerSocket(puerto);
            System.out.println("Servidor iniciado en puerto " + puerto);
            System.out.println("Registros actuales:");
            System.out.println(gestorArchivos.leerRegistros());

            while(true) {
                System.out.println("Esperando conexiones de clientes...");
                Socket socketComunicacion = server.accept();

                Thread clienteHandler = new Thread(new ClienteHandler(socketComunicacion));
                clienteHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ClienteHandler implements Runnable {
        private Socket socketComunicacion;
        private DataInputStream flujoEntrada;
        private DataOutputStream flujoSalida;

        public ClienteHandler(Socket socket) {
            this.socketComunicacion = socket;
        }

        @Override
        public void run() {
            try {
                flujoEntrada = new DataInputStream(socketComunicacion.getInputStream());
                flujoSalida = new DataOutputStream(socketComunicacion.getOutputStream());

                procesarDatosTanqueo();

                flujoEntrada.close();
                flujoSalida.close();
                socketComunicacion.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void procesarDatosTanqueo() throws IOException {
            String id = flujoEntrada.readUTF();
            String tipo = flujoEntrada.readUTF();
            double galones = flujoEntrada.readDouble();

            Registro registro = new Registro(id, tipo, galones);
            gestorArchivos.guardarRegistro(registro);

            System.out.println("Nuevo registro almacenado: " + registro);
            flujoSalida.writeUTF("Registro almacenado exitosamente en archivo");

            // Mostrar registros actualizados
            System.out.println("\nRegistros actuales:");
            System.out.println(gestorArchivos.leerRegistros());
        }
    }
}
