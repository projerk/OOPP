package game;

public interface IContainer {
    void arrange();
    void add(Object object);
    Object get(int id);
}