package org.example.Entity;

import java.util.ArrayList;
import java.util.Objects;

public class Table {

    private String name;
    private ArrayList<Field> fields = new ArrayList<>();
    private ArrayList<Row> rows = new ArrayList<>();
    private int ID;

    public Table(int id, String name, ArrayList<Field> fields,  ArrayList<Row> rows) {
        setName(name);
        setFields(fields);
        setRows(rows);
        setID(id);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) { this.ID = ID; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Field> getFields() {
        return fields;
    }

    public void setFields(ArrayList<Field> fields) {
        this.fields = fields;
    }


    public ArrayList<Row> getRows() {
        return rows;
    }

    public void setRows(ArrayList<Row> rows) {
        this.rows = rows;
    }



    @Override
    public String toString() {
        return "Table{" +
                "name='" + name + '\'' +
                ", fields=" + fields +
                ", rows=" + rows +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Table table = (Table) o;
        return ID == table.ID &&
                Objects.equals(name, table.name) &&
                Objects.equals(fields, table.fields) &&
                Objects.equals(rows, table.rows);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, fields, rows, ID);
    }
}
