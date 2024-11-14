package co.edu.uniquindio.Preparcial3.Hilos.Ejercicio3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;

public class BuscadorArchivos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String directorio = "DirectorioPalabras";

        System.out.print("Ingrese la primera palabra a buscar: ");
        String palabra1 = scanner.nextLine();

        System.out.print("Ingrese la segunda palabra a buscar: ");
        String palabra2 = scanner.nextLine();

        HiloServicio1 hilo1 = new HiloServicio1(directorio, palabra1);
        HiloServicio2 hilo2 = new HiloServicio2(directorio, palabra2);

        hilo1.start();
        hilo2.start();

        try {
            hilo1.join();
            hilo2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
