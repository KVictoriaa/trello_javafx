package mvvm;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Card;

import java.util.Optional;


public class ViewModelCard {

    private final Card card;
    private ViewModelBoard viewModelBoard;


    public ViewModelCard(Card card, ViewModelBoard viewModelBoard) {
        this.card = card;
        this.viewModelBoard = viewModelBoard;

    }

    public Card getCard() {
        return card;
    }

    public void moveUp() {

        card.getColumn().moveCardUp(card);
    }

    public void moveDown() {

        card.getColumn().moveCardDown(card);
    }

    public void moveLeft() {
        card.getColumn().moveCardLeft(card);
    }

    public void moveRight() {
        card.getColumn().moveCardRight(card);
    }

    public void removeCard() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression");
        alert.setContentText("Voulez-vous vraiment supprimer cette carte?");
        Optional<ButtonType> res = alert.showAndWait();
        if (res.isPresent() && res.get() == ButtonType.OK) {
            card.getColumn().removeCardList(card);
        }
    }
    public void setTitle(String title) {
    }

}

