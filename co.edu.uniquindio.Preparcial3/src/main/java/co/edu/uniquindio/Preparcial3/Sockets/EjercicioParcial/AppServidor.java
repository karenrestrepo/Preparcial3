package co.edu.uniquindio.Preparcial3.Sockets.EjercicioParcial;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
public class AppServidor {
    private static final String TRABAJOS_ARCHIVO = "resources/trabajogrados.txt";
    private static final String AUTORES_ARCHIVO = "resources/autores.txt";
    private int puerto = 8081;
    private ServerSocket servidor;
    private Socket cliente;
    private DataInputStream entradaCliente;
    private DataOutputStream salidaCliente;

    public void iniciarServidor() {
        try {
            servidor = new ServerSocket(puerto);
            System.out.println("Servidor iniciado, esperando conexión del cliente...");

            while (true) {
                cliente = servidor.accept();
                entradaCliente = new DataInputStream(cliente.getInputStream());
                salidaCliente = new DataOutputStream(cliente.getOutputStream());

                int opcion = entradaCliente.readInt();
                if (opcion == 1) {
                    enviarTrabajos();
                } else if (opcion == 2) {
                    int index = entradaCliente.readInt();
                    enviarAutores(index);
                }

                entradaCliente.close();
                salidaCliente.close();
                cliente.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void enviarTrabajos() {
        List<TrabajoGrado> trabajos = cargarTrabajos();
        try {
            salidaCliente.writeInt(trabajos.size());
            for (TrabajoGrado trabajo : trabajos) {
                salidaCliente.writeUTF(trabajo.getFecha());
                salidaCliente.writeUTF(trabajo.getTitulo());
                salidaCliente.writeUTF(trabajo.getDescripcion());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void enviarAutores(int index) {
        List<TrabajoGrado> trabajos = cargarTrabajos();
        TrabajoGrado trabajo = trabajos.get(index);
        List<Autor> autores = trabajo.getAutores();
        try {
            salidaCliente.writeInt(autores.size());
            for (Autor autor : autores) {
                salidaCliente.writeUTF(autor.getNombre());
                salidaCliente.writeUTF(autor.getApellidos());
                salidaCliente.writeUTF(autor.getCedula());
                salidaCliente.writeUTF(autor.getPrograma());
                salidaCliente.writeUTF(autor.getTituloProf());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private List<TrabajoGrado> cargarTrabajos() {
        List<TrabajoGrado> trabajos = new ArrayList<>();
        List<Autor> autores = cargarAutores();

        try (BufferedReader br = new BufferedReader(new FileReader(TRABAJOS_ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split("\\|");
                if (datos.length >= 4) {
                    String fecha = datos[0];
                    String titulo = datos[1];
                    String descripcion = datos[2];
                    String[] idsAutores = datos[3].split("@");

                    TrabajoGrado trabajo = new TrabajoGrado(fecha, titulo, descripcion);

                    // Agregar autores al trabajo
                    for (String idAutor : idsAutores) {
                        for (Autor autor : autores) {
                            if (autor.getCedula().equals(idAutor)) {
                                trabajo.agregarAutor(autor);
                            }
                        }
                    }

                    trabajos.add(trabajo);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar trabajos: " + e.getMessage());
        }

        return trabajos;
    }

    private List<Autor> cargarAutores() {
        List<Autor> autores = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(AUTORES_ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split("\\|");
                if (datos.length >= 5) {
                    String nombre = datos[0];
                    String apellidos = datos[1];
                    String cedula = datos[2];
                    String programa = datos[3];
                    String tituloProf = datos[4];

                    autores.add(new Autor(nombre, apellidos, cedula, programa, tituloProf));
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar autores: " + e.getMessage());
        }

        return autores;
    }


}
