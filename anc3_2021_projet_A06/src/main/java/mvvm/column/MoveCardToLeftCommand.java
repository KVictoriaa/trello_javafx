package mvvm.column;

import model.Card;
import model.Column;
import mvvm.card.CardCommand;

public class MoveCardToLeftCommand extends CardCommand {
    public int lastPosition;
    public MoveCardToLeftCommand(Card card, int lastPosition) {

        super(card);
        this.lastPosition = lastPosition;

    }

    @Override
    public void execute() {
        this.getCard().getColumn().moveCardLeft(getCard());
    }

    @Override
    public void undo() {
        this.getCard().getColumn().moveCardRightPosotion(getCard(), lastPosition);
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

    @Override
    public String getRedoNameAction() {
        return "mouvement de la carte " + getCard().getName() + " de la colonne "+(getCard().getColumn().getPosition()-1 ) + " vers la colonne " + getCard().getColumn().getPosition()  ;
    }
}
