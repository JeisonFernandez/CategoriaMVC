package org.example.modelo.db;

import java.sql.Connection;

public interface IConexionDb {
    // El método conectar establece la conexión con la base de datos empleosdb
    public void conectar();
    // El método desconectar cierra la conexión con la base de datos empleosdb
    public void desconectar();
    public Connection getConnexion();
}
