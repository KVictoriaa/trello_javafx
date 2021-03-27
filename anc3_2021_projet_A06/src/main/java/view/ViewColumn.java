package view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Card;
import model.Column;
import mvvm.ViewModelBoard;
import mvvm.ViewModelColumn;

import java.util.ArrayList;
import java.util.List;

public class ViewColumn extends VBox {

    private ViewModelColumn viewModelColumn;
    private ListView<Card> cardListView = new ListView<>();
    private List<ViewCard> cards = new ArrayList<>();
    private Button left = new Button("⬅");
    private Button right = new Button("➡");
    private HBox up = new HBox();
    private HBox down = new HBox();
    private ViewModelBoard viewModelBoard;


    public ViewColumn(Column column, ViewModelBoard viewModelBoard) {
        this.viewModelBoard = viewModelBoard;
        viewModelColumn = new ViewModelColumn(column, viewModelBoard);
        configColumn();
        configData();
        customize();

    }

    private void configColumn() {

        down.getChildren().addAll(cardListView);
        this.getChildren().addAll(up, down);
        up.setPrefWidth(250);
        this.setPrefWidth(250);
        up.setAlignment(Pos.CENTER);
    }

    private void configData() {
        cardListView.itemsProperty().bind(viewModelColumn.cardsProperty());

    }

    private void customize() {
        cardListView.setCellFactory(view -> new ListCell<>() {
            @Override
            protected void updateItem(Card card, boolean item) {
                super.updateItem(card, item);
                ViewCard viewCard = null;
                if (card != null) {
                    viewCard = new ViewCard(card, viewModelBoard);
                }
                setGraphic(viewCard);
            }
        });
    }

}
