package co.edu.uniquindio.Preparcial3.Sockets.Ejercicio1;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private String host;
    private int puerto;
    private ServerSocket servidorSocket;
    private Socket socketCliente;
    private DataInputStream entradaDatos;
    private DataOutputStream salidaDatos;

    public Servidor(String host, int puerto) {
        this.host = host;
        this.puerto = puerto;
    }

    public void iniciarServidor() {
        try {
            servidorSocket = new ServerSocket(puerto);
            System.out.println("Servidor esperando conexiones...");
            socketCliente = servidorSocket.accept();
            System.out.println("Cliente conectado");

            entradaDatos = new DataInputStream(socketCliente.getInputStream());
            salidaDatos = new DataOutputStream(socketCliente.getOutputStream());

            // Recibir datos del cliente
            int numero = entradaDatos.readInt();
            String texto = entradaDatos.readUTF();

            // Calcular resultados
            int digitos = contarDigitos(numero);
            int vocales = contarVocales(texto, 0, 0);
            int consonantes = contarConsonantes(texto, 0, 0);

            // Imprimir resultados en el servidor
            System.out.println("\nResultados en el servidor:");
            System.out.println("Número recibido: " + numero);
            System.out.println("Texto recibido: " + texto);
            System.out.println("Número de dígitos: " + digitos);
            System.out.println("Número de vocales: " + vocales);
            System.out.println("Número de consonantes: " + consonantes);
            System.out.println("\nEnviando resultados al cliente...");

            // Enviar resultados al cliente
            salidaDatos.writeInt(digitos);
            salidaDatos.writeInt(vocales);
            salidaDatos.writeInt(consonantes);

            // Cerrar conexiones
            entradaDatos.close();
            salidaDatos.close();
            socketCliente.close();
            servidorSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int contarDigitos(int num) {
        if (num == 0) {
            return 0;
        }
        return 1 + contarDigitos(num / 10);
    }

    public static int contarVocales(String str, int indice, int vocales) {
        if (indice == str.length()) {
            return vocales;
        }

        char c = Character.toLowerCase(str.charAt(indice));
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
            return contarVocales(str, indice + 1, vocales + 1);
        } else {
            return contarVocales(str, indice + 1, vocales);
        }
    }

    public static int contarConsonantes(String str, int indice, int consonantes) {
        if (indice == str.length()) {
            return consonantes;
        }

        char c = Character.toLowerCase(str.charAt(indice));
        if (c >= 'a' && c <= 'z' && !(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')) {
            return contarConsonantes(str, indice + 1, consonantes + 1);
        } else {
            return contarConsonantes(str, indice + 1, consonantes);
        }
    }
}