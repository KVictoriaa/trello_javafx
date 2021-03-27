package mvvm;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Card;
import model.Column;

import java.util.Optional;


public class ViewModelColumn {
    private final Column column;
    private final SimpleListProperty<Card> cards = new SimpleListProperty<>();
    private final SimpleBooleanProperty disableColumnLeft = new SimpleBooleanProperty();
    private final SimpleBooleanProperty disableColumnRight = new SimpleBooleanProperty();
    private ViewModelBoard viewModelBoard;
    private SimpleStringProperty columnName = new SimpleStringProperty();

    public ViewModelColumn(Column column, ViewModelBoard viewModelBoard) {
        this.viewModelBoard = viewModelBoard;
        this.column = column;
        disableColumnLeft.set(column.isFirstPosition());
        disableColumnRight.set(column.isLastPosition());
        configData();
    }

    public Column getColumn() {
        return column;
    }

    public void configData() {

        cards.setValue(column.getCardList());
        columnName.setValue(column.getName());
    }

    public SimpleListProperty<Card> cardsProperty() {
        return cards;
    }

    public void moveLeft() {
        column.getBoard().moveColumnLeft(column);
    }

    public void moveRight() {
        column.getBoard().moveColumnRight(column);
    }

    public void addCard() {
        Card card = new Card("card" + (column.getCardList().size() + 1), column.getCardList().size() + 1, column);
        //column.addCardList(card);
    }
    public void removeColumn() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression");
        alert.setContentText("Voulez-vous vraiment supprimer cette colonne?");
        Optional<ButtonType> res = alert.showAndWait();
        if (res.isPresent() && res.get() == ButtonType.OK) {
            //column.getBoard().removeColumns(column);
        }
    }
    public SimpleBooleanProperty disableColumnLeftProperty() {
        return disableColumnLeft;
    }
    public SimpleBooleanProperty disableColumnRightProperty() {
        return disableColumnRight;
    }
    public SimpleStringProperty columnNameProperty() {
        return columnName;
    }


}


