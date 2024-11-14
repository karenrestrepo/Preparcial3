package co.edu.uniquindio.Preparcial3.Hilos.Ejercicio3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class HiloServicio1 extends Thread {
    private String directorio;
    private String palabra;

    public HiloServicio1(String directorio, String palabra) {
        this.directorio = directorio;
        this.palabra = palabra;
    }

    @Override
    public void run() {
        int contador = 0;
        File[] archivos = new File(directorio).listFiles(file -> file.isFile() && file.getName().endsWith(".txt"));

        for (File archivo : archivos) {
            try {
                List<String> lineas = Files.readAllLines(archivo.toPath());
                long ocurrencias = lineas.stream()
                        .filter(linea -> linea.contains(palabra))
                        .count();
                if (ocurrencias > 0) {
                    contador += 1;
                    System.out.println("Hilo 1: Encontrada '" + palabra + "' en el archivo: " + archivo.getName());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Hilo 1: Encontrada '" + palabra + "' en " + contador + " archivos.");
    }
}
