package mvvm;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Card;
import model.Column;
import mvvm.board.MoveColumnToLeft;
import mvvm.board.MoveColumnToRight;
import mvvm.board.RemoveColumnCommand;
import mvvm.column.AddCardCommand;
import mvvm.column.SetTitleColumnCommand;

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
        //disableButton.set(column.isSelecte());
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


    public void addCard() {
        Card card = new Card((column.lastIdCard()+1),"card"+(column.getCardList().size() + 1),column.getCardList().size() +1,column);

        AddCardCommand addCardCommand = new AddCardCommand(column, card);
        Processor.getInstance().execute(addCardCommand);
        viewModelBoard.refreshUndoRedoProperty();

    }

    public void moveLeft() {


        MoveColumnToLeft moveColumnToLeft = new MoveColumnToLeft(column);
        Processor.getInstance().execute(moveColumnToLeft);
        viewModelBoard.refreshUndoRedoProperty();

    }

    public void moveRight() {


        MoveColumnToRight moveColumnToRight = new MoveColumnToRight(column);
        Processor.getInstance().execute(moveColumnToRight);
        viewModelBoard.refreshUndoRedoProperty();


    }

    public void removeColumn() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression");
        alert.setContentText("Voulez-vous vraiment supprimer cette colonne?");
        Optional<ButtonType> res = alert.showAndWait();
        if (res.isPresent() && res.get() == ButtonType.OK) {
            //column.getBoard().removeColumns(column);
            RemoveColumnCommand removeColumnCommand = new RemoveColumnCommand(column,column.getPosition());
            Processor.getInstance().execute(removeColumnCommand);
        }
        viewModelBoard.refreshUndoRedoProperty();

    }

    public void setTitle(String title) {
        columnName.setValue(title);
        Processor.getInstance().execute(new SetTitleColumnCommand(column, title, column.getName()));
        viewModelBoard.configData();
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


