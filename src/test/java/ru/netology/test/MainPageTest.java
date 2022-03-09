package ru.netology.test;

import org.junit.jupiter.api.Test;
import ru.netology.pages.MainPage;

import static com.codeborne.selenide.Selenide.open;

public class MainPageTest {

    public static String url = System.getProperty("app.url");

    @Test
    public void shouldOpenMainPage() {
        open(url);
        var mainPage = new MainPage();
    }

    @Test
    public void shouldOpenBuyingForm() {
        open(url);
        var mainPage = new MainPage();
        mainPage.openPaymentForm();
    }

    @Test
    public void shouldOpenCreditForm() {
        open(url);
        var mainPage = new MainPage();
        mainPage.openCreditForm();
    }

}
