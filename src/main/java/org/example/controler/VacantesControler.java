package org.example.controler;

import org.example.modelo.entity.Vacante;
import org.example.modelo.service.Vacante.IVacanteService;

import java.util.List;

public class VacantesControler {
    IVacanteService vacanteService;

    public VacantesControler(IVacanteService vacanteService){
        this.vacanteService = vacanteService;
    }

    public void crear(Vacante vacante){
        vacanteService.crear(vacante);
    }


    public Vacante obtenerPorId(int id){
        return vacanteService.obtenerPorId(id);
    }

    public void actualizar(Vacante vacante){
        vacanteService.actualizar(vacante);
    }

    public void eliminar(int id){
        vacanteService.eliminar(id);
    }


    public List<Vacante> obtenerTodas(){
        return vacanteService.obtenerTodas();
    }

    public List<Vacante> buscarDestacadas(){
        return vacanteService.buscarDestacadas();
    }

    public List<Vacante> buscarPorTexto(String texto){
        return vacanteService.buscarPorTexto(texto);
    }

    public List<Vacante> filtrarPorCategoria(String categoria){
        return vacanteService.filtrarPorCategoria(categoria);
    }
}
