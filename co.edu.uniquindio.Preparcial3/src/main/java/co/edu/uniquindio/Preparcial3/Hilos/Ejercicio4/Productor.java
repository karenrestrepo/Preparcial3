package co.edu.uniquindio.Preparcial3.Hilos.Ejercicio4;

import java.util.HashSet;
import java.util.Set;

class Productor extends Thread {
    private Tuberia tuberia;
    private String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private Set<Character> letrasLanzadas = new HashSet<>();

    public Productor(Tuberia t) {
        tuberia = t;
    }

    public void run() {
        char c;
        while (letrasLanzadas.size() < 15) {
            c = alfabeto.charAt((int) (Math.random() * 26));
            if (!letrasLanzadas.contains(c) && !tuberia.contieneLetra(c)) {
                letrasLanzadas.add(c);
                tuberia.lanzar(c, getName());
                System.out.println("Productor " + getName() + " lanzó " + c + " a la tubería.");
                try {
                    sleep((int) (Math.random() * 100));
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        }
    }
}
