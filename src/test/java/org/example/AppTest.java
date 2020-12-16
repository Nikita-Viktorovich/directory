package org.example;

import org.example.Entity.DirectoryService;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;


public class AppTest {

    @Test
    public void createTableDirectory1() {
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


        d.setName("people"); //изменить имя справочника

        d.setField("lastName", "surname"); //изменить поле

        d.connectDBs(); // создать таблицу

        d.insertRowDB(); // вставить записи

        d.searchRows("name", "count", "Diana", 3); // найти поле по 2-м параметрам

        d.updateRows("count", 2, 1); // изменить запись

        //раскоментируйте код ниже если нужно проверить удаление таблицы или записей

        d.transaction(d.deleteAllStrs()); // удалить все записи

        d.transaction(d.dropTable());  // удалить таблицу

    }

    @Test
    public void createTableDirectory2() {
        String field1 = "numberN";
        String field2 = "numberR";
        String field3 = "counter";            //ключи, названия полей
        String field4 = "useing";


        ArrayList<Integer> arrayListints = new ArrayList<>();
        ArrayList<Double> arrayListDouble = new ArrayList<>();                                                      //контейенеры для значений
        ArrayList<Boolean> arrayListbools = new ArrayList<>();
        ArrayList<Integer> arrayListCount = new ArrayList<>();


        arrayListints.add(4);
        arrayListDouble.add(2.0);
        arrayListbools.add(false);                      //1 запись
        arrayListCount.add(5);

        arrayListints.add(6);
        arrayListDouble.add(2.0);
        arrayListbools.add(false);                      //2 запись
        arrayListCount.add(2);

        arrayListints.add(24);
        arrayListDouble.add(2.0);
        arrayListbools.add(true);                      //3 запись
        arrayListCount.add(2);

        //создать новый справочник

        DirectoryService a = new DirectoryService();
        a.getDirectory().put(field1, new ArrayList<Integer>(arrayListints));
        a.getDirectory().put(field2, new ArrayList<Double>(arrayListDouble));
        a.getDirectory().put(field3, new ArrayList<Integer>(arrayListCount));
        a.getDirectory().put(field4, new ArrayList<Boolean>(arrayListbools));         //создание справочника
        a.getType();


        a.setName("FIGURES");

        a.setField("counter", "quantity"); //изменить поле

        a.setName("NUMBERS");  //изменить имя справочника

        a.connectDBs(); // создать таблицьу

        a.insertRowDB(); // вставить записи

        a.searchRows("quantity", "useing", "2", 0); // найти поле по 2-м параметрам

        a.updateRows("numberN", 4, 5); // изменить запись

        //раскоментируйте код ниже если нужно проверить удаление таблицы или записей

        a.transaction(a.deleteAllStrs()); // удалить все записи

        a.transaction(a.dropTable());  // удалить таблицу

    }

}
