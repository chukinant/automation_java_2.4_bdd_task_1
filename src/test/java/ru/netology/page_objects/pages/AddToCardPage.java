package ru.netology.page_objects.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;

public class AddToCardPage {
    private final SelenideElement addToCardHeader = $x("//form[@method='post']/..//*[@data-test-id]/following-sibling::*[contains(@class, 'heading')]");
    private final SelenideElement cardFromField = $x("//*[@data-test-id='from']//input");
    private final SelenideElement amountField = $x("//*[@data-test-id='amount']//input");
    private final SelenideElement submitButton = $x("//button[@data-test-id='action-transfer']");

    public AddToCardPage() {
        addToCardHeader.shouldBe(Condition.visible).shouldHave(Condition.text("Пополнение карты"));
    }

    public AccountPage moneyTransfer(String cardFrom, int amount) {
        amountField.setValue(String.valueOf(amount));
        cardFromField.setValue(cardFrom);
        submitButton.click();
        return new AccountPage();
    }

    public void emptyAmountFieldTransfer(String cardFrom, int amount) {
        amountField.setValue(String.valueOf(amount));
        cardFromField.setValue(cardFrom);
        amountField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        submitButton.click();
    }

    public void invalidCardFromTransfer(String cardFrom, int amount) {
        amountField.setValue(String.valueOf(amount));
        cardFromField.setValue(cardFrom);
        cardFromField.sendKeys(Keys.BACK_SPACE);
        submitButton.click();
    }

    public void findErrorMsg() {
        $x("//*[@data-test-id='error-notification']").shouldBe(Condition.visible).
                shouldHave(Condition.text("Ошибка"));
    }
}
