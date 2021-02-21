package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Column extends MovableItems{
    private final ObservableList<Card> cards = FXCollections.observableArrayList();

    public Column(String title, Integer position) {
        super(title, position);

    }

    public ObservableList<Card> getCards() {
        return cards;
    }

    @Override
    public String toString() {
        return getTitle();
    }
}
