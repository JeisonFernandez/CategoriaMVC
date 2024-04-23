
package org.example.vista.componentes;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.example.modelo.entity.Categoria;

public class CategoriaTableModel extends AbstractTableModel{
private List<Categoria> categorias;
    private String[] columnas = {"ID", "Nombre", "Descripción"};

    public CategoriaTableModel(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    @Override
    public int getRowCount() {
        return categorias.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Categoria categoria = categorias.get(rowIndex);
        switch (columnIndex) {
            case 0: return categoria.getId();
            case 1: return categoria.getNombre();
            case 2: return categoria.getDescripcion();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }
    
}
