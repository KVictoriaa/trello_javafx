package mvvm.column;

import model.Column;

public class SetTitleColumnCommand extends ColumnCommand {
    private String name;
    private String content;

    public SetTitleColumnCommand(Column column, String name, String content) {
        super(column);
        this.name = name;
        this.content = content;

    }

    @Override
    public void execute() {
        this.getColumn().setName(name);
    }

    @Override
    public void undo() {
        this.getColumn().setName(content);
    }

    @Override
    public boolean canBeUndone() {
        return true;
    }

    @Override
    public String getNameAction() {
        return "la modification du titre";
    }
}
