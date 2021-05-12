package model.column;

import model.Card;
import model.card.CardCommand;

public class MoveCardToRightCommand extends CardCommand {

    public MoveCardToRightCommand(Card card) {
        super(card);

    }

    @Override
    public void execute() {
        this.getCard().getColumn().moveCardRight(getCard());
    }

    @Override
    public void undo() {
        this.getCard().getColumn().moveCardLeft(getCard());
    }

    @Override
    public boolean canBeUndone() {
        return true;
    }

    @Override
    public String getNameAction() {

        return "mouvement de la carte " + getCard().getName() + " de la colonne "+(getCard().getColumn().getPosition()-1)+ " vers la colonne " +(getCard().getColumn().getPosition() );
    }

}
