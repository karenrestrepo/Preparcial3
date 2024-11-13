package co.edu.uniquindio.Preparcial3.Sockets.Ejercicio1;

import java.util.Scanner;

public class MainCliente {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la dirección IP del servidor: ");
        String host = scanner.nextLine();
        System.out.print("Ingrese el puerto del servidor: ");
        int puerto = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        Cliente cliente = new Cliente(host, puerto);
        System.out.println("Iniciando cliente\n");
        cliente.iniciarCliente();
    }
}
