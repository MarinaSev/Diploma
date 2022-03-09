package ru.netology.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentFormPage {
    private final SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthField = $("[placeholder='08']");
    private final SelenideElement yearField = $("[placeholder='22']");
    private final SelenideElement cardHolderField = $$(".input__inner").find(Condition.text("Владелец")).$(".input__control");
    private final SelenideElement cvvField = $("[placeholder='999']");
    private final SelenideElement buttonContinue = $$(".button__text").find(Condition.text("Продолжить"));

    private SelenideElement successNotification = $(withText("Операция одобрена Банком."));
    private SelenideElement errorNotification = $(withText("Банк отказал в проведении операции."));
    private SelenideElement wrongFormat = $(withText("Неверный формат"));
    private SelenideElement wrongDate = $(withText("Неверно указан срок действия карты"));
    private SelenideElement wrongExpired = $(withText("Истёк срок действия карты"));
    private SelenideElement errorNecessary = $(withText("Поле обязательно для заполнения"));

    public void fillForm(DataGenerator.CardInfo cardInfo) {
        cardNumberField.setValue(cardInfo.getCardNumber());
        monthField.setValue(cardInfo.getMonth());
        yearField.setValue(cardInfo.getYear());
        cardHolderField.setValue(cardInfo.getHolder());
        cvvField.setValue(cardInfo.getCvv());
        buttonContinue.click();
    }

    public void checkSuccessMessage() {
        successNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void checkErrorMessage() {
        errorNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void checkWrongFormatMessage() {
        wrongFormat.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void checkWrongDateMessage() {
        wrongDate.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void checkWrongExpiredMessage() {
        wrongExpired.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }
}
