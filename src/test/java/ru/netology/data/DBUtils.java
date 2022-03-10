package ru.netology.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class DBUtils {

    public static Connection getConnection() throws SQLException {
        String dbUrl = System.getProperty("db.url");
        String login = System.getProperty("login");
        String password = System.getProperty("password");
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
        try (var connection = getConnection()) {
            String status = runner.query(connection, query, new ScalarHandler<String>());
            return status;
        }
    }

    public static String getCreditRequestStatus() throws SQLException {
        String statusSQL = "SELECT status FROM credit_request_entity";
        return getStatus(statusSQL);
    }

    private static String getCreditStatus(String query) throws SQLException {
        var runner = new QueryRunner();
        try (var connection = getConnection()) {
            String status = runner.query(connection, query, new ScalarHandler<String>());
            return status;
        }
    }
}
