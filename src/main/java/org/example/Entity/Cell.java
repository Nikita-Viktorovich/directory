package org.example.Entity;

public class Cell<T> {
    private T data;
    private int ID;

    public Cell(int id, T data) {
        setData(data);
        setID(id);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        if (!(data instanceof String || data instanceof java.sql.Date || data instanceof Integer || data instanceof Boolean || data instanceof Double))
            throw new IllegalArgumentException();
        this.data = data;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "data=" + data +
                '}';
    }
}
