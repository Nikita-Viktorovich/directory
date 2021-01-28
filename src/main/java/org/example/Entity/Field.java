package org.example.Entity;


import java.util.Objects;

public class Field {
    private int ID;
    private String name;
    private Type type;


    public Field(int id, String name, Type type) {
        setType(type);
        setName(name);
        setID(id);
    }

    public Type getType() {
        return type;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Field{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field field = (Field) o;
        return ID == field.ID &&
                Objects.equals(name, field.name) &&
                type == field.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, type);
    }
}
