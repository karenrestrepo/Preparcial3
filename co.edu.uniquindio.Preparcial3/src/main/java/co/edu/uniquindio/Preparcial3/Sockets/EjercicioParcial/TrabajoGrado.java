package co.edu.uniquindio.Preparcial3.Sockets.EjercicioParcial;

import java.util.ArrayList;
import java.util.List;

public class TrabajoGrado {
    private String fecha;
    private String titulo;
    private String descripcion;
    private List<Autor> autores;

    public TrabajoGrado(String fecha, String titulo, String descripcion) {
        this.fecha = fecha;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.autores = new ArrayList<>();
    }

    public void agregarAutor(Autor autor) {
        autores.add(autor);
    }

    public String getFecha() { return fecha; }
    public String getTitulo() { return titulo; }
    public String getDescripcion() { return descripcion; }
    public List<Autor> getAutores() { return autores; }
}