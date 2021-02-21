package view;

import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;


import model.Column;
import mvvm.ViewModel;
import model.Direction;

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
}
