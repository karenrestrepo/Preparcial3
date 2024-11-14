package co.edu.uniquindio.Preparcial3.Hilos.Ejercicio5;

class Buffer {
    private char[] buffer = new char[12];
    private int siguiente = 0;
    private boolean estaLlena = false;
    private boolean estaVacia = true;

    public synchronized char[] recoger() {
        while (estaVacia || siguiente < 2) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        char[] caracteres = new char[2];
        siguiente -= 2;
        caracteres[0] = buffer[siguiente];
        caracteres[1] = buffer[siguiente + 1];

        if (siguiente == 0) {
            estaVacia = true;
        }
        estaLlena = false;
        notify();
        return caracteres;
    }

    public synchronized void lanzar(char c) {
        while (estaLlena) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        buffer[siguiente] = c;
        siguiente++;
        if (siguiente == 12) {
            estaLlena = true;
        }
        estaVacia = false;
        notify();
    }
}

