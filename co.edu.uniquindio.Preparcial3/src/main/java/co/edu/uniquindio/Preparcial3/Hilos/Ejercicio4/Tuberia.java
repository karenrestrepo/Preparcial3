package co.edu.uniquindio.Preparcial3.Hilos.Ejercicio4;

import java.util.HashSet;
import java.util.Set;

public class Tuberia {
    private char buffer[] = new char[6];
    private int siguiente = 0;
    private boolean estaLlena = false;
    private boolean estaVacia = true;
    private Set<Character> letrasEnTuberia = new HashSet<>();

    public synchronized char recoger() {
        while (estaVacia) {
            try {
                wait();
            } catch (InterruptedException e) {
                ;
            }
        }
        siguiente--;
        if (siguiente == 0)
            estaVacia = true;
        estaLlena = false;
        notify();
        return buffer[siguiente];
    }

    public synchronized void lanzar(char c, String productor) {
        while (estaLlena) {
            try {
                wait();
            } catch (InterruptedException e) {
                ;
            }
        }
        if (!letrasEnTuberia.contains(c)) {
            buffer[siguiente] = c;
            letrasEnTuberia.add(c);
            siguiente++;
            if (siguiente == 6)
                estaLlena = true;
            estaVacia = false;
            notify();
        }
    }

    public boolean contieneLetra(char c) {
        return letrasEnTuberia.contains(c);
    }
}

