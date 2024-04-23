
package org.example.modelo.Repository;

import java.sql.SQLException;
import java.util.List;
import org.example.modelo.entity.Vacante;

public interface IVacanteRepository {
    // Metodo para crear un nuevo vacante
    Vacante crear(Vacante vacante);
    // Metodo para obtener una vacante por su ID
    Vacante obtenerPorId(int id);
    // Metodo para actualizar un vacante
    Vacante actualizar(Vacante vacante);
    // Metodo para eliminar un vacante por su ID
    void eliminar(int id);
    // Metodos para listar todos los vacantes
    List<Vacante> obtenerTodas();

    List<Vacante> buscarDestacadas();

    List<Vacante> buscarPorTexto(String texto);

    List<Vacante> filtrarPorCategoria(String categoria);
}
