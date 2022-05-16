package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class SignInPage {
    private SelenideElement loginInput = $(By.xpath("//*[@id=\"inputEmail\"]"));
    private SelenideElement passwordInput = $(By.xpath("//*[@id=\"inputPassword\"]"));

    public void setLoginInput(String login) {
        this.loginInput.val(login);
    }

    public void setPasswordInput(String password) {
        this.passwordInput.val(password);
    }

    public void clickButton(String text) {
        $(By.xpath("//button[text()='" + text + "']")).click();
    }

    public void getErrorLoginMessage(String text) {
        $(By.xpath("//p[text()='" + text + "']")).shouldBe(Condition.visible);
    }
}