package mvvm;

import javafx.beans.property.SimpleListProperty;
import model.Board;
import model.Column;

public class ViewModelBoard {
    private Board board;

    private final SimpleListProperty<Column> columns = new SimpleListProperty<>();

    public ViewModelBoard(Board board) {
        this.board = board;
        configData();
    }

    public void configData(){
        columns.setValue(board.getColumns());


    }
    public Board getBoard() {
        return board;
    }
    public SimpleListProperty<Column> columnsProperty() {
        return columns;
    }

}
