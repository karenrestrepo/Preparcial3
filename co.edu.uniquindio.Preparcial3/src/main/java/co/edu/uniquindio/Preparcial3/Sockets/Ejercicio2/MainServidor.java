package co.edu.uniquindio.Preparcial3.Sockets.Ejercicio2;

public class MainServidor {
    public static void main(String[] args) {
        AppServidor appServidor = new AppServidor();
        System.out.println("Iniciando servidor de estación de gasolina");
        appServidor.iniciarServidor();
    }
}
