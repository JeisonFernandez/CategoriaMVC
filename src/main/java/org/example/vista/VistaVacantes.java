package org.example.vista;

import org.example.controler.CategoriasControler;
import org.example.controler.VacantesControler;
import org.example.modelo.entity.Categoria;
import org.example.modelo.entity.Vacante;
import org.example.vista.componentes.CategoriaPanel;
import org.example.vista.componentes.CategoriaTableModel;
import org.example.vista.componentes.VacantePanel;
import org.example.vista.componentes.VacanteTableModel;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;

public class VistaVacantes extends JDialog {
    private JTable tablaVacantes = new JTable();
    private JButton botonAgregar = new JButton("Agregar");
    private JButton botonEditar = new JButton("Editar");
    private JButton botonEliminar = new JButton("Eliminar");
    private VacantesControler vacantesControler;
    private CategoriasControler categoriasControler;

    public VistaVacantes(VacantesControler vacantesControler, Frame owner, CategoriasControler categoriasControler) {
        super(owner, true);

        this.categoriasControler = categoriasControler;
        this.vacantesControler = vacantesControler;

        setLayout(new BorderLayout());
        add(new JScrollPane(tablaVacantes), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();

        botonAgregar.addActionListener(e -> agregar());
        botonEditar.addActionListener(e -> editar());
        botonEliminar.addActionListener(e -> eliminar());

        panelBotones.add(botonAgregar);
        panelBotones.add(botonEditar);
        panelBotones.add(botonEliminar);
        add(panelBotones, BorderLayout.SOUTH);

        setSize(800, 500);
        setLocationRelativeTo(owner);
        actualizarTabla();
    }

    private void agregar() {
        VacantePanel panel = new VacantePanel(categoriasControler);
        int opcion;
        do {
            opcion = JOptionPane.showConfirmDialog(this, panel, "Agregar Vacante", JOptionPane.OK_CANCEL_OPTION);
            if (opcion == JOptionPane.OK_OPTION) {
                String nombre = panel.getNombre();
                String descripcion = panel.getDescripcion();

                if (nombre.isEmpty() || descripcion.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos antes de guardar la vacante.");
                }
            }
        } while (opcion == JOptionPane.OK_OPTION && (panel.getNombre().isEmpty() || panel.getDescripcion().isEmpty()));

        if (opcion == JOptionPane.OK_OPTION) {
            Vacante vacante = new Vacante();
            vacante.setNombre(panel.getNombre());
            vacante.setDescripcion(panel.getDescripcion());
            vacante.setFecha(panel.getFecha());
            vacante.setSalario(panel.getSalario());
            vacante.setEstatus(panel.getEstatus());
            vacante.setDestacado(panel.getDestacado());
            vacante.setImagen(panel.getImagen());
            vacante.setDetalles(panel.getDetalles());
            Categoria categoria = new Categoria();
            categoria.setId(panel.getIdCategoriaSeleccionada());
            vacante.setCategoria(categoria);

            vacantesControler.crear(vacante);
            actualizarTabla();
        }
    }

    private void editar() {
        int filaSeleccionada = getTablaVacantes().getSelectedRow();
        if (filaSeleccionada != -1) {
            // Obtiene el ID de la vacante desde el modelo de la tabla
            int idVacante = (int) getTablaVacantes().getModel().getValueAt(filaSeleccionada, 0);
            Vacante vacante = vacantesControler.obtenerPorId(idVacante);
            VacantePanel panel = new VacantePanel(categoriasControler);

            panel.setNombre(vacante.getNombre());
            panel.setDescripcion(vacante.getDescripcion());
            panel.setFecha(vacante.getFecha());
            panel.setSalario(vacante.getSalario());
            panel.setEstatus(vacante.getEstatus());
            panel.setDestacado(vacante.getDestacado());
            panel.setImagen(vacante.getImagen());
            panel.setDetalles(vacante.getDetalles());
            panel.setIdCategoria(vacante.getCategoria());

            int opcion;
            do {
                opcion = JOptionPane.showConfirmDialog(this, panel, "Editar Vacante", JOptionPane.OK_CANCEL_OPTION);
                if (opcion == JOptionPane.OK_OPTION) {
                    String nombre = panel.getNombre();
                    String descripcion = panel.getDescripcion();
                    // Agrega validaciones adicionales según tus necesidades

                    if (nombre.isEmpty() || descripcion.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos antes de guardar la vacante.");
                    }
                }
            } while (opcion == JOptionPane.OK_OPTION && (panel.getNombre().isEmpty() || panel.getDescripcion().isEmpty()));

            if (opcion == JOptionPane.OK_OPTION) {
                vacante.setId(idVacante);
                vacante.setNombre(panel.getNombre());
                vacante.setDescripcion(panel.getDescripcion());
                vacante.setFecha(panel.getFecha());
                vacante.setSalario(panel.getSalario());
                vacante.setEstatus(panel.getEstatus());
                vacante.setDestacado(panel.getDestacado());
                vacante.setImagen(panel.getImagen());
                vacante.setDetalles(panel.getDetalles());
                Categoria categoria = new Categoria();
                categoria.setId(panel.getIdCategoriaSeleccionada());
                vacante.setCategoria(categoria);

                vacantesControler.actualizar(vacante);
                actualizarTabla();
            }
        }
    }

    private void eliminar() {
        int filaSeleccionada = getTablaVacantes().getSelectedRow();
        if (filaSeleccionada != -1) {
            // Obtiene el ID de la vacante desde el modelo de la tabla
            int idVacante = (int) getTablaVacantes().getModel().getValueAt(filaSeleccionada, 0);
            int opcion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que quieres eliminar la vacante seleccionada?", "Eliminar Vacante", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                vacantesControler.eliminar(idVacante);
                actualizarTabla();
            }
        }
    }

    public void actualizarTabla() {
        List<Vacante> vacantes = vacantesControler.obtenerTodas();
        VacanteTableModel model = new VacanteTableModel(vacantes);
        this.setTablaVacantesModel(model);

        configurarAnchoColumnas();
    }

    // Método para configurar el ancho de las columnas
    private void configurarAnchoColumnas() {
        TableColumn columna;
        columna = tablaVacantes.getColumnModel().getColumn(0); // Obtiene la primera columna
        columna.setPreferredWidth(30); // Establece el ancho preferido a 100

        columna = tablaVacantes.getColumnModel().getColumn(1); // Obtiene la segunda columna
        columna.setPreferredWidth(200); // Establece el ancho preferido a 150

        columna = tablaVacantes.getColumnModel().getColumn(2); // Obtiene la segunda columna
        columna.setPreferredWidth(100);

        columna = tablaVacantes.getColumnModel().getColumn(6); // Obtiene la segunda columna
        columna.setPreferredWidth(90);

        // Repite el proceso para las demás columnas según sea necesario
        // ...
    }

    public JTable getTablaVacantes() {
        return tablaVacantes;
    }

    public JButton getBotonAgregar() {
        return botonAgregar;
    }

    public JButton getBotonEditar() {
        return botonEditar;
    }

    public JButton getBotonEliminar() {
        return botonEliminar;
    }

    public void setTablaVacantesModel(TableModel model) {
        tablaVacantes.setModel(model);
    }


}
