package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ColumnDao extends Dao<Column>{
    BoardDao boardDao = new BoardDao();

    @Override
    public List<Column> findAll(int id) throws SQLException {
        return null;
    }

    @Override
    public Column findAll() {
        Column column = new Column();
        try {
            String sql = "SELECT * FROM column ;";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql); // Obtenir le résultat du SELECT
            while (result.next()) { // Tant qu'il y a encore une ligne dans le résultat
                int idColumn = result.getInt("idColumn"); // Obtenir l'id
                String name = result.getString("nameColumn"); // Obtenir le nom
                int position = result.getInt("position");
                //System.out.println("Client(" + id + "," + name + ")");
                int id = result.getInt("id");
                column.setIdColumn(idColumn);
                //column.setName(name);
                column.setPosition(position);
                //column.getBoard().setId(id);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return column;
    }
    public ObservableList<Column> findAlls() throws SQLException {
        ObservableList<Column> columns =  FXCollections.observableArrayList();
        String sql = "SELECT * FROM column" ;
        Statement statement = connection.createStatement ();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            int id = result.getInt("idColumn");
            String name = result.getString("nameColumn");
            int position = result.getInt("position");
            int boardId = result.getInt("id");
            columns.add (new Column (id, name,  position,boardDao.find(boardId)));
        }
        return columns;
    }

    @Override
    public Column find() throws SQLException {
        return null;
    }

    @Override
    public Column find(int id)  {

        Column column = new Column();
        try {
            String sql = "SELECT * FROM column , board where column.id = " + id;
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql); // Obtenir le résultat du SELECT
            while (result.next()) { // Tant qu'il y a encore une ligne dans le résultat
                int idColumn = result.getInt("idColumn"); // Obtenir l'id
                String name = result.getString("nameColumn"); // Obtenir le nom
                int position = result.getInt("position");
                column.setIdColumn(idColumn);
                column.setPosition(position);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return column;
    }

    @Override
    public Column create(Column obj) {
        try {
            String sql = "INSERT INTO column(idColumn,nameColumn,position,id) VALUES(?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, obj.getIdColumn());
            preparedStatement.setString(2, obj.getName());
            preparedStatement.setInt(3,obj.getPosition());
            preparedStatement.setInt(4,obj.getBoard().getId());
            preparedStatement.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public Column update(Column obj) {
        try {
            String sql = "UPDATE column SET nameColumn  = ?,position = ?, id = ?  WHERE idColumn = "+ obj.getIdColumn() + ";";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setInt(2, obj.getPosition());
            preparedStatement.setInt(3, obj.getBoard().getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public void delete(Column obj) {
        try {
            String sql = "DELETE FROM column WHERE idColumn = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, obj.getIdColumn());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
