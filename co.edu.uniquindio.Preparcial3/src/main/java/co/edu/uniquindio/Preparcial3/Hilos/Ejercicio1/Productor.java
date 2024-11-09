package co.edu.uniquindio.Preparcial3.Hilos.Ejercicio1;

class Productor extends Thread {
	private Buffer buffer;
	private char[] arreglo = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
			'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',

			// Letras mayúsculas
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
			'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',

			// Números
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',

			// Caracteres especiales
			'@', '#', '-', '*', '$', '(', ')', '/', '%', '+', ':', ';'};

	public Productor( Buffer t )
	{
		// Mantiene una copia propia del objeto compartido
		buffer = t;
	}

	public void run() {
		char c;

		// Mete 10 letras en la tuber�a
		for( int i=0; i < 12; i++ )
		{
			c = arreglo.charAt( (int)(Math.random()*26 ) );
			buffer.lanzar( c );
			// Imprime un registro con lo a�adido
			System.out.println( "Lanzado "+c+" a la tuberia." );
			// Espera un poco antes de a�adir m�s letras
			try
			{
				sleep( (int)(Math.random() * 100 ) );
			} 
			catch( InterruptedException e ) 
			{
				System.out.println(e);;
			}
		}
	}
}