
package org.example.modelo.Dao;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.example.controler.CategoriasControler;
import org.example.modelo.Repository.IVacanteRepository;
import org.example.modelo.db.ConexionDb;
import org.example.modelo.db.IConexionDb;
import org.example.modelo.entity.Categoria;
import org.example.modelo.entity.Vacante;

public class VacantesDao implements IVacanteRepository{
    
    IConexionDb conexion;

    public VacantesDao(IConexionDb conexionDb) {
        this.conexion = conexionDb;
    }
    
    

    @Override
    public Vacante crear(Vacante vacante) {
         try {
            // Se conecta a la base de datos empleosdb
            conexion.conectar();
            // Se crea una sentencia SQL para insertar una nueva vacante
            String sql = "INSERT INTO vacantes (nombre, descripcion, fecha, salario, estatus, destacado, imagen, detalles, idCategoria) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            // Se crea un objeto PreparedStatement a partir de la conexión y la sentencia SQL
            PreparedStatement ps = conexion.getConnexion().prepareStatement(sql);
            
            // Se asignan los valores de los atributos de la vacante a los signos de interrogación
            ps.setString(1, vacante.getNombre());
            ps.setString(2, vacante.getDescripcion());
            ps.setDate(3, vacante.getFecha());
            ps.setDouble(4, vacante.getSalario());
            ps.setString(5, vacante.getEstatus());
            ps.setInt(6, vacante.getDestacado());
            ps.setString(7, vacante.getImagen());
            ps.setString(8, vacante.getDetalles());
            ps.setInt(9, vacante.getCategoria().getId());
           
            
            // Se ejecuta la sentencia SQL
            ps.executeUpdate();
            // Se imprime un mensaje indicando que se ha guardado la vacante
            System.out.println("Se ha guardado la vacante: " + vacante);
            // Se cierra el PreparedStatement
            ps.close();
            // Se desconecta de la base de datos empleosdb
            this.conexion.desconectar();
        } catch (SQLException e) {
            // Se imprime un mensaje indicando que ha ocurrido un error
            System.out.println("Ha ocurrido un error al guardar la vacante: " + vacante);
            // Se imprime la traza del error
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Vacante obtenerPorId(int id) {
        //se crea una variable de tipo vacante
        Vacante vacante = null;
        try {
            // Se conecta a la base de datos empleosdb
            this.conexion.conectar();
            // Se crea una sentencia SQL para seleccionar una vacante por su id
            String sql = "SELECT * FROM  vacantes WHERE id = ?";
            // Se crea un objeto PreparedStatement a partir de la conexión y la sentencia SQL
            PreparedStatement ps = conexion.getConnexion().prepareStatement(sql);
            // Se asigna el valor del id al signo de interrogación
            ps.setInt(1, id);
            // Se ejecuta la sentencia SQL y se obtiene un objeto ResultSet con el resultado
            ResultSet rs = ps.executeQuery();
            // Si el ResultSet tiene una fila, se crea una vacante con los valores obtenidos
            if (rs.next()) {
                // Se obtienen los valores de las columnas *
                int idVacante = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                Date fecha = rs.getDate("fecha");
                double salario = rs.getDouble("salario");
                String estatus = rs.getString("estatus");
                int destacado = rs.getInt("destacado");
                String imagen = rs.getString("imagen");
                String detalles = rs.getString("detalles");
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("idCategoria"));
                
                
                // Se crea una vacante con los valores obtenidos y el id
                vacante = new Vacante(idVacante, nombre, descripcion, fecha, salario, estatus, destacado, imagen, detalles, categoria);
            }
            // Se cierra el ResultSet y el PreparedStatement
            rs.close();
            ps.close();
            // Se desconecta de la base de datos empleosdb
            this.conexion.desconectar();
        } catch (SQLException e) {
            // Se imprime un mensaje indicando que ha ocurrido un error
            System.out.println("Ha ocurrido un error al obtener la vacante con id: " + id);
            // Se imprime la traza del error
            e.printStackTrace();
        }
        // Se devuelve la categoria
        return vacante;
    }

    @Override
    public Vacante actualizar(Vacante vacante) {
        try {
            // Se conecta a la base de datos empleosdb
            this.conexion.conectar();
            // Se crea una sentencia SQL para actualizar una vacante
            String sql = "UPDATE vacantes SET nombre = ?, descripcion = ? , fecha = ?, salario = ?, estatus = ?, destacado = ?, imagen = ?, detalles = ?, idCategoria = ? WHERE id = ?";
            // Se crea un objeto PreparedStatement a partir de la conexión y la sentencia SQL
            PreparedStatement ps = conexion.getConnexion().prepareStatement(sql);
            // Se asignan los valores de los atributos de la categoria a los signos de interrogación

            ps.setString(1, vacante.getNombre());
            ps.setString(2, vacante.getDescripcion());
            ps.setDate(3, vacante.getFecha());
            ps.setDouble(4, vacante.getSalario());
            ps.setString(5, vacante.getEstatus());
            ps.setInt(6, vacante.getDestacado());
            ps.setString(7, vacante.getImagen());
            ps.setString(8, vacante.getDetalles());
            ps.setInt(9, vacante.getCategoria().getId());
            ps.setInt(10, vacante.getId());

            // Se ejecuta la sentencia SQL
            ps.executeUpdate();
            // Se imprime un mensaje indicando que se ha actualizado la vacante
            System.out.println("Se ha actualizado la vacante: " + vacante);
            // Se cierra el PreparedStatement
            ps.close();
            // Se desconecta de la base de datos empleosdb
            this.conexion.desconectar();
        } catch (SQLException e) {
            // Se imprime un mensaje indicando que ha ocurrido un error
            System.out.println("Ha ocurrido un error al actualizar la vacante: " + vacante);
            // Se imprime la traza del error
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void eliminar(int id) {
        try {
            // Se conecta a la base de datos empleosdb
            this.conexion.conectar();
            // Se crea una sentencia SQL para eliminar
            String sql = "DELETE FROM vacantes WHERE id = ?";
            // Se crea un objeto PreparedStatement a partir de la conexión y la sentencia SQL
            PreparedStatement ps = conexion.getConnexion().prepareStatement(sql);
            // Se asigna el valor del id a el signo de interrogación
            ps.setInt(1, id);
            // Se ejecuta la sentencia SQL
            ps.executeUpdate();
            // Se imprime un mensaje indicando que se ha eliminado
            System.out.println("Se ha eliminado la vacante con id: " + id);
            // Se cierra el PreparedStatement
            ps.close();
            // Se desconecta de la base de datos empleosdb
            this.conexion.desconectar();
        } catch (SQLException e) {
            // Se imprime un mensaje indicando que ha ocurrido un error
            System.out.println("Ha ocurrido un error al eliminar la vacante con id: " + id);
            // Se imprime la traza del error
            e.printStackTrace();
        }
    }

    @Override
    public List<Vacante> obtenerTodas() {
        // Se crea una lista vacía de vacantes
        List<Vacante> listaVacantes = new ArrayList<>();
        try {
            // Se conecta a la base de datos empleosdb
            this.conexion.conectar();
            // Se crea una sentencia SQL para seleccionar todas las vacantes
            String sql = "SELECT v.*, c.nombre as nombre_categoria FROM vacantes v INNER JOIN categorias c WHERE v.idCategoria = c.id";
            // Se crea un objeto Statement a partir de la conexión
            Statement st = this.conexion.getConnexion().createStatement();
            // Se ejecuta la sentencia SQL y se obtiene un objeto ResultSet con los resultados
            ResultSet rs = st.executeQuery(sql);
            // Se recorre el ResultSet y se crea una vacante por cada fila
            while (rs.next()) {
                // Se obtienen los valores de las columnas id, nombre y descripcion etc..
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                Date fecha = rs.getDate("fecha");
                double salario = rs.getDouble("salario");
                int  destacado = rs.getInt("destacado");
                String estatus = rs.getString("estatus");
                String imagen = rs.getString("imagen");
                String detalles = rs.getString("detalles");
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("idCategoria"));
                categoria.setNombre(rs.getString("nombre_categoria"));

                // Se crea vacante con los valores obtenidos
                Vacante vacante = new Vacante(id,nombre,descripcion,fecha,salario,estatus, destacado,imagen,detalles,categoria);
                // Se añade la categoria a la lista de categorias
                listaVacantes.add(vacante);
            }
            // Se cierra el ResultSet y el Statement
            rs.close();
            st.close();
            // Se desconecta de la base de datos empleosdb
            this.conexion.desconectar();
        } catch (SQLException e) {
            // Se imprime un mensaje indicando que ha ocurrido un error
            System.out.println("Ha ocurrido un error al listar las vacantes");
            // Se imprime la traza del error
            e.printStackTrace();
        }
        // Se devuelve la lista de vacantes
        return listaVacantes;
    }

    @Override
    public List<Vacante> buscarDestacadas() {
        List<Vacante> listaDestacada = new ArrayList<>();
        try {
            // Conecta a la base de datos empleosdb
            this.conexion.conectar();

            // Crea la sentencia SQL parametrizada
            String sql = "SELECT v.*, c.nombre AS nombre_categoria FROM vacantes v INNER JOIN categorias c WHERE v.destacado = ? AND v.estatus = ? AND v.idCategoria = c.id";
            PreparedStatement ps = this.conexion.getConnexion().prepareStatement(sql);

            // Asigna los valores a los parámetros
            ps.setInt(1, 1); // Valor para destacado
            ps.setString(2, "Aprobada"); // Valor para estatus

            // Ejecuta la consulta y obtiene el ResultSet
            ResultSet rs = ps.executeQuery();

            // Recorre el ResultSet y crea objetos Vacante
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                Date fecha = rs.getDate("fecha");
                double salario = rs.getDouble("salario");
                int destacado = rs.getInt("destacado");
                String estatus = rs.getString("estatus");
                String imagen = rs.getString("imagen");
                String detalles = rs.getString("detalles");
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("idCategoria"));
                categoria.setNombre(rs.getString("nombre_categoria"));


                // Crea una instancia de Vacante con los valores obtenidos
                Vacante vacante = new Vacante(id, nombre, descripcion, fecha, salario, estatus, destacado, imagen, detalles, categoria);

                // Agrega la vacante a la lista
                listaDestacada.add(vacante);
            }

            // Cierra el ResultSet y el Statement
            rs.close();
            ps.close();
        } catch (SQLException e) {
            // Maneja las excepciones
            e.printStackTrace();
        } finally {
            // Cierra la conexión
            this.conexion.desconectar();
        }

        return listaDestacada;
    }

