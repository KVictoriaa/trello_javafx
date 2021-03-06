package mvvm.column;

import model.Card;
import mvvm.card.CardCommand;

public class MoveCardToDownCommmand extends CardCommand {
    public MoveCardToDownCommmand(Card card) {
        super(card);
    }

    @Override
    public void execute() {
        this.getCard().getColumn().moveCardDown(getCard());
    }

    @Override
    public void undo() {
        this.getCard().getColumn().moveCardUp(getCard());
    }

    @Override
    public boolean canBeUndone() {
        return true;
    }

    @Override
    public String getNameAction() {
        return "mouvement de la carte " + getCard() + " vers le bas";
    }

    @Override
    public String getRedoNameAction() {
        return "mouvement de la carte  vers le bas";
    }
}
