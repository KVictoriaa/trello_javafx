package mvvm.board;

import model.Column;
import mvvm.column.ColumnCommand;

public class RemoveColumnCommand extends ColumnCommand {
    public RemoveColumnCommand(Column column) {
        super(column);
    }

    @Override
    public void execute() {
        this.getColumn().getBoard().removeColumns(getColumn());
    }

    @Override
    public void undo() {
        this.getColumn().getBoard().addColumns(getColumn());
    }

    @Override
    public boolean canBeUndone() {
        return true;
    }

    @Override
    public String getNameAction() {
        return "suppression de colonne";
    }
}
