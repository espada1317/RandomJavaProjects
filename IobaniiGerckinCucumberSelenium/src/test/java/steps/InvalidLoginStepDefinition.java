package steps;

import config.UserConfig;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.ContulMeuMenu;
import pages.SignInPage;

import static com.codeborne.selenide.Selenide.open;

public class InvalidLoginStepDefinition {
    SignInPage signInPage = new SignInPage();
    ContulMeuMenu contulMeuMenu = new ContulMeuMenu();

    @Before
    public void openUrl() {
        open("https://librarius.md/ro/");
    }

    @And("I log out with menu {string}")
    public void iLogOutWithMenu(String arg0) {
        if(contulMeuMenu.getLink(arg0)) {
            contulMeuMenu.clickLink(arg0);
        }
    }

    @And("I login with bad password")
    public void iLoginWithBadPassword() {
        signInPage.setLoginInput(UserConfig.USER_LOGIN);
        signInPage.setPasswordInput(UserConfig.generateRandomPassword());
    }

    @Then("Should be visible text {string}")
    public void shouldBeVisibleText(String arg0) {
        signInPage.getErrorLoginMessage(arg0);
    }
}