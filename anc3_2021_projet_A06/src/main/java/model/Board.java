package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

public class Board {
    private String name;
    private int id =0;
    private ObservableList<Column> columns = FXCollections.observableArrayList();
    public Board() {

    }

    public Board(String name) {
        this.name = name;
        initData();
    }
    public Board(int id,String name) {
        this.id = id;
        this.name = name;
        initData();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ObservableList<Column> getColumns() {
        Collections.sort(columns, new TriColumnParPosition());
        return FXCollections.unmodifiableObservableList(columns);
    }
    public void addColumns(Column column){
        columns.add(column);
    }
    public void removeColumns(Column column){

        for (int k = column.getPosition(); k < columns.size(); ++k) {
            getColumnByPosition(k+1).setPosition(k);
        }
        columns.remove(column);

    }
    public Column getColumn(int index){
        return this.columns.get(index);
    }
    public Column getColumnByPosition(int position){
        int i = 0;
        while (i < columns.size() && columns.get(i).getPosition() != position){
            ++i;
        }
        return columns.get(i);
    }
    public int getLastPosition() {
        return columns.size();
    }
    public void moveColumnLeft(Column column){

        int pos = column.getPosition();
        if(pos > 1) {
            Column other = getColumnByPosition(pos - 1);
            column.setPosition(pos - 1);
            other.setPosition(pos);
            Collections.sort(columns, new TriColumnParPosition());
        }
    }
    public void moveColumnRight(Column column){

        int pos = column.getPosition();
        if(pos < getColumns().size()) {
            Column other = getColumnByPosition(pos + 1);
            column.setPosition(pos + 1);
            other.setPosition(pos);
            Collections.sort(columns, new TriColumnParPosition());
        }
    }
    public void initData(){
        Column column1 = new Column("column1",1,this);
        Column column2 = new Column("column2",2,this);
        Column column3 = new Column("column3",3,this);

        Card card1 = new Card("card1", 1,column1);
        Card card2 = new Card("card2", 1,column2);
        Card card3 = new Card("card3", 2,column2);
        Card card4 = new Card("card4", 1,column3);
        Card card5 = new Card("card5", 2,column3);
        Card card6 = new Card("card6", 3,column3);

        addColumns(column1);
        addColumns(column2);
        addColumns(column3);

        column1.addCardList(card1);
        column2.addCardList(card2);
        column2.addCardList(card3);
        column3.addCardList(card4);
        column3.addCardList(card5);
        column3.addCardList(card6);


    }


}
