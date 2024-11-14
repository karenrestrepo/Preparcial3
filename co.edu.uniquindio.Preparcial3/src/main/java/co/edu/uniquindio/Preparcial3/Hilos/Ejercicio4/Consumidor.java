package co.edu.uniquindio.Preparcial3.Hilos.Ejercicio4;

class Consumidor extends Thread {
    private Tuberia tuberia;

    public Consumidor(Tuberia t) {
        tuberia = t;
    }

    public void run() {
        char c1, c2;
        while (true) {
            c1 = tuberia.recoger();
            c2 = tuberia.recoger();
            System.out.println("Consumidor recogió los caracteres " + c1 + " y " + c2);
            try {
                sleep(3000); // Detiene el consumidor por 3 segundos
            } catch (InterruptedException e) {
                ;
            }
        }
    }
}
