package co.edu.uniquindio.Preparcial3.Sockets.Ejercicio1;

public class MainServidor {
    public static void main(String[] args) {
        Servidor servidor = new Servidor("localhost", 8081);
        servidor.iniciarServidor();
    }
}
