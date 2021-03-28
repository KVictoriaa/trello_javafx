package mvvm;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import model.Board;
import model.Column;

public class ViewModelBoard {
    private Board board;

    private final SimpleListProperty<Column> columns = new SimpleListProperty<>();
    private SimpleBooleanProperty redoDisable = new SimpleBooleanProperty();
    private SimpleBooleanProperty undoDisable = new SimpleBooleanProperty();
    private SimpleStringProperty nameAction = new SimpleStringProperty(" ");
    private SimpleStringProperty nameActionRedo = new SimpleStringProperty(" ");
    private SimpleStringProperty boardName = new SimpleStringProperty();


    public ViewModelBoard(Board board) {
        this.board = board;
        configData();
    }

    public void configData(){
        columns.setValue(board.getColumns());
        boardName.setValue(board.getName());


    }
    public Board getBoard() {
        return board;
    }
    public void addColumn(){
        board.addColumns(new Column("column"+(board.getColumns().size() + 1),board.getColumns().size() +1,board));
    }
    public void setTitle(String title){
        board.setName(title);
    }

    public SimpleListProperty<Column> columnsProperty() {
        return columns;
    }
    public SimpleBooleanProperty undoDisableProperty() {
        return undoDisable;
    }
    public SimpleBooleanProperty redoDisableProperty() {
        return redoDisable;
    }
    public SimpleStringProperty nameActionProperty() {
        return nameAction;
    }
    public SimpleStringProperty nameActionRedoProperty() {
        return nameActionRedo;
    }
    public SimpleStringProperty boardNameProperty() {
        return boardName;
    }


}
