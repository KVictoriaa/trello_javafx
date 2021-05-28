package model;

import java.io.File;
import java.sql.*;

public class Trello {
    public static String url="jdbc:sqlite:trelloTest.db";
    public static String trelloTest = "trelloTest.db";

    private static void configDB(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql;

        // Activation of checks FK
        sql = "PRAGMA foreign_keys = ON;";
        stmt.execute(sql);
    }

    private static void createTables(Connection conn) throws SQLException {

        String sql;
        Statement stmt = conn.createStatement();

        // SQL statement for client table
        sql = "CREATE TABLE IF NOT EXISTS board ("
                + "	id integer PRIMARY KEY,"
                + "	name text NOT NULL);";
        stmt.execute(sql);

        sql = "CREATE TABLE IF NOT EXISTS column ("
                + "	idColumn integer PRIMARY KEY,"
                + "	nameColumn text NOT NULL,"
                + "	position integer NOT NULL,"
                + "	id integer NOT NULL,"
                + "	CONSTRAINT fk_board FOREIGN KEY (id)"
                + " REFERENCES board(id));";
        stmt.execute(sql);
        //System.out.println(sql);
        sql = "CREATE TABLE IF NOT EXISTS card ("
                + "	idCard integer PRIMARY KEY,"
                + "	nameCard text NOT NULL,"
                + "	position integer NOT NULL,"
                + "	idColumn integer NOT NULL,"
                + "	CONSTRAINT fk_column FOREIGN KEY (idColumn)"
                + " REFERENCES column(idColumn));";
        stmt.execute(sql);


    }
    private static void clearDB(Connection conn) throws SQLException {

        Statement statement = conn.createStatement();
        String sql;

        sql = "DELETE FROM board;";
        statement.execute(sql);
        sql = "DELETE FROM column;";
        statement.execute(sql);
        sql = "DELETE FROM card;";
        statement.execute(sql);
    }
    private static void seedboard(Connection conn) throws SQLException {
        clearDB(conn);
        String sql = "INSERT INTO board(id, name) VALUES(?,?);";
        PreparedStatement preparedStatement = conn.prepareStatement (sql);
        preparedStatement.setInt (1, 1);
        Board board = new Board();
        preparedStatement.setString (2, "Tableau");
        preparedStatement.execute ();
    }


    public static void test() {
        try {
            File monFichier = new File(trelloTest);
            if(monFichier.exists())
            {
                  Connection connection = DriverManager.getConnection(url);
                  configDB(connection);

              }
           else {
               Connection connection = DriverManager.getConnection(url);
               configDB(connection);
               createTables(connection);
               seedboard(connection);

           }
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
        }

    }

}
