package co.edu.uniquindio.Preparcial3.Hilos.Ejercicio5;

import java.util.ArrayList;

public class Consumidor extends Thread {
    private static final String PALABRA = "uniquindio2023";
    private Buffer buffer;
    private char[] palabraObjetivo;
    private boolean[] posicionesOcupadas;
    private ArrayList<Character> sobrantes = new ArrayList<>();

    public Consumidor(Buffer buffer) {
        this.buffer = buffer;
        this.palabraObjetivo = PALABRA.toCharArray();
        this.posicionesOcupadas = new boolean[palabraObjetivo.length];
        this.sobrantes = new ArrayList<>();
    }

    @Override
    public void run() {
        int indice = 0;
        while (!Thread.interrupted() && !palabraCompleta()) {
            char[] c = buffer.recoger();
            procesarCaracter(c[0]);
            procesarCaracter(c[1]);

            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Palabra formada: " + PALABRA);
        System.out.println("Letras sobrantes: " + sobrantes);
    }

    private void procesarCaracter(char c) {
        boolean caracterUsado = false;
        for (int i = 0; i < palabraObjetivo.length; i++) {
            if (!posicionesOcupadas[i] && palabraObjetivo[i] == c) {
                posicionesOcupadas[i] = true;
                caracterUsado = true;
                System.out.println("Caracter '" + c + "' colocado en posición " + i);
                break;
            }
        }
        if (!caracterUsado) {
            sobrantes.add(c);
            System.out.println("Caracter '" + c + "' no usado");
        }
    }

    private boolean palabraCompleta() {
        for (boolean ocupada : posicionesOcupadas) {
            if (!ocupada) return false;
        }
        return true;
    }
}
