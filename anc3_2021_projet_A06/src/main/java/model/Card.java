package model;

public class Card {
    private String name;
    private int position;
    Column column;

    public Card(String name, int position,Column column) {
        this.name = name;
        this.position = position;
        this.column = column;
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
    public Column getColumn() {
        return column;
    }

    public void setColumn(Column column) {
        this.column = column;
    }

    public boolean isFirtPosition(){
        return getPosition() == 1;
    }
    public boolean isLastPostion(){
        return column.getLastPosition() == getPosition();
    }


    @Override
    public String toString() {
        return "" + position;
    }
}
