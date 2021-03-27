package view;


import javafx.geometry.Pos;
import javafx.scene.control.Button;

import javafx.scene.input.MouseButton;
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
        editableLabel = new EditableLabel(viewModelCard.cardNameProperty(), null,null,viewModelCard);
        configCard();
        configAction();
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
    public void configAction(){
        down.setOnMouseClicked(e -> {
            viewModelCard.moveDown();
        });
        up.setOnMouseClicked(e ->{
            viewModelCard.moveUp();
        });
        left.setOnMouseClicked(e ->{
            viewModelCard.moveLeft();
        });
        right.setOnMouseClicked(e ->{
            viewModelCard.moveRight();
        });
        this.setOnMouseClicked(e -> {
            if(e.getButton() == MouseButton.SECONDARY){
                viewModelCard.removeCard();
            }
        });

    }



}
