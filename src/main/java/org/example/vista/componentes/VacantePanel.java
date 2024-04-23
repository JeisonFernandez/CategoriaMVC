package org.example.vista.componentes;

import org.example.controler.CategoriasControler;
import org.example.modelo.entity.Categoria;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Date;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.List;

public class VacantePanel extends JPanel {
    private JTextField nombreField = new JTextField(20);
    private JTextField descripcionField = new JTextField(20);
    private JSpinner fechaSpinner;
    private JFormattedTextField salarioField;
    private JComboBox<String> estatusComboBox = new JComboBox<>();
    private JComboBox<String> destacadoComboBox;
    private JTextField imagen = new JTextField(20);
    private JTextField detalles = new JTextField(20);
    private JComboBox<Categoria> idCategoriaComboBox;
    private CategoriasControler categoriasControler;

    public VacantePanel(CategoriasControler categoriasControler) {

        this.categoriasControler = categoriasControler;

        NumberFormat format = NumberFormat.getNumberInstance();
        NumberFormatter numFormatter = new NumberFormatter(format);
        numFormatter.setValueClass(Double.class);
        numFormatter.setAllowsInvalid(false);
        numFormatter.setMinimum(0.0);

        NumberFormatter intFormatter = new NumberFormatter(format);
        intFormatter.setValueClass(Integer.class);
        intFormatter.setAllowsInvalid(false);
        intFormatter.setMinimum(0);

        idCategoriaComboBox = new JComboBox<>();
        List<Categoria> categorias = this.categoriasControler.obtenerTodas();
        for (Categoria categoria : categorias) {
            idCategoriaComboBox.addItem(categoria);
        }

        idCategoriaComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Categoria) {
                    Categoria categoria = (Categoria) value;
                    setText(categoria.getNombre());
                }
                return this;
            }
        });

        salarioField = new JFormattedTextField(numFormatter);



        // Configuración del JSpinner para la fecha
        Date fechaInicial = new Date(System.currentTimeMillis()); // Fecha actual como valor inicial
        SpinnerDateModel model = new SpinnerDateModel(fechaInicial, null, null, Calendar.DAY_OF_MONTH);
        fechaSpinner = new JSpinner(model);

        // Formato de fecha personalizado (dd/MM/yyyy)
        JSpinner.DateEditor editor = new JSpinner.DateEditor(fechaSpinner, "dd/MM/yyyy");
        fechaSpinner.setEditor(editor);

        // Obtén el campo de texto del JSpinner
        JFormattedTextField textField = ((JSpinner.DefaultEditor) fechaSpinner.getEditor()).getTextField();

        // Agrega un KeyListener para validar en tiempo real
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                String currentText = textField.getText();

                if (!Character.isDigit(c) && c != '/' || currentText.length() >= 10) {
                    e.consume(); // Ignora caracteres no permitidos o si ya hay 10 caracteres
                    if (currentText.length() >= 10) {
                        JOptionPane.showMessageDialog(null, "La fecha debe tener exactamente 10 caracteres.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Solo se permiten números y el carácter '/'.");
                    }
                }
            }
        });


        estatusComboBox.addItem("Creada");
        estatusComboBox.addItem("Aprobada");

        // Configuracion de ComboBox destacado;
        String[] opciones = {"Sí", "No"};
        destacadoComboBox = new JComboBox<>(opciones);


        // Agregar un ActionListener para manejar la selección
        destacadoComboBox.addActionListener(e -> {
            String seleccion = (String) destacadoComboBox.getSelectedItem();
            int valor;
            if (seleccion.equals("Sí")) {
                valor = 1;
            } else {
                valor = 0;
            }
            // Aquí puedes usar el valor como desees (por ejemplo, guardarlo en una variable o llamar a un método).
        });


        setLayout(new GridLayout(0, 2, 10, 10)); // Dos columnas con espacio horizontal y vertical de 10 píxeles

        add(new JLabel("Nombre:"));
        add(nombreField);

        add(new JLabel("Descripción:"));
        add(descripcionField);

        add(new JLabel("Fecha (d/m/a):"));
        add(fechaSpinner);

        add(new JLabel("Salario:"));
        add(salarioField);

        add(new JLabel("Estatus:"));
        add(estatusComboBox);

        add(new JLabel("Destacado:"));
        add(destacadoComboBox);

        add(new JLabel("Imagen:"));
        add(imagen);

        add(new JLabel("Detalles:"));
        add(detalles);

        add(new JLabel("Categoría:"));
        add(idCategoriaComboBox);


    }

    public String getNombre() {
        return nombreField.getText();
    }

    public void setNombre(String nombre) {
        nombreField.setText(nombre);
    }

    public String getDescripcion() {
        return descripcionField.getText();
    }

    public void setDescripcion(String descripcion) {
        descripcionField.setText(descripcion);
    }

    public Date getFecha() {
        return new Date(((java.util.Date) fechaSpinner.getValue()).getTime());
    }

    public void setFecha(Date fecha) {
        fechaSpinner.setValue(new java.util.Date(fecha.getTime()));
    }

    public Double getSalario() {
        return (Double) salarioField.getValue();
    }

    public void setSalario(Double salario) {
        salarioField.setValue(salario);
    }

    public String getEstatus() {
        return (String) estatusComboBox.getSelectedItem();
    }

    public void setEstatus(String estatus) {
        estatusComboBox.setSelectedItem(estatus);
    }

    public Integer getDestacado() {
        String seleccion = (String) destacadoComboBox.getSelectedItem();
        return seleccion.equals("Sí") ? 1 : 0;
    }

    public void setDestacado(Integer destacado) {
        String seleccion = destacado == 1 ? "Sí" : "No";
        destacadoComboBox.setSelectedItem(seleccion);
    }

    public String getImagen() {
        return imagen.getText();
    }

    public void setImagen(String nombreImg) {
        imagen.setText(nombreImg);
    }

    public String getDetalles() {
        return detalles.getText();
    }

    public void setDetalles(String details) {
        detalles.setText(details);
    }

    public int getIdCategoriaSeleccionada() {
        Categoria categoria = (Categoria) idCategoriaComboBox.getSelectedItem();
        return categoria != null ? categoria.getId() : -1;
    }

    public void setIdCategoria(Categoria categoria) {
        idCategoriaComboBox.setSelectedItem(categoria);
    }
}
