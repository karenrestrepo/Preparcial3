package co.edu.uniquindio.Preparcial3.Sockets.Ejercicio2;

public class MainCliente {
    public static void main(String[] args) {
        AppCliente appCliente = new AppCliente("localhost", 8081);
        System.out.println("Iniciando cliente de estación de gasolina\n");
        appCliente.iniciarCliente();
    }
}