    @Override
    public List<Vacante> buscarPorTexto(String texto) {
        List<Vacante> vacantesEncontradas = new ArrayList<>();
        try {
            conexion.conectar();
            String sql = "SELECT v.*, c.nombre AS nombre_categoria FROM vacantes v INNER JOIN categorias c ON v.idCategoria = c.id WHERE (v.nombre LIKE ? OR v.descripcion LIKE ?)";
            PreparedStatement ps = conexion.getConnexion().prepareStatement(sql);
            ps.setString(1, "%" + texto + "%");
            ps.setString(2, "%" + texto + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Aquí debes asignar los valores del ResultSet a los atributos de la vacante
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                Date fecha = rs.getDate("fecha");
                double salario = rs.getDouble("salario");
                int destacado = rs.getInt("destacado");
                String estatus = rs.getString("estatus");
                String imagen = rs.getString("imagen");
                String detalles = rs.getString("detalles");
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("idCategoria"));
                categoria.setNombre(rs.getString("nombre_categoria"));


                // Crea una instancia de Vacante con los valores obtenidos
                Vacante vacante = new Vacante(id, nombre, descripcion, fecha, salario, estatus, destacado, imagen, detalles, categoria);

                // ... otros atributos ...
                vacantesEncontradas.add(vacante);
            }
            rs.close();
            ps.close();
            conexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vacantesEncontradas;
    }

