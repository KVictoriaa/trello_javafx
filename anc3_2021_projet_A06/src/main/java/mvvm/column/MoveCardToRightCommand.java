package mvvm.column;

import model.Card;
import mvvm.card.CardCommand;

public class MoveCardToRightCommand extends CardCommand {

    public int lastPosition;
    public MoveCardToRightCommand(Card card, int lastPosition) {
        super(card);
        this.lastPosition = lastPosition;

    }

    @Override
    public void execute() {
        this.getCard().getColumn().moveCardRight(getCard());
    }

    @Override
    public void undo() {
        this.getCard().getColumn().moveCardLeftPosotion(getCard(), lastPosition);
    }

    @Override
    public boolean canBeUndone() {
        return true;
    }

    @Override
    public String getNameAction() {

        return "mouvement de la carte " + getCard().getName() + " de la colonne "+(getCard().getColumn().getPosition()-1)+ " vers la colonne " +(getCard().getColumn().getPosition() );
    }

    @Override
    public String getRedoNameAction() {
        return "mouvement de la carte " + getCard().getName() + " de la colonne "+(getCard().getColumn().getPosition())+ " vers la colonne " +(getCard().getColumn().getPosition() +1 );
    }

}
