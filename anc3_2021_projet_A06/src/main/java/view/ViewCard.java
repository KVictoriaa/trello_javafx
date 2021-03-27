package view;


import javafx.geometry.Pos;
import javafx.scene.control.Button;

import javafx.scene.layout.BorderPane;
import model.Card;
import mvvm.ViewModelBoard;
import mvvm.ViewModelCard;

public class ViewCard extends BorderPane {
    private EditableLabel editableLabel;
    private ViewModelCard viewModelCard;
    private ViewModelBoard viewModelBoard;
    private Button up = new Button("⬆");
    private Button right = new Button("➡");
    private Button down = new Button("⬇");
    private Button left = new Button("⬅");



    public ViewCard(Card card, ViewModelBoard viewModelBoard) {
        this.viewModelBoard = viewModelBoard;
        viewModelCard = new ViewModelCard(card,viewModelBoard);

        configCard();

    }

    private void configCard(){

        this.setTop(up);
        BorderPane.setAlignment(up, Pos.TOP_CENTER);

        this.setRight(right);

        this.setBottom(down);
        BorderPane.setAlignment(down, Pos.BOTTOM_CENTER);

        this.setLeft(left);

        this.setCenter(editableLabel);
        this.setStyle("-fx-background-color: #e1bee7;");
    }



}
