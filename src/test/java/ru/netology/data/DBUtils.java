package ru.netology.data;

import com.github.javafaker.Faker;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DBUtils {

    private static String dbUrl = System.getProperty("db.url");
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

    public static String getPaymentStatus() throws SQLException {
        String statusSQL = "SELECT status FROM payment_entity";
        return getStatus(statusSQL);
    }

    private static String getStatus(String query) throws SQLException {
        var runner = new QueryRunner();
        try (var connection = DriverManager.getConnection(dbUrl, login, password)) {
            String status = runner.query(connection, query, new ScalarHandler<String>());
            return status;
        }
    }

    public static String getSQLAmount() throws SQLException {
        String amountSQL = "SELECT amount FROM payment_entity WHERE true;";
        return getAmount(amountSQL);
    }

    private static String getAmount(String query) throws SQLException {
        var runner = new QueryRunner();
        try (var connection = DriverManager.getConnection(dbUrl, login, password)) {
            String amount = runner.query(connection, query, new ScalarHandler<String>());
            return amount;
        }
    }

}
