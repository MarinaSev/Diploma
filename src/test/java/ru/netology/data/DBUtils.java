package ru.netology.data;

import java.sql.Connection;
import java.sql.DriverManager;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class DBUtils {

    @SneakyThrows
    public static Connection getConnection() {
        String dbUrl = System.getProperty("db.url");
        String login = System.getProperty("login");
        String password = System.getProperty("password");
        final Connection connection = DriverManager.getConnection(dbUrl, login, password);
        return connection;
    }

    @SneakyThrows
    public static void clearData() {
        var runner = new QueryRunner();
        try (
                var connection = getConnection();
        ) {
            runner.update(connection, "DELETE FROM payment_entity;");
            runner.update(connection, "DELETE FROM credit_request_entity;");
            runner.update(connection, "DELETE FROM order_entity;");
        }
    }

    @SneakyThrows
    public static String getPaymentStatus() {
        String statusSQL = "SELECT status FROM payment_entity";
        return getStatus(statusSQL);
    }

    @SneakyThrows
    private static String getStatus(String query) {
        var runner = new QueryRunner();
        try (var connection = getConnection()) {
            String status = runner.query(connection, query, new ScalarHandler<String>());
            return status;
        }
    }

    @SneakyThrows
    public static String getCreditRequestStatus() {
        String statusSQL = "SELECT status FROM credit_request_entity";
        return getStatus(statusSQL);
    }

}
