package org.example.Entity;

import java.util.ArrayList;

public class Service {

    public static ArrayList<String> SQLscript = new ArrayList<>();

    public static String setField(Table table, String oldName, String newName) {
        int id = 0;
        Type t = null;
        for (Field f : table.getFields()) {
            if (f.getName().equals(oldName)) {
                id = f.getID();
                f.setName(newName);
                break;
            }
        }
        return "update field set name = '" + newName + "' where ID_FIELD = " + id;
    }


    public static void createTable(Table table) {
        String createTable = "INSERT INTO Directory(ID, NAME_TABLE) VALUES(" + table.getID() + ",'" + table.getName() + "');";
        StringBuilder fields = new StringBuilder("INSERT INTO Field(ID_FIELD, Directory_id, NAME, TYPE) VALUES(");
        for (Field field : table.getFields()) {
            fields.append(field.getID()).append(",").append(table.getID()).append(",").append("'").append(field.getName()).append("', ").append("'").append(field.getType().getSql()).append("'), (");
        }

        fields.delete(fields.length() - 3, fields.length()).append(";");
        System.out.println(createTable);
        System.out.println(fields);

        SQLscript.add(createTable);
        SQLscript.add(fields + "");
    }

    public static void insertRow(Table table, Row row) {
        int i = 0;
        for (Cell<?> cell : row.getRow()) {
            StringBuilder insert = new StringBuilder("INSERT INTO ");
            if (cell.getData().getClass().getSimpleName().toUpperCase().equals(Type.STRING.name())) {
                insert.append("DataString (ID_DATA,data) values (").append(cell.getID()).append(",'").append(cell.getData()).append("');");
            } else if (cell.getData().getClass().getSimpleName().toUpperCase().equals(Type.DATE.name())) {
                insert.append("DataDate (ID_DATA,data) values(").append(cell.getID()).append(",").append(" DATE '").append(cell.getData()).append("');");
            } else if (cell.getData().getClass().getSimpleName().toUpperCase().equals(Type.DOUBLE.name())) {
                insert.append("DataDouble (ID_DATA,data) values(").append(cell.getID()).append(",").append(cell.getData()).append(");");
            } else if (cell.getData().getClass().getSimpleName().toUpperCase().equals(Type.INTEGER.name()) ||
                    cell.getData().getClass().getSimpleName().toUpperCase().equals(Type.BOOLEAN.name())) {
                insert.append("DataInt (ID_DATA,data) values(").append(cell.getID()).append(",").append(cell.getData()).append(");");
            }

            String data = "INSERT INTO DATA(ID, ID_ROW) values(" + cell.getID() + "," + row.getID() + ");";
            SQLscript.add(data);
            SQLscript.add(insert + "");
            SQLscript.add("INSERT INTO DATA_HAS_FIELD(ID_DATA, ID_FIELD, ID_DIRECTORY) VALUES (" + cell.getID() + "," + table.getFields().get(i++).getID() + "," + table.getID() + ");");
        }
    }

    public static String searchRows(Table table, Object val, Object val2) {
        ArrayList<Integer> ids = new ArrayList<>();
        StringBuilder idCount = new StringBuilder();

        for (int i = 0; i < table.getRows().size(); i++)
            for (int k = 0; k < table.getRows().get(i).getRow().size(); k++) {
                if (val.toString().equals(table.getRows().get(i).getRow().get(k).getData().toString())) {
                    ids.add(table.getRows().get(i).getRow().get(k).getID());
                } else if (val2.toString().equals(table.getRows().get(i).getRow().get(k).getData().toString())) {
                    ids.add(table.getRows().get(i).getRow().get(k).getID());
                }
            }

        for (int i : ids) {
            idCount.append(i).append(",");
        }
        idCount.delete(idCount.length() - 1, idCount.length());
        return "select id, data " +
                "from data left join ( " +
                "select * " +
                "from datastring " +
                "union " +
                "select * " +
                "from dataint " +
                "union " +
                "select * " +
                "from datadouble " +
                "union " +
                "select * " +
                "from datadate) as rows " +
                "on data.ID = ID_DATA " +
                "where ID_ROW in (" + idCount + ");";
    }

    public static String updateRow(Table table, String nameField, Object oldValue, Object newValue) {
        String type = "";
        int id = 0;

        for (Field field : table.getFields()) {
            if (field.getName().equals(nameField)) {
                type = field.getType().name();
            }
        }

        for (int i = 0; i < table.getRows().size(); i++) {
            for (int k = 0; k < table.getRows().get(i).getRow().size(); k++) {
                if (oldValue.toString().equals(table.getRows().get(i).getRow().get(k).getData().toString())) {
                    id = table.getRows().get(i).getRow().get(k).getID();
                }
            }
        }

        return "update data" + type + " set DATA = '" + newValue + "' where ID_DATA = " + id;
    }

    public static String truncate(Table table) {
        StringBuilder idRows = new StringBuilder();
        for (int i = 0; i < table.getRows().size(); i++) {
            idRows.append(table.getRows().get(i).getID()).append(',');
        }

        idRows.delete(idRows.length() - 1, idRows.length());
        table.getRows().clear();
        return "delete from data where id_row in (" + idRows + ");";
    }

    public static String deleteTable(Table table) {
        table.getFields().clear();
        return "delete from directory where id = " + table.getID();
    }
}