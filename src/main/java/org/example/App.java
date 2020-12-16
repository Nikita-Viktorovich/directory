package org.example;

import org.example.Entity.DirectoryService;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;


public class App {

    public static void main(String[] args)  {
        String field1 = "name";
        String field2 = "lastName";
        String field3 = "count";            //ключи, названия полей
        String field4 = "date";
        String field5 = "late";

        ArrayList<String> arraystr = new ArrayList<>();
        ArrayList<String> arraystrrs = new ArrayList<>();
        ArrayList<Integer> arrayListint = new ArrayList<>();            //контейенеры для значений
        ArrayList<java.sql.Date> arrayListdates = new ArrayList<>();
        ArrayList<Boolean> arrayListbools = new ArrayList<>();


        arraystr.add("Nikita");
        arraystrrs.add("qwert");
        arrayListint.add(2);                      //1 запись
        arrayListdates.add(Date.valueOf("2012-12-11"));
        arrayListbools.add(true);

        arraystr.add("Don");
        arraystrrs.add("qwertyy");                  //2 запись
        arrayListint.add(1);
        arrayListdates.add(Date.valueOf("2014-11-10"));
        arrayListbools.add(true);

        arraystr.add("Diana");
        arraystrrs.add("room");                  //3 запись
        arrayListint.add(0);
        arrayListdates.add(Date.valueOf("2020-11-10"));
        arrayListbools.add(false);

        //создать новый справочник

        DirectoryService d = new DirectoryService();
        d.getDirectory().put(field1, new ArrayList<String>(arraystr));
        d.getDirectory().put(field2, new ArrayList<String>(arraystrrs));
        d.getDirectory().put(field3, new ArrayList<Integer>(arrayListint));         //создание справочника
        d.getDirectory().put(field4, new ArrayList<java.sql.Date>(arrayListdates));
        d.getDirectory().put(field5, new ArrayList<Boolean>(arrayListbools));
        d.getType();


        d.setName("peoples"); //изменить имя справочника

        d.setField("lastName", "surname"); //изменить поле

        d.connectDBs(); // создать таблицу

        d.insertRowDB(); // вставить записи

        d.searchRows("name", "count", "Diana", 3); // найти поле по 2-м параметрам

        d.updateRows("count", 2, 1); // изменить запись

        //раскоментируйте код ниже если нужно проверить удаление таблицы или записей

        //  d.transaction(d.deleteAllStrs()); // удалить все записи

        //d.transaction(d.dropTable());  // удалить таблицу

    }
}
