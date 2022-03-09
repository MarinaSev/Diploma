package ru.netology.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DBUtils;
import ru.netology.pages.MainPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;

public class CardPaymentTest {
    public static String url = System.getProperty("app.url");

    @BeforeEach
    public void setUp() {
        open(url);
        var mainPage = new MainPage();
        mainPage.openPaymentForm();
    }

    @AfterEach
    public void clearData() throws SQLException {
        DBUtils.clearData();
    }

    @Test
    public void shouldSendValidCard() {

    }

}
