package co.edu.uniquindio.Preparcial3.Hilos.Ejercicio1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Consumidor extends Thread {
	private Buffer buffer;
	private char[] palabraObjetivo;
	private boolean[] posicionesOcupadas;
	private ArrayList<Character> letrasNoUsadas;
	private static final String PALABRA_OBJETIVO = "universid@d#2024-2%";

	public Consumidor(Buffer buffer) {
		this.buffer = buffer;
		this.palabraObjetivo = PALABRA_OBJETIVO.toCharArray();
		this.posicionesOcupadas = new boolean[palabraObjetivo.length];
		this.letrasNoUsadas = new ArrayList<>();
	}

	@Override
	public void run() {
		while (!Thread.interrupted() && !palabraCompleta()) {
			char[] caracteres = buffer.recogerDos();
			procesarCaracter(caracteres[0]);
			procesarCaracter(caracteres[1]);

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				break;
			}
		}

		System.out.println("Palabra formada: " + PALABRA_OBJETIVO);
		System.out.println("Letras no usadas: " + letrasNoUsadas);

		guardarResultados();
		registrarLog();
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
			letrasNoUsadas.add(c);
			System.out.println("Caracter '" + c + "' no usado");
		}
	}

	private boolean palabraCompleta() {
		for (boolean ocupada : posicionesOcupadas) {
			if (!ocupada) return false;
		}
		return true;
	}

	private void guardarResultados() {
		try (PrintWriter writer = new PrintWriter("letrasSobrantes.txt")) {
			for (Character c : letrasNoUsadas) {
				writer.println(c);
			}
		} catch (IOException e) {
			System.err.println("Error al guardar letras sobrantes: " + e.getMessage());
		}
	}

	private void registrarLog() {
		try (PrintWriter writer = new PrintWriter(new FileWriter("log.txt", true))) {
			LocalDateTime ahora = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			writer.println("Fecha: " + ahora.format(formatter));
			writer.println("Palabra formada: " + PALABRA_OBJETIVO);
			writer.println("Accion: Palabra completada");
			writer.println("--------------------");
		} catch (IOException e) {
			System.err.println("Error al guardar el log: " + e.getMessage());
		}
	}
}