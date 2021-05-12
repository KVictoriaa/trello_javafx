package mvvm.column;

import model.Column;
import mvvm.Command;

public abstract class ColumnCommand implements Command {

    private final Column column;

    public ColumnCommand(Column column) {
        this.column = column;
    }
    public Column getColumn() {
        return column;
    }
}
