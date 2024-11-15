package co.edu.uniquindio.Preparcial3.Hilos.Ejercicio1;

public class Ejecucion {
	public static void main(String[] args) {


		Buffer buffer = new Buffer();

		// Crear los productores con sus respectivos filtros
		Productor p1 = new Productor(buffer, 100, "vocales",
				c -> CaracteresCompartidos.esVocal(c));
		Productor p2 = new Productor(buffer, 150, "consonantes",
				c -> CaracteresCompartidos.esConsonante(c));
		Productor p3 = new Productor(buffer, 250, "números",
				c -> CaracteresCompartidos.esNumero(c));
		Productor p4 = new Productor(buffer, 350, "especiales",
				c -> CaracteresCompartidos.esEspecial(c));

		// Crear el consumidor
		Consumidor consumidor = new Consumidor(buffer);

		// Iniciar todos los hilos
		p1.start();
		p2.start();
		p3.start();
		p4.start();
		consumidor.start();

		try {
			consumidor.join();
			// Una vez que el consumidor termina, detenemos los productores
			detenerHilos(p1, p2, p3, p4);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void detenerHilos(Productor p1, Productor p2, Productor p3, Productor p4) {
		p1.interrupt();
		p2.interrupt();
		p3.interrupt();
		p4.interrupt();
		System.out.println("Todos los hilos han sido detenidos.");
	}

}