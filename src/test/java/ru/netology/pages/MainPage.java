package ru.netology.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {

    private SelenideElement heading = $(byText("Путешествие дня"));
    private SelenideElement buyButton = $$(".button__text").find(Condition.text("Купить"));
    private SelenideElement paymentFormHeading = $(byText("Оплата по карте"));
    private SelenideElement creditButton = $$(".button__text").find(Condition.text("Купить в кредит"));
    private SelenideElement creditFormHeading = $(byText("Кредит по данным карты"));

    public MainPage() {
        heading.shouldBe(Condition.visible);
    }

    public PaymentFormPage openPaymentForm(){
        buyButton.click();
        paymentFormHeading.shouldBe(Condition.visible);
        return new PaymentFormPage();
    }

    public CreditFormPage openCreditForm(){
        creditButton.click();
        creditFormHeading.shouldBe(Condition.visible);
        return new CreditFormPage();
    }

}
