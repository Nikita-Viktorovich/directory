package org.example;

import org.example.Entity.*;

import java.util.ArrayList;


public class App {

    public static void main(String[] args) {

        ArrayList<Field> fields = new ArrayList<>();
        fields.add(new Field(1, "name", Type.STRING));                                ///поля первой таблицы
        fields.add(new Field(2, "surname", Type.STRING));
        fields.add(new Field(3, "date", Type.DATE));

        ArrayList<Field> fields1 = new ArrayList<>();
        fields1.add(new Field(4, "name", Type.STRING));
        fields1.add(new Field(5, "Date", Type.DATE));                               //поля второй таблицы
        fields1.add(new Field(6, "count", Type.INTEGER));
        fields1.add(new Field(7, "verify", Type.BOOLEAN));

        ArrayList<Cell<?>> rowFirst = new ArrayList<>();
        rowFirst.add(new Cell<>(1, "Nikita"));
        rowFirst.add(new Cell<>(2, "Obukhov"));                                 // 1-я строка первой таблицы
        rowFirst.add(new Cell<>(3, java.sql.Date.valueOf("1998-11-11")));

        ArrayList<Cell<?>> rowTab = new ArrayList<>();
        rowTab.add(new Cell<>(7, "elon"));
        rowTab.add(new Cell<>(8, java.sql.Date.valueOf("1980-10-10")));                    //1-я строка второй таблицы
        rowTab.add(new Cell<>(9, 5));
        rowTab.add(new Cell<>(10, true));


        ArrayList<Cell<?>> rowSecond = new ArrayList<>();
        rowSecond.add(new Cell<>(4, "Vasya"));
        rowSecond.add(new Cell<>(5, "pupkin"));                  // 2-я строка первой таблицы
        rowSecond.add(new Cell<>(6, java.sql.Date.valueOf("2000-11-12")));

        ArrayList<Cell<?>> rowsrcc = new ArrayList<>();
        rowsrcc.add(new Cell<>(11, "ben"));
        rowsrcc.add(new Cell<>(12, java.sql.Date.valueOf("2000-11-11")));        //2-я строка второй таблицы
        rowsrcc.add(new Cell<>(13, 3));
        rowsrcc.add(new Cell<>(14, false));


        Row row = new Row(1, rowFirst);
        Row row1 = new Row(2, rowSecond);

        Row rowtab = new Row(3, rowTab);
        Row rowsqqq = new Row(4, rowsrcc);


        ArrayList<Row> rows = new ArrayList<>();
        rows.add(row);
        rows.add(row1);

        ArrayList<Row> rowsTab = new ArrayList<>();
        rowsTab.add(rowtab);
        rowsTab.add(rowsqqq);

        Table table = new Table(1, "friends", fields, rows);                // создание первой таблицы

        Table tablesec = new Table(2, "people", fields1, rowsTab);          //создание второй таблицы

        Service.createTable(table);
        Service.insertRow(table, row);
        Service.insertRow(table, row1);

        Service.createTable(tablesec);
        Service.insertRow(tablesec, rowtab);
        Service.insertRow(tablesec, rowsqqq);

        DBUtil.bagStatement(Service.SQLscript);     //добавление данных непосредственно в бд

        DBUtil.statement(Service.setField(table, "surname", "patronymic"));
        DBUtil.statement(Service.searchRows(table, "Nikita", "Obukhov"));
        DBUtil.statement(Service.updateRow(table, "name", "pupkin", "Drob"));

        DBUtil.statement(Service.truncate(tablesec)); //удаление строк второй таблицы
        DBUtil.statement(Service.deleteTable(tablesec)); //удаление второй таблицы

    }
}
