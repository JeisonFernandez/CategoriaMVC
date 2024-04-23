package org.example.modelo.db;

import java.sql.*;

public class ConexionDb implements IConexionDb {
    // Los atributos url, user y password almacenan los datos de la conexión
    private String url = "jdbc:mysql://localhost:3306/empleosdb";
    private String user = "root";
    private String password = "";
    // El atributo con es un objeto de la clase Connection que representa la conexión
    private Connection con;
    // El atributo instance es la única instancia de la clase Conexiondb
    private static ConexionDb instance;

    // El constructor de la clase Conexiondb es privado para evitar que otras clases puedan crear objetos de la clase Conexiondb
    private  ConexionDb() {
        // Se inicializa el atributo con como null
        this.con = null;
    }

    // El método getInstance devuelve la instancia de la clase Conexiondb, y la crea si no existe
    public static ConexionDb getInstance() {
        // Se usa un bloque sincronizado para garantizar la seguridad en entornos multihilo
        synchronized (ConexionDb.class) {
            // Si la instancia es null, se crea una nueva instancia
            if (instance == null) {
                instance = new ConexionDb();
            }
        }
        // Se devuelve la instancia
        return instance;
    }

    // El método conectar implementa la lógica para establecer la conexión con la base de datos
    @Override
    public void conectar() {
        try {
            // Se carga el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Se crea la conexión con la base de datos empleosdb usando los atributos url, user y password
            this.con = DriverManager.getConnection(url, user, password);
            // Se imprime un mensaje indicando que se ha conectado
            System.out.println("Se ha conectado a la base de datos empleosdb");
        } catch (ClassNotFoundException | SQLException e) {
            // Se imprime un mensaje indicando que ha ocurrido un error
            System.out.println("Ha ocurrido un error al conectar a la base de datos empleosdb");
            // Se imprime la traza del error
            e.printStackTrace();
        }
    }

    // El método desconectar implementa la lógica para cerrar la conexión con la base de datos
    @Override
    public void desconectar() {
        try {
            // Se cierra la conexión con la base de datos empleosdb
            this.con.close();
            // Se imprime un mensaje indicando que se ha desconectado
            System.out.println("Se ha desconectado de la base de datos empleosdb");
        } catch (SQLException e) {
            // Se imprime un mensaje indicando que ha ocurrido un error
            System.out.println("Ha ocurrido un error al desconectar de la base de datos empleosdb");
            // Se imprime la traza del error
            e.printStackTrace();
        }
    }
    @Override
    // El método getCon devuelve el atributo con, que representa la conexión con la base de datos
    public Connection getConnexion() {
        return this.con;
    }
}