package model.column;

import model.Card;
import model.Column;
import model.card.CardCommand;

public class MoveCardToLeftCommand extends CardCommand {

    public MoveCardToLeftCommand(Card card) {
        super(card);
    }

    @Override
    public void execute() {
        this.getCard().getColumn().moveCardLeft(getCard());
    }

    @Override
    public void undo() {
        this.getCard().getColumn().moveCardRight(getCard());
    }

    @Override
    public boolean canBeUndone() {
        return true;
    }

    @Override
    public String getNameAction() {
        Column column = getCard().getColumn().getBoard().getColumnByPosition(getCard().getColumn().getPosition()+1);
        return "mouvement de la carte " + getCard().getName() + " de la colonne "+(getCard().getColumn().getPosition()+1) + " vers la colonne " +getCard().getColumn().getPosition()  ;

    }
}
