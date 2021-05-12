package mvvm.board;

import model.Column;
import mvvm.column.ColumnCommand;

public class AddColumnCommand extends ColumnCommand {

    public AddColumnCommand(Column column) {
        super(column);

    }

    @Override
    public void execute() {
        this.getColumn().getBoard().addColumns(getColumn());
    }

    @Override
    public void undo() {
        this.getColumn().getBoard().removeColumns(getColumn());
    }

    @Override
    public boolean canBeUndone() {
        return true;
    }

    @Override
    public String getNameAction() {
        return " ajout d'une colonne";
    }

    @Override
    public String getRedoNameAction() {
        return " ajout d'une colonne";
    }
}
