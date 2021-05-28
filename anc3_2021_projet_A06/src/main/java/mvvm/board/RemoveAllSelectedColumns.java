package mvvm.board;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Board;
import model.Column;

public class RemoveAllSelectedColumns extends BoardCommand{
    ObservableList<Column> columns = FXCollections.observableArrayList();

    public RemoveAllSelectedColumns(Board board,ObservableList<Column>columns) {
        super(board);
        this.columns = columns;

    }

    @Override
    public void execute() {
        this.getBoard().removeAllSelectedColumns(columns);

    }

    @Override
    public void undo() {
        this.getBoard().addAllSelectedColumn(columns);

    }

    @Override
    public boolean canBeUndone() {
        return true;
    }

    @Override
    public String getNameAction() {
        return "suppression de colonne";
    }

    @Override
    public String getRedoNameAction() {
        return "suppression de colonne";
    }
}
