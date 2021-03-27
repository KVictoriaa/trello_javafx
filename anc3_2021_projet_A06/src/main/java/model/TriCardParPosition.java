package model;

import java.util.Comparator;

public class TriCardParPosition implements Comparator<Card> {

    @Override
    public int compare(Card o1, Card o2) {
        return o1.getPosition() - o2.getPosition();
    }
}
