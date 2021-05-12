package mvvm.column;

import model.Card;
import mvvm.card.CardCommand;

public class RemoveCardCommand extends CardCommand {

    public int lastPosition;
    public RemoveCardCommand(Card card,int lastPosition) {
        super(card);
        this.lastPosition = lastPosition;
    }

    @Override
    public void execute() {
        this.getCard().getColumn().removeCardListDao(getCard());
    }

    @Override
    public void undo() {
        this.getCard().getColumn().addCardListDaoByPosition(getCard(),lastPosition);
    }

    @Override
    public boolean canBeUndone() {
        return true;
    }

    @Override
    public String getNameAction() {
        return "suppression de carte";

    }

    @Override
    public String getRedoNameAction() {
        return "suppression de carte";    }
}
