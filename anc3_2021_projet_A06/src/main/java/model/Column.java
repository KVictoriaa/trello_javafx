package model;

public class Column implements Comparable<Column>{
    private String title;
    private Integer position;

    public Column(String title) {
        setTitle(title);
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
    public String toString() {
        return title;
    }


    @Override
    public int compareTo(Column that) {
        return getPosition().compareTo(that.getPosition());
    }
}
