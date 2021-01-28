package org.example.Entity;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class DBUtil {

    private static final String DRIVER = "driver.class.name";
    private static final String URL = "db.url";
    private static final String USER = "db.username";
    private static final String PASSWORD = "db.password";

    private static Connection connection;
    private static Properties properties;

    static {
        try {
            properties = new Properties();
            properties.load(new FileInputStream("src\\main\\java\\org\\example\\resources\\database.properties"));
            Class.forName(properties.getProperty(DRIVER));
            connection = (Connection) DriverManager.getConnection(properties.getProperty(URL), properties.getProperty(USER), properties.getProperty(PASSWORD));

        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void statement(String sql) {
        try {
            getConnection().setAutoCommit(false);
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement(sql);
            preparedStatement.execute();
            getConnection().commit();
            preparedStatement.close();
            System.out.println(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void bagStatement(ArrayList<String> transactions) {
        try {
            PreparedStatement preparedStatement = (PreparedStatement) getConnection().prepareStatement("");
            getConnection().setAutoCommit(false);
            for (String s : transactions) {
                preparedStatement.addBatch(s);
            }
            preparedStatement.executeBatch();
            getConnection().commit();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
