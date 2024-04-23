package org.example.vista.componentes;

import org.example.controler.CategoriasControler;
import org.example.controler.VacantesControler;
import org.example.modelo.entity.Categoria;
import org.example.modelo.entity.Vacante;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TablaVacantes extends JTable {
    private DefaultTableModel modeloTabla;
    private VacantesControler vacantesControler;

    public TablaVacantes(VacantesControler vacantesControler) {
        this.vacantesControler = vacantesControler;

        modeloTabla = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                // Todas las celdas no editables
                return false;
            }
        };

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Verificar si es doble clic
                if (e.getClickCount() == 2) {
                    // Obtener el índice de la fila seleccionada
                    int filaSeleccionada = getSelectedRow();
                    if (filaSeleccionada != -1) {

                        int idRegistro = (Integer) getModel().getValueAt(filaSeleccionada, 0);

                        // crear y mostrar tu JDialog
                        JDialog dialogo = new JDialog((Frame) SwingUtilities.getWindowAncestor(TablaVacantes.this), "Detalles de la Vacante", true);
                        JButton postulacion = new JButton("Postular");
                        JTextArea areaTextoDetalles = new JTextArea(5, 20);
                        areaTextoDetalles.setEditable(false); // Hacer que el JTextArea no sea editable
                        JScrollPane scrollPane = new JScrollPane(areaTextoDetalles); // Agregar el JTextArea a un JScrollPane

                        areaTextoDetalles.setFont(new Font("Arial", Font.BOLD, 12));
                        areaTextoDetalles.setLineWrap(true);
                        areaTextoDetalles.setWrapStyleWord(true);

                        // Obtener los detalles del registro usando el ID y el controlador
                        Vacante detalles = vacantesControler.obtenerPorId(idRegistro);
                        areaTextoDetalles.setText(detalles.getDetalles()); // Mostrar los detalles en el JTextArea

                        dialogo.setLayout(new BorderLayout());
                        JLabel texto = new JLabel("Detalles", SwingConstants.CENTER);
                        texto.setFont(new Font("Arial", Font.BOLD, 15));

                        dialogo.add(texto, BorderLayout.NORTH);

                        dialogo.add(scrollPane, BorderLayout.CENTER); // Agregar el JScrollPane al JDialog
                        dialogo.add(postulacion, BorderLayout.SOUTH);
                        dialogo.setSize(400, 300);
                        dialogo.setLocationRelativeTo((Frame) SwingUtilities.getWindowAncestor(TablaVacantes.this));

                        // Agregar ActionListener al botón
                        postulacion.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                // Mostrar mensaje de éxito
                                JOptionPane.showMessageDialog(dialogo, "Te has postulado con éxito.", "Postulación Exitosa", JOptionPane.INFORMATION_MESSAGE);
                            }
                        });

                        dialogo.setVisible(true);
                    }
                }
            }
        });

        setModel(modeloTabla);
        modeloTabla.addColumn("Id");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Descripcion");
        modeloTabla.addColumn("Fecha");
        modeloTabla.addColumn("Salario");
        //modeloTabla.addColumn("Estatus");
        //modeloTabla.addColumn("Destacado");
        modeloTabla.addColumn("Imagen");
        modeloTabla.addColumn("Detalles");
        modeloTabla.addColumn("Categoria");

        getColumnModel().getColumn(0).setPreferredWidth(150); // Nombre
        getColumnModel().getColumn(1).setPreferredWidth(260); // Descripcion
        getColumnModel().getColumn(2).setPreferredWidth(70); // Fecha
        getColumnModel().getColumn(3).setPreferredWidth(50); // Salario
        getColumnModel().getColumn(4).setPreferredWidth(80); // Imagen
        getColumnModel().getColumn(5).setPreferredWidth(100); // Detalles
        getColumnModel().getColumn(6).setPreferredWidth(200); // Categoria
    }

    public void setVacantesDestacadas(Object[][] vacantesDestacadas) {
        modeloTabla.setRowCount(0);
        for (Object[] vacante : vacantesDestacadas) {
            modeloTabla.addRow(vacante);
        }
    }
}
