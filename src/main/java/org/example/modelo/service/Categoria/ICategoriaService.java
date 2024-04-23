package org.example.modelo.service.Categoria;

import org.example.modelo.entity.Categoria;

import java.sql.SQLException;
import java.util.List;

public interface ICategoriaService {

    // Método para crear una nueva categoría
    Categoria crear(Categoria categoria);

    // Método para obtener una categoría por su ID
    Categoria obtenerPorId(int id);

    // Método para actualizar una categoría existente
    Categoria actualizar(Categoria categoria);

    // Método para eliminar una categoría por su ID
    void eliminar(int id);

    // Método para obtener todas las categorías
    List<Categoria> obtenerTodas();
}
