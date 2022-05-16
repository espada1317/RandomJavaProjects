package pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ContulMeuMenu {

    public void clickLink(String text) {
        $(By.xpath("//a[text()='" + text + "']")).click();
    }

    public Boolean getLink(String text) {
           if( $(By.xpath("//a[text()='" + text + "']")).shouldBe(Condition.visible).exists() ) {
                return true;
           }
        return false;
    }
}