    @Override
    public List<Vacante> filtrarPorCategoria(String categoria) {
        List<Vacante> vacantesPorCategoria = new ArrayList<>();
        try {
            conexion.conectar();
            String sql = "SELECT v.*, c.nombre AS nombre_categoria FROM vacantes v INNER JOIN categorias c ON v.idCategoria = c.id WHERE c.nombre = ?";
            PreparedStatement ps = conexion.getConnexion().prepareStatement(sql);
            ps.setString(1, categoria);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Aquí debes asignar los valores del ResultSet a los atributos de la vacante
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                Date fecha = rs.getDate("fecha");
                double salario = rs.getDouble("salario");
                int destacado = rs.getInt("destacado");
                String estatus = rs.getString("estatus");
                String imagen = rs.getString("imagen");
                String detalles = rs.getString("detalles");
                Categoria categoriaObj = new Categoria();
                categoriaObj.setId(rs.getInt("idCategoria"));
                categoriaObj.setNombre(rs.getString("nombre_categoria"));


                // Crea una instancia de Vacante con los valores obtenidos
                Vacante vacante = new Vacante(id, nombre, descripcion, fecha, salario, estatus, destacado, imagen, detalles, categoriaObj);

                vacantesPorCategoria.add(vacante);
            }
            rs.close();
            ps.close();
            conexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vacantesPorCategoria;
    }


}
