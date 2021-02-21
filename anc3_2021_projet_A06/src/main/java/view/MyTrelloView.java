package view;

import javafx.collections.ObservableList;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


import model.Card;
import model.Column;
import mvvm.ViewModel;
import model.Direction;

import java.util.Optional;

public class MyTrelloView extends VBox {
    TextField title;
    ListView<Column> boardView;
    ViewModel viewModel;


    public MyTrelloView(String title, ObservableList<Column> columns, ViewModel viewModel) {
        this.viewModel = viewModel;
        setTitle(new TextField(title));

        this.title.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getClickCount() == 2) {
                this.title.setEditable(true);
            }
        });

        this.title.setOnKeyPressed(event -> {
            if (KeyCode.ENTER.equals(event.getCode()) || KeyCode.TAB.equals(event.getCode())) {
                this.getTitle().setText(this.title.getText());
                this.title.setEditable(false);
            } else if (KeyCode.ESCAPE.equals(event.getCode())) {
                this.title.setText(this.getTitle().getText());
                this.title.setEditable(false);
            }
        });

        setBoardView(new ListView<>(columns));


        boardView.setCellFactory(listView -> new ColumnCell());
        getBoardView().setOrientation(Orientation.HORIZONTAL);
        getChildren().addAll(getTitle(), boardView);

        boardView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getClickCount() == 2) {
                Column lastColumn = viewModel.getColumns().get(viewModel.getColumns().size() - 1);
                viewModel.addColumn(new Column("colonne", lastColumn.getPosition() + 1));
            }
        });
    }

    public TextField getTitle() {
        return title;
    }

    public void setTitle(TextField title) {
        this.title = title;
    }

    public ListView<Column> getBoardView() {
        return boardView;
    }

    public void setBoardView(ListView<Column> boardView) {
        this.boardView = boardView;
    }

    final class ColumnCell extends ListCell<Column> {
        VBox vbox;
        TextField field;
        HBox hbox = new HBox();

        ColumnCell() {
            super();
            vbox = new VBox();
            field = new TextField();
            field.setEditable(false);
            Button left = new Button("⬅");
            Button right = new Button("➡");
            vbox.getChildren().setAll(hbox);
            hbox.getChildren().setAll(left, field, right);
            right.setOnAction(event -> viewModel.moveColumn(getItem(), Direction.RIGHT));
            left.setOnAction(event -> viewModel.moveColumn(getItem(), Direction.LEFT));

        }

        final class CardCell extends ListCell<Card> {
            BorderPane borderPane;
            TextField field;


            CardCell() {
                super();
                borderPane = new BorderPane();
                Button up = new Button("⬆");
                borderPane.setTop(up);
                BorderPane.setAlignment(up, Pos.TOP_CENTER);
                up.setOnAction(event -> viewModel.moveCard(getItem(), Direction.UP));


                Button right = new Button("➡");
                borderPane.setRight(right);
                right.setOnAction(event -> viewModel.moveCard(getItem(), Direction.RIGHT));


                Button down = new Button("⬇");
                borderPane.setBottom(down);
                BorderPane.setAlignment(down, Pos.BOTTOM_CENTER);
                down.setOnAction(event -> viewModel.moveCard(getItem(), Direction.DOWN));


                Button left = new Button("⬅");
                borderPane.setLeft(left);
                left.setOnAction(event -> viewModel.moveCard(getItem(), Direction.LEFT));


                field = new TextField();
                field.setEditable(false);
                borderPane.setCenter(field);


            }

            @Override
            protected void updateItem(Card card, boolean empty) {
                super.updateItem(card, empty);
                if (!empty) {
                    field.setText(card.toString());
                    setGraphic(borderPane);

                    field.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                        if (event.getClickCount() == 2) {
                            field.setEditable(true);
                        }
                    });
                    field.setOnKeyPressed(event -> {
                        if (KeyCode.ENTER.equals(event.getCode()) || KeyCode.TAB.equals(event.getCode())) {
                            card.setTitle(field.getText());
                            field.setEditable(false);
                        } else if (KeyCode.ESCAPE.equals(event.getCode())) {
                            field.setText(card.getTitle());
                            field.setEditable(false);
                        }
                    });
                    ContextMenu contextMenu = new ContextMenu();
                    MenuItem delete = new MenuItem("Supprimer");
                    delete.setOnAction(actionEvent -> {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Confirmation");
                        alert.setHeaderText("Confirmation");
                        alert.setContentText("Supprimer  " + card.getTitle() + "?");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.isPresent() && result.get() == ButtonType.OK) {
                            card.getColumn().getCards().remove(card);
                        }
                    });

                    contextMenu.getItems().add(delete);
                    borderPane.setOnContextMenuRequested(contextMenuEvent -> contextMenu.show(
                            borderPane, contextMenuEvent.getScreenX(), contextMenuEvent.getScreenY()
                    ));

                } else
                    setGraphic(null);
            }
        }
    }
}

