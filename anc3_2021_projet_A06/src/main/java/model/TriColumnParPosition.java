package model;

import java.util.Comparator;

public class TriColumnParPosition implements Comparator<Column> {
    @Override
    public int compare(Column o1, Column o2) {
        return o1.getPosition() - o2.getPosition();
    }
}
