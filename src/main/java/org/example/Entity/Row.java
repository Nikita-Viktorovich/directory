package org.example.Entity;


import java.util.ArrayList;

public class Row {
    private ArrayList<Cell<?>> row;
    private int ID;

    public Row(int id, ArrayList<Cell<?>> row) {
        setRow(row);
        setID(id);
    }

    public ArrayList<Cell<?>> getRow() {
        return row;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setRow(ArrayList<Cell<?>> row) {
        this.row = row;
    }

    @Override
    public String toString() {
        return "Row{" +
                "row=" + row +
                '}';
    }

}


