package co.edu.uniquindio.Preparcial3.Hilos.Ejercicio1;

public class Consumidor extends Thread {
	private Buffer buffer;

	public Consumidor( Buffer t )
	{
		// Mantiene una copia propia del objeto compartido
		buffer = t;
	}

	public void run() {
		char c;

		// Consume 10 letras de la tuber�a
		for( int i=0; i < 10; i++ )
		{
			c = buffer.recoger();
			// Imprime las letras retiradas
			System.out.println( "Recogido el caracter "+c );
			// Espera un poco antes de coger m�s letras
			try 
			{
				sleep( (int)(Math.random() * 2000 ) );
			}
			catch( InterruptedException e )
			{
				;
			}
		}
	}
}