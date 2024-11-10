package co.edu.uniquindio.Preparcial3.Hilos.Ejercicio1;

class Productor extends Thread {
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

	@Override
	public void run() {
		while (!Thread.interrupted()) {
			char[] caracteres = CaracteresCompartidos.TODOS_CARACTERES;
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
}