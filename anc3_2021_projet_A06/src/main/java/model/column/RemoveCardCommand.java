package model.column;

import model.Card;
import model.card.CardCommand;

public class RemoveCardCommand extends CardCommand {
    public RemoveCardCommand(Card card) {
        super(card);
    }

    @Override
    public void execute() {
        this.getCard().getColumn().removeCardList(getCard());
    }

    @Override
    public void undo() {
        this.getCard().getColumn().addCardList(getCard());
    }

    @Override
    public boolean canBeUndone() {
        return true;
    }

    @Override
    public String getNameAction() {
        return "suppression de carte";

    }
}
