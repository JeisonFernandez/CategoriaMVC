package org.example.modelo.Dao;

import org.example.modelo.Repository.ICategoriaRepository;
import org.example.modelo.db.IConexionDb;
import org.example.modelo.entity.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriasDao implements ICategoriaRepository {

    IConexionDb conexion;

    public CategoriasDao(IConexionDb conexionDb) {
        this.conexion = conexionDb;
    }

    @Override
    public Categoria crear(Categoria categoria) {
        try {
            // Se conecta a la base de datos empleosdb
            conexion.conectar();
            // Se crea una sentencia SQL para insertar una nueva categoria
            String sql = "INSERT INTO categorias (nombre, descripcion) VALUES (?, ?)";
            // Se crea un objeto PreparedStatement a partir de la conexión y la sentencia SQL
            PreparedStatement ps = conexion.getConnexion().prepareStatement(sql);
            // Se asignan los valores de los atributos de la categoria a los signos de interrogación
            ps.setString(1, categoria.getNombre());
            ps.setString(2, categoria.getDescripcion());
            // Se ejecuta la sentencia SQL
            ps.executeUpdate();
            // Se imprime un mensaje indicando que se ha guardado la categoria
            System.out.println("Se ha guardado la categoria: " + categoria);
            // Se cierra el PreparedStatement
            ps.close();
            // Se desconecta de la base de datos empleosdb
            this.conexion.desconectar();
        } catch (SQLException e) {
            // Se imprime un mensaje indicando que ha ocurrido un error
            System.out.println("Ha ocurrido un error al guardar la categoria: " + categoria);
            // Se imprime la traza del error
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Categoria obtenerPorId(int id) {
        //se crea una variable de tipo categoria
        Categoria categoria = null;
        try {
            // Se conecta a la base de datos empleosdb
            this.conexion.conectar();
            // Se crea una sentencia SQL para seleccionar una categoria por su id
            String sql = "SELECT nombre, descripcion FROM categorias WHERE id = ?";
            // Se crea un objeto PreparedStatement a partir de la conexión y la sentencia SQL
            PreparedStatement ps = conexion.getConnexion().prepareStatement(sql);
            // Se asigna el valor del id al signo de interrogación
            ps.setInt(1, id);
            // Se ejecuta la sentencia SQL y se obtiene un objeto ResultSet con el resultado
            ResultSet rs = ps.executeQuery();
            // Si el ResultSet tiene una fila, se crea una categoria con los valores obtenidos
            if (rs.next()) {
                // Se obtienen los valores de las columnas nombre y descripcion
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                // Se crea una categoria con los valores obtenidos y el id
                categoria = new Categoria(id, nombre, descripcion);
            }
            // Se cierra el ResultSet y el PreparedStatement
            rs.close();
            ps.close();
            // Se desconecta de la base de datos empleosdb
            this.conexion.desconectar();
        } catch (SQLException e) {
            // Se imprime un mensaje indicando que ha ocurrido un error
            System.out.println("Ha ocurrido un error al obtener la categoria con id: " + id);
            // Se imprime la traza del error
            e.printStackTrace();
        }
        // Se devuelve la categoria
        return categoria;
    }

    @Override
    public Categoria actualizar(Categoria categoria) {
        try {
            // Se conecta a la base de datos empleosdb
            this.conexion.conectar();
            // Se crea una sentencia SQL para actualizar una categoria
            String sql = "UPDATE categorias SET nombre = ?, descripcion = ? WHERE id = ?";
            // Se crea un objeto PreparedStatement a partir de la conexión y la sentencia SQL
            PreparedStatement ps = conexion.getConnexion().prepareStatement(sql);
            // Se asignan los valores de los atributos de la categoria a los signos de interrogación
            ps.setString(1, categoria.getNombre());
            ps.setString(2, categoria.getDescripcion());
            ps.setInt(3, categoria.getId());
            // Se ejecuta la sentencia SQL
            ps.executeUpdate();
            // Se imprime un mensaje indicando que se ha actualizado la categoria
            System.out.println("Se ha actualizado la categoria: " + categoria);
            // Se cierra el PreparedStatement
            ps.close();
            // Se desconecta de la base de datos empleosdb
            this.conexion.desconectar();
        } catch (SQLException e) {
            // Se imprime un mensaje indicando que ha ocurrido un error
            System.out.println("Ha ocurrido un error al actualizar la categoria: " + categoria);
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
            // Se crea una sentencia SQL para eliminar una categoria
            String sql = "DELETE FROM categorias WHERE id = ?";
            // Se crea un objeto PreparedStatement a partir de la conexión y la sentencia SQL
            PreparedStatement ps = conexion.getConnexion().prepareStatement(sql);
            // Se asigna el valor del id a el signo de interrogación
            ps.setInt(1, id);
            // Se ejecuta la sentencia SQL
            ps.executeUpdate();
            // Se imprime un mensaje indicando que se ha eliminado la categoria
            System.out.println("Se ha eliminado la categoria con id: " + id);
            // Se cierra el PreparedStatement
            ps.close();
            // Se desconecta de la base de datos empleosdb
            this.conexion.desconectar();
        } catch (SQLException e) {
            // Se imprime un mensaje indicando que ha ocurrido un error
            System.out.println("Ha ocurrido un error al eliminar la categoria con id: " + id);
            // Se imprime la traza del error
            e.printStackTrace();
        }

    }

    @Override
    public List<Categoria> obtenerTodas() {
        // Se crea una lista vacía de categorias
        List<Categoria> categorias = new ArrayList<>();
        try {
            // Se conecta a la base de datos empleosdb
            this.conexion.conectar();
            // Se crea una sentencia SQL para seleccionar todas las categorias
            String sql = "SELECT id, nombre, descripcion FROM categorias";
            // Se crea un objeto Statement a partir de la conexión
            Statement st = this.conexion.getConnexion().createStatement();
            // Se ejecuta la sentencia SQL y se obtiene un objeto ResultSet con los resultados
            ResultSet rs = st.executeQuery(sql);
            // Se recorre el ResultSet y se crea una categoria por cada fila
            while (rs.next()) {
                // Se obtienen los valores de las columnas id, nombre y descripcion
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                // Se crea una categoria con los valores obtenidos
                Categoria categoria = new Categoria(id, nombre, descripcion);
                // Se añade la categoria a la lista de categorias
                categorias.add(categoria);
            }
            // Se cierra el ResultSet y el Statement
            rs.close();
            st.close();
            // Se desconecta de la base de datos empleosdb
            this.conexion.desconectar();
        } catch (SQLException e) {
            // Se imprime un mensaje indicando que ha ocurrido un error
            System.out.println("Ha ocurrido un error al listar las categorias");
            // Se imprime la traza del error
            e.printStackTrace();
        }
        // Se devuelve la lista de categorias
        return categorias;
    }
}
