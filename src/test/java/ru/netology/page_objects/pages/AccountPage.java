package ru.netology.page_objects.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class AccountPage {
    private final SelenideElement header = $x("//*[@data-test-id='dashboard']");
    private ElementsCollection cards = $$(".list__item div");

    public AccountPage() {
        header.shouldBe(Condition.visible).shouldHave(Condition.text("Личный кабинет"));
    }

    public int getCardBalance(String id) {
        SelenideElement card = cards.findBy(Condition.attributeMatching("data-test-id", id));
        return extractCardBalance(card.getText());
    }

    private int extractCardBalance(String text) {
        int startIndex = text.indexOf("баланс: ");
        int finishIndex = text.indexOf(" р.");
        String value = text.substring(startIndex + "баланс: ".length(), finishIndex);
        return Integer.parseInt(value);
    }

    public AddToCardPage initiateTransferToCard(String id) {
        SelenideElement card = cards.findBy(Condition.attribute("data-test-id", id));
        card.$x(".//button").click();
        return new AddToCardPage();
    }

    public void checkCardBalance(String id, int expectedBalance) {
        SelenideElement card = cards.findBy(Condition.attributeMatching("data-test-id", id));
        card.shouldBe(Condition.visible).should(Condition.text("баланс: " + expectedBalance + " р."));
    }
}
