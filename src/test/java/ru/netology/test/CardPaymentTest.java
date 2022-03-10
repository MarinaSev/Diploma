package ru.netology.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DBUtils;
import ru.netology.data.DataGenerator;
import ru.netology.pages.MainPage;
import ru.netology.pages.PaymentFormPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataGenerator.*;

public class CardPaymentTest {
    private PaymentFormPage paymentPage;

    @BeforeEach
    public void setUp() {
        open(System.getProperty("app.url"));
        var mainPage = new MainPage();
        paymentPage = mainPage.openPaymentForm();
    }

    @AfterEach
    public void clearData() throws SQLException {
        DBUtils.clearData();
    }

    @Test
    public void shouldSendApprovedCard() {
        var validCard = DataGenerator.getValidCard();
        paymentPage.fillForm(validCard);
        paymentPage.checkSuccessMessage();
    }

    @Test
    public void shouldSendDeclinedCard() {
        var invalidCard = DataGenerator.getInvalidCard();
        paymentPage.fillForm(invalidCard);
        paymentPage.checkErrorMessage();
    }

    @Test
    public void shouldCheckStatusApprovedCard() throws SQLException {
        var validCard = DataGenerator.getValidCard();
        paymentPage.fillForm(validCard);
        paymentPage.checkSuccessMessage();
        assertEquals("APPROVED", DBUtils.getPaymentStatus());
    }

    @Test
    public void shouldCheckStatusDeclinedCard() throws SQLException {
        var invalidCard = DataGenerator.getInvalidCard();
        paymentPage.fillForm(invalidCard);
        paymentPage.checkErrorMessage();
        assertEquals("DECLINED", DBUtils.getPaymentStatus());
    }

    @Test
    public void shouldSendAnyCard() {
        var anyCard = DataGenerator.getAnyCard();
        paymentPage.fillForm(anyCard);
        paymentPage.checkErrorMessage();
    }

    @Test
    public void shouldSendFormWithEmptyCardNumberField() {
        var emptyField = new DataGenerator.CardInfo("", getMonth(), getYear(), getName(), getCvv());
        paymentPage.fillForm(emptyField);
        paymentPage.checkWrongFormatMessage();
    }

    @Test
    public void shouldSendFormWithEmptyMonthField() {
        var emptyField = new DataGenerator.CardInfo(getApprovedCardNumber(), "", getYear(), getName(), getCvv());
        paymentPage.fillForm(emptyField);
        paymentPage.checkWrongFormatMessage();
    }

    @Test
    public void shouldSendFormWithEmptyYearField() {
        var emptyField = new DataGenerator.CardInfo(getApprovedCardNumber(), getMonth(), "", getName(), getCvv());
        paymentPage.fillForm(emptyField);
        paymentPage.checkWrongFormatMessage();
    }

    @Test
    public void shouldSendFormWithEmptyNameField() {
        var emptyField = new DataGenerator.CardInfo(getApprovedCardNumber(), getMonth(), getYear(), "", getCvv());
        paymentPage.fillForm(emptyField);
        paymentPage.checkErrorNecessaryMessage();
    }

    @Test
    public void shouldSendFormWithEmptyCvvField() {
        var emptyField = new DataGenerator.CardInfo(getCardNumber(), getMonth(), getYear(), getName(), "");
        paymentPage.fillForm(emptyField);
        paymentPage.checkWrongFormatMessage();
    }

    @Test
    public void shouldSendFormWithShortCardNumber() {
        var CardNumber15Info = new DataGenerator.CardInfo(getNumber(15), getMonth(), getYear(), getName(), getCvv());
        paymentPage.fillForm(CardNumber15Info);
        paymentPage.checkWrongFormatMessage();
    }

    @Test
    public void shouldSendFormWithLongCardNumber() {
        var CardNumber17Info = new DataGenerator.CardInfo(getNumber(17), getMonth(), getYear(), getName(), getCvv());
        paymentPage.fillForm(CardNumber17Info);
        paymentPage.checkWrongFormatMessage();
    }

    @Test
    public void shouldSendFormWithWrongMonth() {
        var wrongMonthCardInfo = new DataGenerator.CardInfo(getApprovedCardNumber(), getWrongMonth(), getYear(), getName(), getCvv());
        paymentPage.fillForm(wrongMonthCardInfo);
        paymentPage.checkWrongDateMessage();
    }

    @Test
    public void shouldSendFormWithMonth00() {
        var month00CardInfo = new DataGenerator.CardInfo(getApprovedCardNumber(), "00", getYear(), getName(), getCvv());
        paymentPage.fillForm(month00CardInfo);
        paymentPage.checkWrongDateMessage();
    }

    @Test
    public void shouldSendFormWithPartialMonth() {
        var partialMonthCardInfo = new DataGenerator.CardInfo(getApprovedCardNumber(), getPlainNumber(), getYear(), getName(), getCvv());
        paymentPage.fillForm(partialMonthCardInfo);
        paymentPage.checkWrongFormatMessage();
    }

    @Test
    public void shouldSendFormWithWrongYear() {
        var wrongYearCardInfo = new DataGenerator.CardInfo(getApprovedCardNumber(), getMonth(), getWrongYear(), getName(), getCvv());
        paymentPage.fillForm(wrongYearCardInfo);
        paymentPage.checkWrongExpiredMessage();
    }

    @Test
    public void shouldSendFormWithPartialYear() {
        var partialYearCardInfo = new DataGenerator.CardInfo(getApprovedCardNumber(), getMonth(), getPlainNumber(), getName(), getCvv());
        paymentPage.fillForm(partialYearCardInfo);
        paymentPage.checkWrongFormatMessage();
    }

    @Test
    public void shouldSendFormWithHyphenInName() {
        var hyphenInNameCardInfo = new DataGenerator.CardInfo(getApprovedCardNumber(), getMonth(), getYear(), getNameWIhtHyphen(), getCvv());
        paymentPage.fillForm(hyphenInNameCardInfo);
        paymentPage.checkSuccessMessage();
    }

    @Test
    public void shouldSendFormWith3WordName() {
        var threeWordNameCardInfo = new DataGenerator.CardInfo(getApprovedCardNumber(), getMonth(), getYear(), getThreeWordName(), getCvv());
        paymentPage.fillForm(threeWordNameCardInfo);
        paymentPage.checkSuccessMessage();
    }

    @Test
    public void shouldSendFormWithOneWordName() {
        var oneWordNameCardInfo = new DataGenerator.CardInfo(getApprovedCardNumber(), getMonth(), getYear(), getFirstName(), getCvv());
        paymentPage.fillForm(oneWordNameCardInfo);
        paymentPage.checkWrongFormatMessage();
    }

    @Test
    public void shouldSendFormWithRusName() {
        var rusNameCardInfo = new DataGenerator.CardInfo(getApprovedCardNumber(), getMonth(), getYear(), getRuName(), getCvv());
        paymentPage.fillForm(rusNameCardInfo);
        paymentPage.checkWrongFormatMessage();
    }

    @Test
    public void shouldSendFormWitCvv000() {
        var cvv000CardInfo = new DataGenerator.CardInfo(getApprovedCardNumber(), getMonth(), getYear(), getName(), "000");
        paymentPage.fillForm(cvv000CardInfo);
        paymentPage.checkWrongFormatMessage();
    }

    @Test
    public void shouldSendFormWitParialCvv() {
        var parialCvvCardInfo = new DataGenerator.CardInfo(getApprovedCardNumber(), getMonth(), getYear(), getName(), getNumber(2));
        paymentPage.fillForm(parialCvvCardInfo);
        paymentPage.checkWrongFormatMessage();
    }
}
