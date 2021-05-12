package mvvm.board;

import model.Column;
import mvvm.column.ColumnCommand;

public class RemoveColumnCommand extends ColumnCommand {
    public int lastPosition;
    public RemoveColumnCommand(Column column, int lastPosition) {

        super(column);
        this.lastPosition = lastPosition;
    }

    @Override
    public void execute() {
        this.getColumn().getBoard().removeColumns(getColumn());
    }

    @Override
    public void undo() {
        this.getColumn().getBoard().addColumnsByPosition(getColumn(),lastPosition);
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
