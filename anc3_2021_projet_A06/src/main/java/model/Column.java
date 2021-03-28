package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Column {
    Board board;
    private String name;
    private int position;
    private ObservableList<Card> cardList = FXCollections.observableArrayList();


    public Column(String name, int position, Board board) {
        this.name = name;
        this.position = position;
        this.board = board;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public ObservableList<Card> getCardList() {
        cardList.sort(new TriCardParPosition());
        return cardList;
    }

    //    public void setCardList(ObservableList<Card> cardList) {
//        this.cardList = cardList;
//    }
    public void addCardList(Card card) {
        getCardList().add(card);
    }

    public void removeCardList(Card card) {

        for (int k = card.getPosition(); k < cardList.size(); ++k) {
            getCardByPosition(k + 1).setPosition(k);
        }
        getCardList().remove(card);

    }

    //    public Card getCard(int index){
//        return this.cardList.get(index);
//    }
    public Card getCardByPosition(int position) {
        int i = 0;
        while (i < cardList.size() && cardList.get(i).getPosition() != position) {
            ++i;
        }
        return cardList.get(i);
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    @Override
    public String toString() {
        return "" + position;
    }

    public void moveCardUp(Card card) {

        int pos = card.getPosition();
        if (pos > 1) {
            Card other = getCardByPosition(pos - 1);
            other.setPosition(pos);
            card.setPosition(pos - 1);
            cardList.sort(new TriCardParPosition());
        }
    }

    public void moveCardDown(Card card) {
        int pos = card.getPosition();
        if (pos < getCardList().size()) {
            Card other = getCardByPosition(pos + 1);
            card.setPosition(pos + 1);
            other.setPosition(pos);
            cardList.sort(new TriCardParPosition());
        }
    }

    public void moveCardLeft(Card card) {
        if (getPosition() > 1) {
            Column other = board.getColumnByPosition(getPosition() - 1);
            removeCardList(card);
            card.setPosition(other.getCardList().size() + 1);
            card.setColumn(other);
            other.addCardList(card);
        }
    }

    public void moveCardRight(Card card) {
        if (getPosition() < getBoard().getColumns().size()) {
            Column other = board.getColumnByPosition(getPosition() + 1);

            removeCardList(card);
            card.setPosition(other.getCardList().size() + 1);
            card.setColumn(other);
            other.addCardList(card);
        }
    }

    public boolean isFirstPosition() {
        return getPosition() == 1;
    }

    public boolean isLastPosition() {
        return board.getLastPosition() == getPosition();
    }

    public int getLastPosition() {
        return cardList.size();
    }

}
