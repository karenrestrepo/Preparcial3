package co.edu.uniquindio.Preparcial3.Hilos.Ejercicio5;


import co.edu.uniquindio.Preparcial3.Hilos.Ejercicio1.CaracteresCompartidos;

import java.util.function.Predicate;

public class Productor extends Thread {
    public static char[] CARACTERES = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
    };
    private Buffer buffer;
    private int tiempoEspera;
    private String tipo;
    private java.util.function.Predicate<Character> filtro;

    public Productor(Buffer buffer, int tiempoEspera, String tipo,
                     java.util.function.Predicate<Character> filtro) {
        this.buffer = buffer;
        this.tiempoEspera = tiempoEspera;
        this.tipo = tipo;
        this.filtro = filtro;
    }


    public void run() {
        while (!Thread.interrupted()) {
            char[] caracteres = CARACTERES;
            char caracterSeleccionado;
            do {
                caracterSeleccionado = caracteres[(int)(Math.random() * caracteres.length)];
            } while (!filtro.test(caracterSeleccionado));

            buffer.lanzar(caracterSeleccionado);
            System.out.println("Productor " + tipo + " lanzó: " + caracterSeleccionado);

            try {
                Thread.sleep(tiempoEspera);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public static boolean esVocal(char c) {
        return "aeiou".indexOf(c) != -1;
    }

    public static boolean esConsonante(char c) {
        return Character.isLetter(c) && !esVocal(c);
    }

    public static boolean esNumero(char c) {
        return Character.isDigit(c);
    }
}