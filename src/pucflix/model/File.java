package pucflix.model;

public abstract class File {

    String name;

    public String getName() { return name; }

    public abstract void create();
    public abstract void read();
    public abstract void update();
    public abstract void delete();
}
