package org.example.modelo.service.Categoria;

import org.example.modelo.Repository.ICategoriaRepository;
import org.example.modelo.entity.Categoria;

import java.sql.SQLException;
import java.util.List;

public class CategoriaService implements ICategoriaService {
    ICategoriaRepository repository;
    
    
    public CategoriaService(ICategoriaRepository repository){
        this.repository = repository;
    }
    
    
    @Override
    public Categoria crear(Categoria categoria) {
        repository.crear(categoria);
        return null;
    }

    @Override
    public Categoria obtenerPorId(int id) {

        return  repository.obtenerPorId(id);
    }

    @Override
    public Categoria actualizar(Categoria categoria) {
        repository.actualizar(categoria);
        return null;
    }

    @Override
    public void eliminar(int id) {
        repository.eliminar(id);

    }

    @Override
    public List<Categoria> obtenerTodas() {

        return  repository.obtenerTodas();
    }
}
