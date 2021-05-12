package mvvm;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Card;
import mvvm.card.SetTitleCardCommand;
import mvvm.column.*;

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

        MoveCardToUpCommand moveCardToUpCommand = new MoveCardToUpCommand(card);
        Processor.getInstance().execute(moveCardToUpCommand);
        viewModelBoard.refreshUndoRedoProperty();
    }

    public void moveDown() {


        MoveCardToDownCommmand moveCardToDownCommmand = new MoveCardToDownCommmand(card);
        Processor.getInstance().execute(moveCardToDownCommmand);
        viewModelBoard.refreshUndoRedoProperty();

    }

    public void moveLeft() {
        MoveCardToLeftCommand moveCardToLeftCommand = new MoveCardToLeftCommand(card,card.getPosition());
        Processor.getInstance().execute(moveCardToLeftCommand);
        viewModelBoard.refreshUndoRedoProperty();
    }

    public void moveRight() {
        MoveCardToRightCommand moveCardToRightCommand = new MoveCardToRightCommand(card, card.getPosition());
        Processor.getInstance().execute(moveCardToRightCommand);
        viewModelBoard.refreshUndoRedoProperty();
    }

    public void removeCard() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression");
        alert.setContentText("Voulez-vous vraiment supprimer cette carte?");
        Optional<ButtonType> res = alert.showAndWait();
        if(res.isPresent() && res.get() == ButtonType.OK) {

            RemoveCardCommand removeCardCommand = new RemoveCardCommand(card,card.getPosition());
            Processor.getInstance().execute(removeCardCommand);
        }
      viewModelBoard.refreshUndoRedoProperty();
    }

    public void setTitle(String title) {
        Processor.getInstance().execute(new SetTitleCardCommand(card ,title ,card.getName()));

        viewModelBoard.refreshUndoRedoProperty();
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

