package view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Card;
import model.Column;
import mvvm.ViewModelBoard;
import mvvm.ViewModelColumn;

import java.util.ArrayList;
import java.util.List;

public class ViewColumn extends VBox {
    private EditableLabel editableLabel;
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
        editableLabel = new EditableLabel(viewModelColumn.columnNameProperty(), null, viewModelColumn, null);
        configColumn();
        configData();
        customize();
        configAction();

    }

    private void configColumn() {
        up.getChildren().addAll(left, editableLabel, right);
        down.getChildren().addAll(cardListView);
        this.getChildren().addAll(up, down);
        up.setPrefWidth(250);
        this.setPrefWidth(250);
        up.setAlignment(Pos.CENTER);
    }

    private void configData() {
        cardListView.itemsProperty().bind(viewModelColumn.cardsProperty());
        left.disableProperty().bind(viewModelColumn.disableColumnLeftProperty());
        right.disableProperty().bind(viewModelColumn.disableColumnRightProperty());

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
    public void configAction(){

        left.setOnMouseClicked(e -> {
            viewModelColumn.moveLeft();
        });
        right.setOnMouseClicked(e ->{
            viewModelColumn.moveRight();
        });
        cardListView.setOnMouseClicked(e -> {
            if(e.getClickCount() == 3){
                viewModelColumn.addCard();
            }
        });
        this.setOnMouseClicked(e -> {
            if(e.getButton() == MouseButton.SECONDARY){
                viewModelColumn.removeColumn();
            }
        });

    }
}

