package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class Dao <T> {
    public static String url="jdbc:sqlite:trelloTest.db";
    public Connection connection;

    public Dao() {
        try {
             connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public abstract List<T> findAll(int id) throws SQLException;

    public abstract T findAll();
    public abstract List<T> findAlls() throws SQLException;
    public abstract T find() throws SQLException;
    public abstract T find(int id) throws SQLException;
    public abstract T create(T obj) throws SQLException;
    public abstract T update(T obj);
    public abstract void delete(T obj);
}
