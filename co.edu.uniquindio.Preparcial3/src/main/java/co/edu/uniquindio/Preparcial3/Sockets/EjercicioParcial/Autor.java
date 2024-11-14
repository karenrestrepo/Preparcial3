package co.edu.uniquindio.Preparcial3.Sockets.EjercicioParcial;

public class Autor {
    private String nombre;
    private String apellidos;
    private String cedula;
    private String programa;
    private String tituloProf;

    public Autor(String nombre, String apellidos, String cedula, String programa, String tituloProf) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.cedula = cedula;
        this.programa = programa;
        this.tituloProf = tituloProf;
    }

    public String getNombre() { return nombre; }
    public String getApellidos() { return apellidos; }
    public String getCedula() { return cedula; }
    public String getPrograma() { return programa; }
    public String getTituloProf() { return tituloProf; }
}
