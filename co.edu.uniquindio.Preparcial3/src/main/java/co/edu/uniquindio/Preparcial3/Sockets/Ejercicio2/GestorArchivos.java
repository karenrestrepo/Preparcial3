package co.edu.uniquindio.Preparcial3.Sockets.Ejercicio2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GestorArchivos {
    private String rutaArchivo;

    public GestorArchivos() {
        // Crear el directorio data si no existe
        try {
            Files.createDirectories(Paths.get("data"));
            this.rutaArchivo = "dataTanqueo/registros_tanqueo.txt";

            // Crear el archivo si no existe
            File archivo = new File(rutaArchivo);
            if (!archivo.exists()) {
                archivo.createNewFile();
                // Escribir encabezado
                try (PrintWriter writer = new PrintWriter(new FileWriter(archivo, true))) {
                    writer.println("ID_CLIENTE,TIPO_VEHICULO,GALONES,FECHA_HORA");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void guardarRegistro(Registro registro) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(rutaArchivo, true))) {
            writer.print(registro.toFileString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String leerRegistros() {
        StringBuilder contenido = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contenido.toString();
    }
}
