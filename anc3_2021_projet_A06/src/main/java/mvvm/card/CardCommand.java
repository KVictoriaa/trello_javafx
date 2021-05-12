package mvvm.card;

import model.Card;
import model.Command;

public abstract class CardCommand implements Command {
    private final Card card;

    public CardCommand(Card card) {
        this.card = card;
    }
    public Card getCard() {
        return card;
    }

}
