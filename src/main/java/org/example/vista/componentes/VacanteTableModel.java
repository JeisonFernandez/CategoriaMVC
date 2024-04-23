package org.example.vista.componentes;

import org.example.modelo.entity.Categoria;
import org.example.modelo.entity.Vacante;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class VacanteTableModel extends AbstractTableModel {
    private List<Vacante> vacantes;
    private String[] columnas = {"ID", "Nombre", "Descripción", "Fecha", "Salario", "Estatus", "Destacado", "Imagen", "Detalles", "Categoria"};

    public VacanteTableModel(List<Vacante> vacantes) {
        this.vacantes = vacantes;
    }

    @Override
    public int getRowCount() {
        return vacantes.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Vacante vacante = vacantes.get(rowIndex);
        switch (columnIndex) {
            case 0: return vacante.getId();
            case 1: return vacante.getNombre();
            case 2: return vacante.getDescripcion();
            case 3: return vacante.getFecha();
            case 4: return vacante.getSalario();
            case 5: return vacante.getEstatus();
            case 6: return vacante.getDestacado();
            case 7: return vacante.getImagen();
            case 8: return vacante.getDetalles();
            case 9: return vacante.getCategoria().getNombre();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }
}
