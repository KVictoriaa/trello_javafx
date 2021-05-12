package mvvm.board;

import model.Column;
import model.column.ColumnCommand;

public class MoveColumnToLeft extends ColumnCommand {
    public MoveColumnToLeft(Column column) {
        super(column);
    }

    @Override
    public void execute() {
        this.getColumn().getBoard().moveColumnLeft(getColumn());
    }

    @Override
    public void undo() {
        this.getColumn().getBoard().moveColumnRight(getColumn());
    }

    @Override
    public boolean canBeUndone() {
        return true;
    }

    @Override
    public String getNameAction() {
        return " mouvement de la colonne vers la gauche";
    }
}
