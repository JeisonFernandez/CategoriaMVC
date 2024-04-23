
package org.example.vista.componentes;

import javax.swing.*;

public class CategoriaPanel extends JPanel{
    private JTextField nombreField = new JTextField(20);
    private JTextField descripcionField = new JTextField(20);

    public CategoriaPanel() {
      add(new JLabel("Nombre:"));
      add(nombreField);
      add(new JLabel("Descripción:"));
      add(descripcionField);
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
    
}
