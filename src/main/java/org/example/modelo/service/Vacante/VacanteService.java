
package org.example.modelo.service.Vacante;

import java.sql.SQLException;
import java.util.List;
import org.example.modelo.Repository.IVacanteRepository;
import org.example.modelo.entity.Vacante;


public class VacanteService implements IVacanteService{
    
    IVacanteRepository repository;

    public VacanteService(IVacanteRepository repository) {
        this.repository = repository;
    }
    
    

    @Override
    public Vacante crear(Vacante vacante) {
        this.repository.crear(vacante);
        return null;
    }

    @Override
    public Vacante obtenerPorId(int id) {
        return repository.obtenerPorId(id);
    }

    @Override
    public Vacante actualizar(Vacante vacante) {
        repository.actualizar(vacante);
        return null;
    }

    @Override
    public void eliminar(int id) {
        repository.eliminar(id);
    }

    @Override
    public List<Vacante> obtenerTodas() {
        return repository.obtenerTodas();
    }

    @Override
    public List<Vacante> buscarDestacadas() {
        return repository.buscarDestacadas();
    }

    @Override
    public List<Vacante> buscarPorTexto(String texto) {
        return repository.buscarPorTexto(texto);
    }

    @Override
    public List<Vacante> filtrarPorCategoria(String categoria) {
        return repository.filtrarPorCategoria(categoria);
    }

}
