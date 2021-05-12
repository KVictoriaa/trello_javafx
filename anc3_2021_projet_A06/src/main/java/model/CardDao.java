package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CardDao extends Dao<Card>{
    ColumnDao columnDao = new ColumnDao();
    @Override
    public Card findAll() {
       Card card = new Card();
        try {
            String sql = "SELECT * FROM card ;";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql); // Obtenir le résultat du SELECT
            while (result.next()) { // Tant qu'il y a encore une ligne dans le résultat
                int idCard = result.getInt("idCard"); // Obtenir l'id
                String name = result.getString("nameCard"); // Obtenir le nom
                int position = result.getInt("position");
                //System.out.println("Client(" + id + "," + name + ")");
                int id = result.getInt("idColumn");
               card.setId(idCard);
               //card.setName(name);
               card.setPosition(position);
                //column.getBoard().setId(id);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return card;
    }

    @Override
    public List<Card> findAlls() throws SQLException {
        return null;
    }

    @Override
    public Card find() throws SQLException {
        return null;
    }

    public ObservableList<Card> findAll(int columnId) throws SQLException {
        ObservableList<Card> cards = FXCollections.observableArrayList();
        String sql = "SELECT * FROM card where idColumn = " + columnId +" order by position;";
        Statement statement = connection.createStatement ();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            int id = result.getInt("idCard");
            String name = result.getString("nameCard");
            int position = result.getInt("position");
            int colId = result.getInt("idColumn");
            cards.add(new Card(id, name,  position,columnDao.find(colId)));
        }
        return cards;
    }

    @Override
    public Card find(int id) throws SQLException {
        return null;
    }

    @Override
    public Card create(Card obj) {

        try {

            String sql = "INSERT INTO card(idCard,nameCard,position,idColumn) VALUES(?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, obj.getId());
            preparedStatement.setString(2, obj.getName());
            preparedStatement.setInt(3,obj.getPosition());
            preparedStatement.setInt(4,obj.getColumn().getIdColumn());
            preparedStatement.execute();
            //cards = this.findAll(obj.getId());
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public Card update(Card obj) {
        try {
            String sql = "UPDATE card SET nameCard = ? ,position = ?, idColumn = ? WHERE idCard ="+ obj.getId() + ";";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //preparedStatement.setInt(1, obj.getId());
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setInt(2, obj.getPosition());
            preparedStatement.setInt(3, obj.getColumn().getIdColumn());
            //preparedStatement.setInt(4, obj.getId());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }


    @Override
    public void delete(Card obj) {
        try {
            String sql = "DELETE FROM card WHERE idCard = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, obj.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
