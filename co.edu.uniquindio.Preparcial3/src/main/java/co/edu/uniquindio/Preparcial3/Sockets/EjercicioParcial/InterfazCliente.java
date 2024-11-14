package co.edu.uniquindio.Preparcial3.Sockets.EjercicioParcial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

class InterfazCliente extends JFrame {
    private JList<String> listTrabajos;
    private JTextArea areaAutores;
    private DefaultListModel<String> modeloLista;
    private List<TrabajoGrado> trabajos;
    private AppCliente cliente;

    public InterfazCliente(AppCliente cliente) {
        super("Trabajos de Grado - Universidad del Quindío");
        this.cliente = cliente;
        configurarVentana();
        iniciarComponentes();
        cargarTrabajos();
    }
    public InterfazCliente() {}

    private void configurarVentana() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
    }

    private void iniciarComponentes() {
        // Panel principal con división vertical
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

        // Panel izquierdo: Lista de trabajos
        JPanel panelTrabajos = new JPanel(new BorderLayout());
        JLabel labelTrabajos = new JLabel("Trabajos de Grado");
        labelTrabajos.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panelTrabajos.add(labelTrabajos, BorderLayout.NORTH);

        modeloLista = new DefaultListModel<>();
        listTrabajos = new JList<>(modeloLista);
        listTrabajos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollTrabajos = new JScrollPane(listTrabajos);
        panelTrabajos.add(scrollTrabajos, BorderLayout.CENTER);

        // Panel derecho: Área de autores
        JPanel panelAutores = new JPanel(new BorderLayout());
        JLabel labelAutores = new JLabel("Autores del Trabajo");
        labelAutores.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panelAutores.add(labelAutores, BorderLayout.NORTH);

        areaAutores = new JTextArea();
        areaAutores.setEditable(false);
        areaAutores.setMargin(new Insets(5, 5, 5, 5));
        JScrollPane scrollAutores = new JScrollPane(areaAutores);
        panelAutores.add(scrollAutores, BorderLayout.CENTER);

        // Configurar el split pane
        splitPane.setLeftComponent(panelTrabajos);
        splitPane.setRightComponent(panelAutores);
        splitPane.setDividerLocation(300);

        // Evento de selección de trabajo
        listTrabajos.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int index = listTrabajos.getSelectedIndex();
                if (index != -1) {
                    mostrarAutores(index);
                }
            }
        });

        add(splitPane);
    }

    private void cargarTrabajos() {
        trabajos = cliente.obtenerTrabajos();
        modeloLista.clear();
        for (TrabajoGrado trabajo : trabajos) {
            modeloLista.addElement(trabajo.getTitulo());
        }
    }

    private void mostrarAutores(int index) {
        List<Autor> autores = cliente.obtenerAutores(index);
        StringBuilder sb = new StringBuilder();

        if (autores.size() < 2) {
            sb.append("Error: Este trabajo debe tener al menos 2 autores.");
        } else {
            for (Autor autor : autores) {
                sb.append("Nombre: ").append(autor.getNombre()).append(" ").append(autor.getApellidos()).append("\n");
                sb.append("Cédula: ").append(autor.getCedula()).append("\n");
                sb.append("Programa: ").append(autor.getPrograma()).append("\n");
                sb.append("Título: ").append(autor.getTituloProf()).append("\n\n");
            }
        }

        areaAutores.setText(sb.toString());
        areaAutores.setCaretPosition(0);
    }
}