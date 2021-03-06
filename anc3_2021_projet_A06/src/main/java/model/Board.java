package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.*;

public class Board {
    private String name;
    private int id =0;
    private ObservableList<Column> columns = FXCollections.observableArrayList();
    public ColumnDao columnDao = new ColumnDao();
    private ObservableList<Column> selectedColumns = FXCollections.observableArrayList();

    public Board() {

    }

    public Board(String name) {
        this.name = name;

    }
    public Board(int id,String name) {
        this.id = id;
        this.name = name;
        //initData();
        refreshData();
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
    public ObservableList<Column> getSelectedColumns() {
        return FXCollections.unmodifiableObservableList(selectedColumns);
    }

    public void addColumn(Column column){

        selectedColumns.add(column);

    }
    public void addAllSelectedColumn(ObservableList<Column> selectColumns) {

        columns.addAll( selectColumns);

    }
    public void addColumns(Column column){

        columns.add(column);
        columnDao.create(column);
        Collections.sort(this.columns,new TriColumnParPosition());


    }
    public void addColumnsByPosition(Column column, int pos){
        updatePositionOtherColumn(this,pos);
        columns.add(column);
        columnDao.create(column);
        column.getCardList().forEach(card ->{
            column.cardDao.create(card);
        });
        columnDao.update(column);
        Collections.sort(columns,new TriColumnParPosition());
    }
    public void removeColumn(Column column){
        selectedColumns.remove(column);
    }
    public void removeColumns() {
        selectedColumns = FXCollections.observableArrayList();
    }
    public void removeAllSelectedColumns(ObservableList<Column>selectColumns) {
        columns.removeAll(selectColumns);}
    public void removeColumns(Column column){

        for (int k = column.getPosition(); k < columns.size(); ++k) {
            Column c = getColumnByPosition(k+1);
            c.setPosition(k);
            columnDao.update(c);
        }
        columns.remove(column);
        column.getCardList().forEach(card ->{
            column.cardDao.delete(card);
        });
        columnDao.delete(column);

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
            columnDao.update(column);
            other.setPosition(pos);
            columnDao.update(other);
            Collections.sort(columns, new TriColumnParPosition());
        }
    }
    public void moveColumnRight(Column column){

        int pos = column.getPosition();
        if(pos < getColumns().size()) {
            Column other = getColumnByPosition(pos + 1);
            column.setPosition(pos + 1);
            columnDao.update(column);
            other.setPosition(pos);
            columnDao.update(other);
            Collections.sort(columns, new TriColumnParPosition());
        }
    }

    public int lastIdColumn(){
        return columnDao.findAll().getIdColumn();
    }
    public void updatePositionOtherColumn(Board board, int pos) {
        for (int i = pos; i <= board.getColumns().size(); i++) {
            Column c = board.getColumns().get(i-1);

            c.setPosition(i + 1);
            columnDao.update(c);

        }
    }
    public void refreshData() {
        try {
            for (Column c :columnDao.findAlls()) {
                if (!columns.contains(c)) {
                    columns.add (new Column (c.getIdColumn (), c.getName (),  c.getPosition(),this));
                }
            }
            Collections.sort(this.columns, new TriColumnParPosition());
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }
    }
    public void initData(){
        Column column1 = new Column(1,"column1",1,this);
        Column column2 = new Column(2,"column2",2,this);
        Column column3 = new Column(3,"column3",3,this);

        Card card1 = new Card(1,"card1", 1,column1);
        Card card2 = new Card(2,"card2", 1,column2);
        Card card3 = new Card(3,"card3", 2,column2);
        Card card4 = new Card(4,"card4", 1,column3);
        Card card5 = new Card(5,"card5", 2,column3);
        Card card6 = new Card(6,"card6", 3,column3);

        addColumns(column1);
        addColumns(column2);
        addColumns(column3);

        column1.addCardListDao(card1);
        column2.addCardListDao(card2);
        column2.addCardListDao(card3);
        column3.addCardListDao(card4);
        column3.addCardListDao(card5);
        column3.addCardListDao(card6);
    }
    public boolean isSelecte() {return getColumns().isEmpty();}

}
