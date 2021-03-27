package mvvm;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Card;

import java.util.Optional;


public class ViewModelCard {

    private final Card card;
    private final SimpleBooleanProperty disableCardUp = new SimpleBooleanProperty();
    private final SimpleBooleanProperty disableCardDown = new SimpleBooleanProperty();
    private final SimpleBooleanProperty disableCardLeft = new SimpleBooleanProperty();
    private final SimpleBooleanProperty disableCardRight = new SimpleBooleanProperty();
    private ViewModelBoard viewModelBoard;
    private SimpleStringProperty cardName = new SimpleStringProperty();


    public ViewModelCard(Card card, ViewModelBoard viewModelBoard) {
        this.card = card;
        this.viewModelBoard = viewModelBoard;
        configData();


    }

    public void configData() {
        disableCardUp.set(card.isFirtPosition());
        disableCardDown.set(card.isLastPostion());
        disableCardLeft.set(card.getColumn().isFirstPosition());
        disableCardRight.set(card.getColumn().isLastPosition());
        cardName.setValue(card.getName());

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

    public SimpleBooleanProperty disableCardDownProperty() {
        return disableCardDown;
    }

    public SimpleBooleanProperty disableCardUpProperty() {
        return disableCardUp;
    }

    public SimpleBooleanProperty disableCardLeftProperty() {
        return disableCardLeft;
    }

    public SimpleBooleanProperty disableCardRightProperty() {
        return disableCardRight;
    }

    public SimpleStringProperty cardNameProperty() {
        return cardName;
    }
}

