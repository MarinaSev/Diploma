package ru.netology.data;

import com.github.javafaker.Faker;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.*;

public class DBUtils {

    private static String dbUrl = System.getProperty("db.url");
    private static String appUrl = System.getProperty("app.url");
    //    private static String appPort = System.getProperty("app.port");
    private static String login = System.getProperty("login");
    private static String password = System.getProperty("password");

    public static Connection getConnection() throws SQLException {
        final Connection connection = DriverManager.getConnection(dbUrl, login, password);
        return connection;
    }

    public static void clearData() throws SQLException {
        var runner = new QueryRunner();
        try (
                var connection = getConnection();
        ) {
            runner.update(connection, "DELETE FROM payment_entity;");
            runner.update(connection, "DELETE FROM credit_request_entity;");
            runner.update(connection, "DELETE FROM order_entity;");
        }
    }


}
