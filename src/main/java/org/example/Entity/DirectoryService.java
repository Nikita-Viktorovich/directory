package org.example.Entity;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import com.mysql.jdbc.PreparedStatement;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;


public class DirectoryService {

    private String name;
    private LinkedHashMap<String, List<?>> directory = new LinkedHashMap<>();
    private List<String> typesField = new ArrayList<>();


    public DirectoryService() {
    }

    public DirectoryService(String name, LinkedHashMap<String, List<?>> directory) {
        setDirectory(directory);
        setName(name);
    }

    public List<String> getTypesField() {
        return typesField;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedHashMap<String, List<?>> getDirectory() {
        return directory;
    }

    public void setDirectory(LinkedHashMap<String, List<?>> directory) {
        this.directory = directory;
    }

    public String dropTable() {
        return "DROP TABLE " + getName() + ";";
    }

    public String deleteAllStrs() {
        return "DELETE FROM " + getName() + ";";
    }

    public String createTable() {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        for (String str : getDirectory().keySet()) {
            stringBuilder.append(str).append(" ").append(getTypesField().get(i++)).append(",");
        }
        stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
        return new String("CREATE TABLE " + getName() + " (" + stringBuilder + ");");
    }

    public void getType() {
        typesField.clear();
        for (String s : getDirectory().keySet()) {
            if (getDirectory().get(s).get(0).getClass().getSimpleName().equals("String")) typesField.add("VARCHAR(30)");
            else if (getDirectory().get(s).get(0).getClass().getSimpleName().equals("Integer")) typesField.add("INT");
            else if (getDirectory().get(s).get(0).getClass().getSimpleName().equals("Double")) typesField.add("DOUBLE");
            else if (getDirectory().get(s).get(0).getClass().getSimpleName().equals("Date")) typesField.add("DATE");
            else if (getDirectory().get(s).get(0).getClass().getSimpleName().equals("Boolean"))
                typesField.add("BOOLEAN");
        }
    }

    public void connectDBs() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/directory", "root", "1234")) {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(createTable());
            preparedStatement.executeUpdate();
            System.out.println(createTable());
            preparedStatement.close();
        } catch (SQLException e) {
            e.getCause();
        }
    }

    public void insertRowDB() {
        String res = "";
        StringBuilder fields = new StringBuilder();
        for (String keyq : getDirectory().keySet()) {
            res = keyq;
            fields.append(keyq).append(",");
        }
        fields.delete(fields.length() - 1, fields.length());
        StringBuilder val = new StringBuilder();

        for (int i = 0; i < getDirectory().size(); i++) {
            val.append("?,");
        }

        val = new StringBuilder(val.substring(0, val.length() - 1));
        String sqlin = "INSERT into " + getName() + "(" + fields + ") " +
                "values (" + val + ");";
        System.out.println(sqlin);

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/directory", "root", "1234")) {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sqlin);

            int q = 1;
            int k = 0;

            ArrayList<List<?>> arrarr = new ArrayList<>(getDirectory().values());
            for (int c = 0; c < getDirectory().get(res).size() + 1; c++) {
                if (k == getTypesField().size()) {
                    k = 0;
                    q = 1;
                    System.out.println(preparedStatement.asSql() + "IF");
                    preparedStatement.addBatch();
                    if (c == getDirectory().get(res).size()) break;
                }
                for (List<?> l : arrarr) {

                    if (typesField.get(k).equals("VARCHAR(30)")) {
                        preparedStatement.setString(q, String.valueOf(l.get(c)));
                    } else if (typesField.get(k).equals("BOOLEAN")) {
                        preparedStatement.setBoolean(q, (Boolean) l.get(c));
                    } else if (typesField.get(k).equals("DOUBLE")) {
                        preparedStatement.setDouble(q, (Double) l.get(c));
                    } else if (typesField.get(k).equals("INT")) {
                        preparedStatement.setInt(q, (Integer) l.get(c));
                    } else if (typesField.get(k).equals("DATE")) {
                        preparedStatement.setDate(q, (Date) l.get(c));
                    }
                    k++;
                    q++;
                }
            }
            preparedStatement.executeBatch();
            System.out.println(sqlin);
            System.out.println(preparedStatement.asSql());
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchRows(String nameField, String nameField2, Object val, Object val1) {
        String sqlsel = "SELECT *" +
                " FROM " + getName() +
                " WHERE " + nameField + " IN ('" + val + "')" + " and " + nameField2 + " IN ('" + val1 + "');";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/directory", "root", "1234")) {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sqlsel);
            System.out.println(preparedStatement.asSql());
            ResultSet rs = preparedStatement.executeQuery(sqlsel);
            while (rs.next()) {
                System.out.println(rs.getString(nameField) + " " + rs.getString(nameField2));
            }
            preparedStatement.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRows(String field, Object oldValue, Object newValue) {

        String sqlupdate = "UPDATE " + getName() +
                " SET " + field + " = " + newValue +
                " WHERE " + field + " = " + oldValue + ";";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/directory", "root", "1234")) {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sqlupdate);
            System.out.println(preparedStatement.asSql());
            System.out.println(preparedStatement.executeUpdate() + " запись изменена");
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setField(String oldField, String newField) {
        List<?> arr = getDirectory().get(oldField);
        getDirectory().put(newField, new ArrayList<>(arr)); //изменить поле справочника
        getDirectory().remove(oldField);
        getType();
    }

    public void transaction(String sql) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/directory", "root", "1234")) {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
            System.out.println(sql);

            statement.execute();
            statement.close();
        } catch (SQLException ex) {
            ex.getCause();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectoryService directory1 = (DirectoryService) o;
        return Objects.equals(name, directory1.name) &&
                Objects.equals(directory, directory1.directory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, directory);
    }
}
