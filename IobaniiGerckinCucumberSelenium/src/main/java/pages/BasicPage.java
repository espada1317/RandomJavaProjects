package pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class BasicPage {

    public void clickDivBlock(String text) {
        $(By.xpath("//div[text()='" + text + "']")).click();
    }

    public void checkIfLoggedAccountIsVisible(String text) {
        $(By.xpath("//a[text()='" + text + "']")).shouldBe(Condition.visible);
    }
}
