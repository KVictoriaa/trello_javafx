package model;

public class Card {
    private String name;
    private int position;
    private int id = 0;
    Column column;


    public Card(){}

    public Card(String name, int position,Column column) {
        this.name = name;
        this.position = position;
        this.column = column;
    }
    public Card(int id,String name, int position,Column column) {
        this.id = id;
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
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
