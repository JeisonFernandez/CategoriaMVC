package org.example.controler;

import org.example.modelo.entity.Categoria;
import org.example.modelo.service.Categoria.ICategoriaService;

import java.util.List;


public class CategoriasControler {
    ICategoriaService service ;
    
    public CategoriasControler(ICategoriaService service){
        this.service = service;
    }

    public void crear(Categoria categoria){
        service.crear(categoria);
    }

    public void actualizar(Categoria categoria) {
        service.actualizar(categoria);
    }

    public void eliminar(int id) {
        service.eliminar(id);
    }
    public Categoria obtenerPorId(int id){

        return  service.obtenerPorId(id);
    }

    public List<Categoria> obtenerTodas(){
        return service.obtenerTodas();
    }

}
