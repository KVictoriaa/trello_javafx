package mvvm;

import model.Card;


public class ViewModelCard {

    private final Card card;
    private ViewModelBoard viewModelBoard;


    public ViewModelCard(Card card, ViewModelBoard viewModelBoard) {
        this.card = card;
        this.viewModelBoard = viewModelBoard;

    }

    public Card getCard() {
        return card;
    }


}
