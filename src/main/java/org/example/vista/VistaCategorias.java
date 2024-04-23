
package org.example.vista;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import org.example.controler.CategoriasControler;
import org.example.modelo.entity.Categoria;
import org.example.vista.componentes.CategoriaPanel;
import org.example.vista.componentes.CategoriaTableModel;

public class VistaCategorias extends JDialog{
    
    private JTable tablaCategorias = new JTable();
  private JButton botonAgregar = new JButton("Agregar");
  private JButton botonEditar = new JButton("Editar");
  private JButton botonEliminar = new JButton("Eliminar");
  private CategoriasControler categoriasControler;

  public VistaCategorias(CategoriasControler categoriasControler, Frame owner) {
      super(owner, true);

      this.categoriasControler = categoriasControler;

      setLayout(new BorderLayout());
      add(new JScrollPane(tablaCategorias), BorderLayout.CENTER);

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
        CategoriaPanel panel = new CategoriaPanel();
        int opcion;
        do {
            opcion = JOptionPane.showConfirmDialog(this, panel, "Agregar Categoría", JOptionPane.OK_CANCEL_OPTION);
            if (opcion == JOptionPane.OK_OPTION) {
                String nombre = panel.getNombre();
                String descripcion = panel.getDescripcion();

                if (nombre.isEmpty() || descripcion.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos antes de guardar.");
                }
            }
        } while (opcion == JOptionPane.OK_OPTION && (panel.getNombre().isEmpty() || panel.getDescripcion().isEmpty()));

        if (opcion == JOptionPane.OK_OPTION) {
            Categoria categoria = new Categoria();
            categoria.setNombre(panel.getNombre());
            categoria.setDescripcion(panel.getDescripcion());
            categoriasControler.crear(categoria);
            actualizarTabla();
        }
    }

    private void editar() {
        int filaSeleccionada = getTablaCategorias().getSelectedRow();
        if (filaSeleccionada != -1) {
            int idCategoria = (int) getTablaCategorias().getModel().getValueAt(filaSeleccionada, 0);
            Categoria categoria = categoriasControler.obtenerPorId(idCategoria);
            CategoriaPanel panel = new CategoriaPanel();
            panel.setNombre(categoria.getNombre());
            panel.setDescripcion(categoria.getDescripcion());

            int opcion;
            do {
                opcion = JOptionPane.showConfirmDialog(this, panel, "Editar Categoría", JOptionPane.OK_CANCEL_OPTION);
                if (opcion == JOptionPane.OK_OPTION) {
                    String nombre = panel.getNombre();
                    String descripcion = panel.getDescripcion();

                    if (nombre.isEmpty() || descripcion.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos antes de guardar.");
                    }
                }
            } while (opcion == JOptionPane.OK_OPTION && (panel.getNombre().isEmpty() || panel.getDescripcion().isEmpty()));

            if (opcion == JOptionPane.OK_OPTION) {
                categoria.setNombre(panel.getNombre());
                categoria.setDescripcion(panel.getDescripcion());
                categoriasControler.actualizar(categoria);
                actualizarTabla();
            }
        }
    }


    private void eliminar() {
        int filaSeleccionada = getTablaCategorias().getSelectedRow();
        if (filaSeleccionada != -1) {
            // Obtén el ID de la categoría desde el modelo de la tabla
            int idCategoria = (int) getTablaCategorias().getModel().getValueAt(filaSeleccionada, 0);
            int opcion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que quieres eliminar la categoría seleccionada?", "Eliminar Categoría", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                categoriasControler.eliminar(idCategoria);
                actualizarTabla();
            }
        }
    }

    public void actualizarTabla() {
        List<Categoria> categorias = categoriasControler.obtenerTodas();
        CategoriaTableModel model = new CategoriaTableModel(categorias);
        this.setTablaCategoriasModel(model);

        configurarAnchoColumnas();
    }

    // Método para configurar el ancho de las columnas
    private void configurarAnchoColumnas() {
        TableColumn columna;
        columna = tablaCategorias.getColumnModel().getColumn(0); // Obtiene la primera columna
        columna.setPreferredWidth(10); // Establece el ancho preferido a 100

        columna = tablaCategorias.getColumnModel().getColumn(1); // Obtiene la segunda columna
        columna.setPreferredWidth(150); // Establece el ancho preferido a 150

        columna = tablaCategorias.getColumnModel().getColumn(2); // Obtiene la segunda columna
        columna.setPreferredWidth(500);

        // Repite el proceso para las demás columnas según sea necesario
        // ...
    }

  public JTable getTablaCategorias() {
      return tablaCategorias;
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

  public void setTablaCategoriasModel(TableModel model) {
    tablaCategorias.setModel(model);
}
    
}
