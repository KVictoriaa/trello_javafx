package model;

import java.util.*;

public class Board {

    private String title;

    private final List<Column> columns = new ArrayList<>();
    private final List <Card> cards = new ArrayList<>();

    public Board() {
        initData();
    }

    private void initData() {
        setTitle("Project Trello");

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addColumn(Column column){
        columns.add(column);
    }


    public String toString() {
        return title;
    }
}
