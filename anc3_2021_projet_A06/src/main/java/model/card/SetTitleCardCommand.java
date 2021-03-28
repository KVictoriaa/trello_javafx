package model.card;

import model.Card;

public class SetTitleCardCommand extends  CardCommand {
    private String name;
    private String content;
    public SetTitleCardCommand(Card card, String name, String content) {
        super(card);
        this.name = name;
        this.content=content;

    }

    @Override
    public void execute() {
        this.getCard().setName(name);

    }

    @Override
    public void undo() {
        this.getCard().setName(content);

    }

    @Override
    public boolean canBeUndone() {
        return true;
    }

    @Override
    public String getNameAction() {
        return "la modification du titre";
    }
}
