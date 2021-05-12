package mvvm.board;

import model.Column;
import model.column.ColumnCommand;

public class MoveColumnToRight extends ColumnCommand {
    public MoveColumnToRight(Column column) {
        super(column);
    }

    @Override
    public void execute() {
        this.getColumn().getBoard().moveColumnRight(getColumn());
    }

    @Override
    public void undo() {
        this.getColumn().getBoard().moveColumnLeft(getColumn());
    }

    @Override
    public boolean canBeUndone() {
        return true;
    }

    @Override
    public String getNameAction() {
        return "annule mouvement de colonne vers la droite";

    }
}
