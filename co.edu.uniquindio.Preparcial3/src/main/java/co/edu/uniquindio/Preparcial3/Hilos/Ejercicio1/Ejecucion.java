package co.edu.uniquindio.Preparcial3.Hilos.Ejercicio1;

public class Ejecucion {

	public static void main( String args[] ) {
		Buffer t = new Buffer();
		Productor p = new Productor( t );
		Consumidor c = new Consumidor( t );
		p.start();
		c.start();
	}
}