package mvvm;

import javafx.beans.property.SimpleListProperty;

import model.Card;
import model.Column;


public class ViewModelColumn {
    private final Column column;
    private final SimpleListProperty<Card> cards = new SimpleListProperty<>();
    private ViewModelBoard viewModelBoard;

    public ViewModelColumn(Column column, ViewModelBoard viewModelBoard) {
        this.viewModelBoard = viewModelBoard;
        this.column = column;
        configData();
    }

    public Column getColumn() {
        return column;
    }

    public void configData() {

        cards.setValue(column.getCardList());

    }

    public SimpleListProperty<Card> cardsProperty() {
        return cards;
    }


}


