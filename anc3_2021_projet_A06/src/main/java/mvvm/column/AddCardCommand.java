package mvvm.column;

import model.Card;
import model.Column;

public class AddCardCommand extends ColumnCommand {
    private final Card card;
    public AddCardCommand(Column column, Card card) {
        super(column);
        this.card = card;
    }

    @Override
    public void execute() {
        this.getColumn().addCardList(card);
    }

    @Override
    public void undo() {
        this.getColumn().removeCardList(card);
    }

    @Override
    public boolean canBeUndone() {
        return true;
    }
    public String getNameAction(){
        return "ajout d'une carte";
    }
}
