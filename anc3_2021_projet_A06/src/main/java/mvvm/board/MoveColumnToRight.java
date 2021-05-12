package mvvm.board;

import model.Column;
import mvvm.column.ColumnCommand;

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
        return "mouvement de colonne vers la droite";

    }

    @Override
    public String getRedoNameAction() {
        return "mouvement de colonne vers la droite";
    }
}
