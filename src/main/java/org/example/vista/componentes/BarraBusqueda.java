package org.example.vista.componentes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BarraBusqueda extends JPanel {
    private JTextField campoTexto;
    private JButton botonBuscar;
    private SelectorCategorias selectorCategorias;

    public BarraBusqueda(SelectorCategorias selectorCategorias) {
        this.selectorCategorias = selectorCategorias;
        setLayout(new FlowLayout(FlowLayout.LEFT));
        campoTexto = new JTextField(20);
        botonBuscar = new JButton("Buscar");

        add(campoTexto);
        add(this.selectorCategorias);
        add(botonBuscar);
    }

    public String getTextoBusqueda() {
        return campoTexto.getText();
    }

    public void agregarListenerBuscar(ActionListener listener) {
        botonBuscar.addActionListener(listener);
    }
}
