package org.example.vista.componentes;

import javax.swing.*;

public class SelectorCategorias extends JComboBox<String> {

    public SelectorCategorias() {
        super(new DefaultComboBoxModel<>());
        // El modelo se llenará dinámicamente desde el controlador
    }

    public void setCategorias(String[] categorias) {
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) getModel();
        model.removeAllElements();
        for (String categoria : categorias) {
            model.addElement(categoria);
        }
    }
}
