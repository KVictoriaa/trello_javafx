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
        setTitle("Trello");

        Column column1 = new Column("column 1", 1);
        Column column2 = new Column("column 2", 2);
        Column column3 = new Column("column 3", 3);

        Card card1 = new Card("card 1", 1, column1);
        Card card2 = new Card("card 2", 2, column2);
        Card card3 = new Card("card 3", 3, column2);
        Card card4 = new Card("card 4", 4, column3);
        Card card5 = new Card("card 5", 5, column3);
        Card card6 = new Card("card 6", 6, column3);

        column1.getCards().addAll(Arrays.asList(card1));
        column2.getCards().addAll(Arrays.asList(card2, card3));
        column3.getCards().addAll(Arrays.asList(card4, card5, card6));

        cards.addAll(Arrays.asList(card1, card2, card3, card4, card5, card6));
        columns.addAll(Arrays.asList(column1, column2, column3));

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
