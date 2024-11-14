package co.edu.uniquindio.Preparcial3.Hilos.Ejercicio4;

public class Ejecucion {
    public static void main(String args[]) {
        Tuberia t = new Tuberia();
        Productor p1 = new Productor(t);
        Productor p2 = new Productor(t);
        Consumidor c = new Consumidor(t);
        p1.setName("P1");
        p2.setName("P2");
        p1.start();
        p2.start();
        c.start();
    }
}
