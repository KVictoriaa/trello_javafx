package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Collections;


public class Column {
    Board board;
    private String name;
    private int position;
    private int idColumn = 0;
    CardDao cardDao = new CardDao();
    private ObservableList<Card> cardList = FXCollections.observableArrayList();

    public Column () {

    }

    public Column(String name, int position, Board board) {
        this.name = name;
        this.position = position;
        this.board = board;
    }

    public Column(int idColumn,String name, int position,Board board) {
        this.idColumn = idColumn;
        this.name = name;
        this.position = position;
        this.board = board;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
        board.columnDao.update(this);

    }


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public ObservableList<Card> getCardList() {
        Collections.sort(cardList, new TriCardParPosition());
        return FXCollections.unmodifiableObservableList(cardList);
    }

    //    public void setCardList(ObservableList<Card> cardList) {
//        this.cardList = cardList;
//    }
    public void addCardList(Card card) {
        cardList.add(card);
    }
    public void addCardListDao(Card card){
        cardList.add(card);
        cardDao.create(card);
    }
    public void addCardListDaoByPosition(Card card, int pos){
        updatePositionOtherCards(this,pos);
        cardList.add(card);
        cardDao.create(card);
        Collections.sort(cardList, new TriCardParPosition());
    }
    public void removeCardList(Card card) {

        for (int k = card.getPosition() ; k < cardList.size(); ++k) {
            Card c =getCardByPosition(k+1);
            c.setPosition(k);
            cardDao.update(c);
        }
        cardList.remove(card);

    }
    public void removeCardListDao(Card card){

        for (int k = card.getPosition() ; k < cardList.size(); ++k) {
            Card c =getCardByPosition(k+1);
            c.setPosition(k);
            cardDao.update(c);
        }
        cardList.remove(card);
        cardDao.delete(card);

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
    public int getIdColumn() {
        return idColumn;
    }

    public void setIdColumn(int idColumn) {
        this.idColumn = idColumn;
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
            cardDao.update(other);
            card.setPosition(pos - 1);
            cardDao.update(card);
            Collections.sort(cardList, new TriCardParPosition());
        }
    }

    public void moveCardDown(Card card) {
        int pos = card.getPosition();
        if (pos < getCardList().size()) {
            Card other = getCardByPosition(pos + 1);
            card.setPosition(pos + 1);
            cardDao.update(card);
            other.setPosition(pos);
            cardDao.update(other);
            Collections.sort(cardList, new TriCardParPosition());
        }
    }

    public void moveCardLeft(Card card) {
        if (getPosition() > 1) {
            Column other = board.getColumnByPosition(getPosition() - 1);
            removeCardList(card);
            card.setPosition(other.getCardList().size() + 1);
            card.setColumn(other);
            other.addCardList(card);
            cardDao.update(card);
            Collections.sort(cardList, new TriCardParPosition());
        }
    }

    public void moveCardRight(Card card) {
        if (getPosition() < getBoard().getColumns().size()) {
            Column other = board.getColumnByPosition(getPosition() + 1);
            removeCardList(card);
            card.setPosition(other.getCardList().size() + 1);
            card.setColumn(other);
            other.addCardList(card);
            cardDao.update(card);
            Collections.sort(cardList, new TriCardParPosition());
        }
    }
    public void moveCardRightPosotion(Card card, int pos){
        if(getPosition() < getBoard().getColumns().size()) {
            Column other = board.getColumnByPosition(getPosition() + 1);
            removeCardList(card);
            updatePositionOtherCards(other, pos);
            card.setPosition(pos);
            card.setColumn(other);
            other.addCardList(card);
            cardDao.update(card);
            Collections.sort(cardList, new TriCardParPosition());

        }
    }

    public void moveCardLeftPosotion(Card card, int pos){
        if(getPosition() > 1) {
            Column other = board.getColumnByPosition(getPosition() -1);
            removeCardList(card);
            updatePositionOtherCards(other, pos);
            card.setPosition(pos);
            card.setColumn(other);
            other.addCardList(card);
            cardDao.update(card);
            Collections.sort(cardList, new TriCardParPosition());

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
    public int lastIdCard(){

        return cardDao.findAll().getId();

    }
    public void updatePositionOtherCards(Column column, int pos) {
        for (int i = pos; i <= column.getCardList().size(); i++) {
            Card c = column.getCardList().get(i-1);
            c.setPosition(i + 1);
            cardDao.update(c);
        }
    }

}
