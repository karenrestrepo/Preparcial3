package co.edu.uniquindio.Preparcial3.Sockets.Ejercicio1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    private String host;
    private int puerto;
    private Socket socket;
    private DataOutputStream salidaDatos;
    private DataInputStream entradaDatos;
    private Scanner scanner;

    public Cliente(String host, int puerto) {
        this.host = host;
        this.puerto = puerto;
        this.scanner = new Scanner(System.in);
    }

    public void iniciarCliente() {
        try {
            crearConexion();
            entradaDatos = new DataInputStream(socket.getInputStream());
            salidaDatos = new DataOutputStream(socket.getOutputStream());

            // Solicitar datos al usuario
            System.out.print("Ingrese un número entero: ");
            int numero = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            System.out.print("Ingrese una frase: ");
            String frase = scanner.nextLine();

            // Enviar datos al servidor
            enviarDatos(numero, frase);
            System.out.println("\nDatos enviados al servidor:");
            System.out.println("Número enviado: " + numero);
            System.out.println("Frase enviada: " + frase);

            // Recibir resultados del servidor
            System.out.println("\nEsperando respuesta del servidor...");
            int digitos = entradaDatos.readInt();
            int vocales = entradaDatos.readInt();
            int consonantes = entradaDatos.readInt();

            // Mostrar resultados en consola del cliente
            System.out.println("\nResultados recibidos del servidor:");
            System.out.println("Número de dígitos: " + digitos);
            System.out.println("Número de vocales: " + vocales);
            System.out.println("Número de consonantes: " + consonantes);

            // Cerrar conexiones
            entradaDatos.close();
            salidaDatos.close();
            socket.close();
            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void enviarDatos(int numero, String frase) throws IOException {
        salidaDatos.writeInt(numero);
        salidaDatos.writeUTF(frase);
    }

    private void recibirDatos() throws IOException {
        int digitos = entradaDatos.readInt();
        System.out.println("\nResultados del servidor:");
        System.out.println("Número de dígitos: " + digitos);
        System.out.println(entradaDatos.readUTF());
        System.out.println(entradaDatos.readUTF());
    }

    private void crearConexion() throws IOException {
        socket = new Socket(host, puerto);
        System.out.println("Conexión establecida con el servidor " + host + ":" + puerto);
    }
}
