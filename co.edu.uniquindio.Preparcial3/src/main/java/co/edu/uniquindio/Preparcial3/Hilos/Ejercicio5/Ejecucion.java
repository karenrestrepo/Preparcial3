package co.edu.uniquindio.Preparcial3.Hilos.Ejercicio5;
import co.edu.uniquindio.Preparcial3.Hilos.Ejercicio5.*;


public class Ejecucion {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();

        Productor p1 = new Productor(buffer, 500, "vocales",
                c -> Productor.esVocal(c));
        Productor p2 = new Productor(buffer, 1000, "consonantes",
                c -> Productor.esConsonante(c));
        Productor p3 = new Productor(buffer, 2000, "números",
                c -> Productor.esNumero(c));

        Consumidor consumidor = new Consumidor(buffer);

        p1.start();
        p2.start();
        p3.start();
        consumidor.start();
        try {
            consumidor.join();
            // Una vez que el consumidor termina, detenemos los productores
            detenerHilos(p1, p2, p3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void detenerHilos(Productor p1, Productor p2, Productor p3) {
        p1.interrupt();
        p2.interrupt();
        p3.interrupt();
        System.out.println("Todos los hilos han sido detenidos.");
    }
}
