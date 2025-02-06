package ru.netology.page_objects.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.page_objects.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class VerificationPage {
    private final SelenideElement codeInput = $x("//*[@data-test-id='code']//input");
    private final SelenideElement submitButton = $x("//button[@data-test-id='action-verify']");

    public VerificationPage() {
        codeInput.shouldBe(Condition.visible);
    }

    public AccountPage validVerification(DataHelper.VerificationCode verificationCode) {
        codeInput.setValue(verificationCode.getCode());
        submitButton.click();
        return new AccountPage();
    }
}
