package ru.netology.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DBUtils;
import ru.netology.data.DataGenerator;
import ru.netology.pages.CreditFormPage;
import ru.netology.pages.MainPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataGenerator.*;
import static ru.netology.data.DataGenerator.getCvv;

public class CreditPaymentTest {

    private CreditFormPage creditPage;

    @BeforeEach
    public void setUp() {
        open(System.getProperty("app.url"));
        var mainPage = new MainPage();
        creditPage = mainPage.openCreditForm();
    }

    @AfterEach
    public void clearData() throws SQLException {
        DBUtils.clearData();
    }

    @Test
    public void shouldSendApprovedCard() {
        var validCard = DataGenerator.getValidCard();
        creditPage.fillForm(validCard);
        creditPage.checkSuccessMessage();
    }

    @Test
    public void shouldSendDeclinedCard() {
        var invalidCard = DataGenerator.getInvalidCard();
        creditPage.fillForm(invalidCard);
        creditPage.checkErrorMessage();
    }

    @Test
    public void shouldCheckStatusApprovedCard() throws SQLException {
        var validCard = DataGenerator.getValidCard();
        creditPage.fillForm(validCard);
        creditPage.checkSuccessMessage();
        assertEquals("APPROVED", DBUtils.getCreditRequestStatus());
    }

    @Test
    public void shouldCheckStatusDeclinedCard() throws SQLException {
        var invalidCard = DataGenerator.getInvalidCard();
        creditPage.fillForm(invalidCard);
        creditPage.checkErrorMessage();
        assertEquals("DECLINED", DBUtils.getCreditRequestStatus());
    }

    @Test
    public void shouldSendFormWithMonth00() {
        var month00CardInfo = new DataGenerator.CardInfo(getApprovedCardNumber(), "00", getYear(), getName(), getCvv());
        creditPage.fillForm(month00CardInfo);
        creditPage.checkWrongDateMessage();
    }
}
