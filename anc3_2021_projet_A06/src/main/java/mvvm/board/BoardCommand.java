package mvvm.board;

import model.Board;
import mvvm.Command;

abstract class BoardCommand implements Command {

    private final Board board;

    public BoardCommand(Board board) {
        this.board = board;
    }
    public Board getBoard() {
        return board;
    }
}
