package mvvm;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Board;
import model.Card;
import model.Column;

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



    }
