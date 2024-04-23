package org.example.vista;

import org.example.controler.CategoriasControler;
import org.example.controler.VacantesControler;
import org.example.modelo.entity.Vacante;
import org.example.vista.componentes.BarraBusqueda;
import org.example.vista.componentes.SelectorCategorias;
import org.example.vista.componentes.TablaVacantes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;


public class Vista {
    private JFrame frame;
    private BarraBusqueda barraBusqueda;
    private SelectorCategorias selectorCategorias;
    private TablaVacantes tablaVacantes;
    private CategoriasControler categoriasControler;
    private VacantesControler vacantesControler;
    private JPanel panelNorth = new JPanel();
    private JPanel panelCenter = new JPanel();
    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("Menu");
    JMenuItem empleos = new JMenuItem("Empleos");
    JMenuItem categorias = new JMenuItem("Categorias");
    
    
    public Vista(CategoriasControler categoriasControler, VacantesControler vacantesControler) {

        this.categoriasControler = categoriasControler;
        this.vacantesControler = vacantesControler;

        frame = new JFrame("Portal de Empleos");
        frame.setLayout(new BorderLayout());
            
       
        categorias.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarVacantesDestacadas();
                VistaCategorias vistaCategorias = new VistaCategorias(categoriasControler, frame);
                vistaCategorias.setVisible(true);
                mostrarVacantesDestacadas();
            }
        });

        empleos.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarVacantesDestacadas();
                VistaVacantes vistaVacantes = new VistaVacantes(vacantesControler, frame, categoriasControler);
                vistaVacantes.setVisible(true);
                mostrarVacantesDestacadas();
            }
        });
        
        menu.add(empleos);
        menu.add(categorias);
        menuBar.add(menu);
        
        
        // Inicializar el selector de categorías y la barra de búsqueda
        this.selectorCategorias = new SelectorCategorias();
        this.barraBusqueda = new BarraBusqueda(selectorCategorias);

        // Inicializar la tabla de vacantes
        this.tablaVacantes = new TablaVacantes(vacantesControler);



        panelNorth.setBackground(Color.BLACK);
        frame.setBackground(Color.BLACK);

        // Agregar la barra de búsqueda y la tabla al frame
        frame.setJMenuBar(menuBar);
        panelNorth.add(barraBusqueda);
        frame.add(panelNorth, BorderLayout.NORTH);
        // panelCenter.add(new JScrollPane(tablaVacantes));
        tablaVacantes.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tablaVacantes.getTableHeader().setForeground(Color.WHITE);
        tablaVacantes.getTableHeader().setOpaque(false);
        tablaVacantes.getTableHeader().setBackground(Color.BLACK);
        tablaVacantes.setFont(new Font("Arial", Font.BOLD, 12));
        tablaVacantes.setForeground(Color.WHITE);
        tablaVacantes.setBackground(Color.BLACK);

        frame.add(new JScrollPane(tablaVacantes), BorderLayout.CENTER);


        // Agregar ActionListener al botón de búsqueda
        barraBusqueda.agregarListenerBuscar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarBusqueda();
            }
        });

        // Agregar ActionListener al selector de categorías
        selectorCategorias.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrarPorCategoria();
            }
        });

        cargarCategorias();
        mostrarVacantesDestacadas();

        // Configurar tamaño y visibilidad
        frame.setBackground(Color.BLACK);
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Métodos getters para los componentes
    public BarraBusqueda getBarraBusqueda() {
        return barraBusqueda;
    }

    public TablaVacantes getTablaVacantes() {
        return tablaVacantes;
    }

    public void mostrarVacantesDestacadas() {
        List<Vacante> vacantesDestacadas = vacantesControler.buscarDestacadas();
        DefaultTableModel modeloTabla = (DefaultTableModel) tablaVacantes.getModel();
        modeloTabla.setRowCount(0); // Limpiar la tabla antes de agregar nuevas filas

        for (Vacante vacante : vacantesDestacadas) {
            Object[] fila = new Object[]{
                    vacante.getId(),
                    vacante.getNombre(),
                    vacante.getDescripcion(),
                    vacante.getFecha(),
                    vacante.getSalario(),
                    //vacante.getEstatus(),
                    //vacante.getDestacado(),
                    vacante.getImagen(),
                    vacante.getDetalles(),
                    vacante.getCategoria().getNombre()
            };
            modeloTabla.addRow(fila);
        }
    }

    private void realizarBusqueda() {
        String textoBusqueda = barraBusqueda.getTextoBusqueda();
        if(textoBusqueda.isEmpty()) {
            mostrarVacantesDestacadas(); // Mostrar todas las vacantes si no hay texto de búsqueda
        } else {
            List<Vacante> resultadoBusqueda = vacantesControler.buscarPorTexto(textoBusqueda);
            mostrarVacantes(resultadoBusqueda);
        }
    }


    private void filtrarPorCategoria() {
        String categoriaSeleccionada = (String) selectorCategorias.getSelectedItem();
        // Verificar si se seleccionó "Todas las categorías" o alguna opción similar
        if(categoriaSeleccionada.equals("Todas las categorías")) {
            mostrarVacantesDestacadas(); // Mostrar todas las vacantes
        } else {
            System.out.println(categoriaSeleccionada);
            List<Vacante> vacantesPorCategoria = vacantesControler.filtrarPorCategoria(categoriaSeleccionada);
            mostrarVacantes(vacantesPorCategoria);
        }
    }

    public void cargarCategorias() {
        List<String> nombresCategorias = categoriasControler.obtenerTodas().stream()
                .map(categoria -> categoria.getNombre())
                .collect(Collectors.toList());

        // Agregar "Todas las categorías" al inicio de la lista
        nombresCategorias.add(0, "Todas las categorías");

        selectorCategorias.setCategorias(nombresCategorias.toArray(new String[0]));
    }

    private void mostrarVacantes(List<Vacante> vacantes) {
        DefaultTableModel modeloTabla = (DefaultTableModel) tablaVacantes.getModel();
        modeloTabla.setRowCount(0); // Limpiar la tabla antes de agregar nuevas filas

        for (Vacante vacante : vacantes) {
            Object[] fila = new Object[]{
                    vacante.getId(),
                    vacante.getNombre(),
                    vacante.getDescripcion(),
                    vacante.getFecha(),
                    vacante.getSalario(),
                    //vacante.getEstatus(),
                    //vacante.getDestacado(),
                    vacante.getImagen(),
                    vacante.getDetalles(),
                    vacante.getCategoria().getNombre()
            };
            modeloTabla.addRow(fila);
        }
    }
}
