package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class BoardDao extends Dao<Board>{
    @Override
    public List<Board> findAll(int id) throws SQLException {
        return null;
    }
    @Override
    public Board find()  {
        Board board = null;
        try {
            String sql = "select * from board";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next ()) {
                int id = result.getInt ("id");
                String name = result.getString ("name");
                board = new Board (id, name);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return board;
    }

    @Override
    public Board findAll() {
        Board board = new Board();
        ObservableList<Column> columns = FXCollections.observableArrayList();
        try {
            String sql = "select * from board ";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) { // Tant qu'il y a encore une ligne dans le résultat
                int idBoard = resultSet.getInt("id"); // Obtenir l'id
                String name = resultSet.getString("name"); // Obtenir le nom
                //System.out.println("Board(" + id + "," + name + ")");
                //columns.addAll(board.columnDao.findColumn());
                board.setId(idBoard);
                board.setName(name);
                //board.setColumns(columns);
                System.out.println();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return board;
    }

    @Override
    public List<Board> findAlls() throws SQLException {
        return null;
    }

    @Override
    public Board find(int id)  {
        Board board = null;
        try {
            String sql = "select * from board";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) { // Tant qu'il y a encore une ligne dans le résultat
                int idBoard = resultSet.getInt("id"); // Obtenir l'id
                String name = resultSet.getString("name"); // Obtenir le nom
                //System.out.println("Board(" + id + "," + name + ")");
                if(idBoard == id){
                    board = new Board();
                    board.setId(idBoard);
                    board.setName(name);
                }

            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return board;
    }

    @Override
    public Board create(Board obj) {

        try {
            String sql = "INSERT INTO board(id,name) VALUES(?,?)";
            System.out.println(sql);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, obj.getName());
            preparedStatement.execute();


        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public Board update(Board obj) {
        try {
            String sql = "UPDATE board SET name = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setInt(2, obj.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public void delete(Board obj) {

    }
}
