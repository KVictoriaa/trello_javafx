package mvvm;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Board;
import model.Card;
import model.Column;
import model.Direction;

import java.util.Collections;

public class ViewModel {

    private final Board board;

        private ObservableList<Column> columns;
        private ObservableList<Card> cards ;

        public ViewModel(Board board) {
            this.board = board;
            configData();
        }

        public ObservableList<Column> getColumns() {
            return columns;
        }
        public ObservableList<Card> getCards() {
            return cards;
        }

        private void configData() {
            columns =FXCollections.observableList(board.getColumns());
            cards   = FXCollections.observableList(board.getCards());
        }

    public void moveColumn(Column column, Direction direction){
        board.moveColumn(column,direction);
        Collections.sort(getColumns());
    }
    public void moveCard(Card card,Direction direction){
        board.moveCard(card,direction);
    }

    public void addColumn(Column column) {
        columns.add(column);
    }

    public void remove(Column column) {
        getColumns().remove(column);
    }

}
