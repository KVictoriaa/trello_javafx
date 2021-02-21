package model;

public abstract class MovableItems implements Comparable<MovableItems> {
    private String title;
    private Integer position;

    public MovableItems(String title, Integer position) {
        this.title = title;
        this.position = position;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Override
    public int compareTo(MovableItems that) {
        return getPosition().compareTo(that.getPosition());
    }



}