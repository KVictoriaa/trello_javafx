package model;


public class Card extends MovableItems {

    private Column column;

    public Card(String title, Integer position, Column column) {
        super(title, position);
        setColumn(column);
    }

    public Column getColumn() {
        return column;
    }

    public void setColumn(Column column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return getTitle();

    }


}