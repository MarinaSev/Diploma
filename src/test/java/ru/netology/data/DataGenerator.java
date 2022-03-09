package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {

    private static Faker faker = new Faker(new Locale("en"));
    private static Random random = new Random();

    private DataGenerator() {
    }

    @Value
    @RequiredArgsConstructor
    public static class CardInfo {
        private String cardNumber;
        private String month;
        private String year;
        private String holder;
        private String cvv;
    }

    public static CardInfo getValidCard() {
        return new CardInfo(getApprovedCardNumber(), getMonth(), getYear(), getName(), getCvv());
    }

    public static CardInfo getInvalidCard() {
        return new CardInfo(getDeclinedCardNumber(), getMonth(), getYear(), getName(), getCvv());
    }

    public static CardInfo getAnyCard() {
        return new CardInfo(getCardNumber(), getMonth(), getYear(), getName(), getCvv());
    }

    public static String getApprovedCardNumber(){
        return "4444 4444 4444 4441";
    }

    public static String getDeclinedCardNumber(){
        return "4444 4444 4444 4442";
    }

    public static String getCardNumber() {
        return faker.business().creditCardNumber();
    }

    public static String getMonth() {
        String[] month = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        return month[random.nextInt(month.length)];
    }

    public static String getYear() {
        return LocalDate.now().plusYears(random.nextInt(5)+1).format(DateTimeFormatter.ofPattern("YY"));
    }

    public static String getWrongYear() {
        return LocalDate.now().plusYears(-1).format(DateTimeFormatter.ofPattern("YY"));
    }

    public static String getWrongMonth() {
        return Integer.toString(random.nextInt(99)+13);
    }

    public static String getPlainNumber() {
        return Integer.toString(random.nextInt(9)+1);
    }

    public static String getName() {
        return faker.name().fullName();
    }

    public static String getNameWIhtHyphen() {
        return (faker.name().firstName() + " " + faker.name().lastName() + "-" + faker.name().lastName());
    }

    public static String getFirstName() {
        return faker.name().firstName();
    }

    public static String getRuName() {
        Faker ruFaker = new Faker(new Locale("ru"));
        return ruFaker.name().fullName();
    }

    public static String getCvv() {
        return faker.number().digits(3);
    }

    public static String getNumber(int numberLenght) {
        return faker.number().digits(numberLenght);
    }

    public static String getInvalidCardNumber() {
        return faker.business().creditCardNumber();
    }
}
