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
    public List<Column> getColumns() {
        return this.columns;
    }
    public List<Card> getCards() {
        return cards;
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

    public void moveColumn(Column column, Direction direction) {
        if (direction == Direction.DOWN || direction == Direction.UP)
            throw new UnsupportedOperationException();

        if (direction == Direction.RIGHT) {
            Optional<Column> nextColumn = getColumns().stream().sorted().filter(c -> c.getPosition().compareTo(column.getPosition()) > 0).findFirst();
            if (nextColumn.isPresent()) {
                Integer temp = nextColumn.get().getPosition();
                nextColumn.get().setPosition(column.getPosition());
                column.setPosition(temp);
            }
        } else if (direction == Direction.LEFT) {
            Collections.reverse(getColumns());
            Optional<Column> previousColumn = getColumns().stream().filter(c -> c.getPosition().compareTo(column.getPosition()) < 0).findFirst();
            if (previousColumn.isPresent()) {
                Integer temp = previousColumn.get().getPosition();
                previousColumn.get().setPosition(column.getPosition());
                column.setPosition(temp);
            }
        }

    }
}
