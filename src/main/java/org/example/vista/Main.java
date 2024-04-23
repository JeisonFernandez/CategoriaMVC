package org.example.vista;

import org.example.controler.CategoriasControler;
import org.example.controler.VacantesControler;
import org.example.modelo.Dao.CategoriasDao;
import org.example.modelo.Dao.VacantesDao;
import org.example.modelo.Repository.ICategoriaRepository;
import org.example.modelo.Repository.IVacanteRepository;
import org.example.modelo.db.ConexionDb;
import org.example.modelo.db.IConexionDb;
import org.example.modelo.service.Categoria.CategoriaService;
import org.example.modelo.service.Categoria.ICategoriaService;
import org.example.modelo.service.Vacante.IVacanteService;
import org.example.modelo.service.Vacante.VacanteService;



public class Main {
    public static void main(String[] args) {

        IConexionDb db = ConexionDb.getInstance();


        ICategoriaRepository categoriaRepository = new CategoriasDao(db);
        ICategoriaService categoriaService = new CategoriaService(categoriaRepository);
        CategoriasControler categoriasControler = new CategoriasControler(categoriaService);



        IVacanteRepository vacanteRepository = new VacantesDao(db);
        IVacanteService vacanteService = new VacanteService(vacanteRepository);
        VacantesControler vacantesControler = new VacantesControler(vacanteService);

        Vista vista = new Vista(categoriasControler, vacantesControler);
    }

}