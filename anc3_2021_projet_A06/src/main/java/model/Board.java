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
    public void moveCard(Card card, Direction direction) {

        if (direction == Direction.UP) {
            Collections.reverse(getCards());
            Optional<Card> previousCard = getCards().stream()
                    .filter(c -> c.getPosition().compareTo(card.getPosition()) < 0)
                    .filter(c -> c.getColumn() == card.getColumn())
                    .findFirst();
            if (previousCard.isPresent()) {
                Integer temp = previousCard.get().getPosition();
                previousCard.get().setPosition(card.getPosition());
                card.setPosition(temp);
            }
        } else if (direction == Direction.DOWN) {
            Optional<Card> nextCard = getCards().stream().sorted()
                    .filter(c -> c.getPosition().compareTo(card.getPosition()) > 0)
                    .filter(c -> c.getColumn() == card.getColumn())
                    .findFirst();
            if (nextCard.isPresent()) {
                Integer temp = nextCard.get().getPosition();
                nextCard.get().setPosition(card.getPosition());
                card.setPosition(temp);
            }
        } else if (direction == Direction.LEFT) {
            Optional<Column> previousColumn = getColumns().stream().sorted(Comparator.reverseOrder()).filter(c -> c.getPosition().compareTo(card.getColumn().getPosition()) < 0).findFirst();
            if (previousColumn.isPresent()) {
                Optional<Card> lastCard = previousColumn.get().getCards().stream().max(Comparator.naturalOrder());
                if (lastCard.isPresent()) {
                    Integer lastPosition = lastCard.get().getPosition();
                    card.setPosition(lastPosition + 1);
                }
                card.getColumn().getCards().remove(card);
                previousColumn.get().getCards().add(card);
                card.setColumn(previousColumn.get());
            }
        } else if (direction == Direction.RIGHT) {
            Optional<Column> nextColumn = getColumns().stream().sorted().filter(c -> c.getPosition().compareTo(card.getColumn().getPosition()) > 0).findFirst();
            if (nextColumn.isPresent()) {
                Optional<Card> lastCard = nextColumn.get().getCards().stream().max(Comparator.naturalOrder());
                if (lastCard.isPresent()) {
                    Integer lastPosition = lastCard.get().getPosition();
                    card.setPosition(lastPosition + 1);
                }
                card.getColumn().getCards().remove(card);
                nextColumn.get().getCards().add(card);
                card.setColumn(nextColumn.get());
            }
        }
        Collections.sort(card.getColumn().getCards());
    }
}
