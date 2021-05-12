package mvvm.column;

import model.Card;
import mvvm.card.CardCommand;

public class MoveCardToUpCommand extends CardCommand {
    public MoveCardToUpCommand(Card card) {
        super(card);
    }

    @Override
    public void execute() {
        this.getCard().getColumn().moveCardUp(getCard());
    }

    @Override
    public void undo() {
        this.getCard().getColumn().moveCardDown(getCard());
    }

    @Override
    public boolean canBeUndone() {
        return true;
    }

    @Override
    public String getNameAction() {
        return "mouvement de carte vers le haut";

    }

    @Override
    public String getRedoNameAction() {
        return "mouvement de carte  vers le haut";
    }
}
