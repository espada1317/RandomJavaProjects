package steps;

import config.UserConfig;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.BasicPage;
import pages.SignInPage;
import test.TestBase;

import static com.codeborne.selenide.Selenide.open;

public class SignInStepDefinition{

    BasicPage basicPage = new BasicPage();
    SignInPage signInPage = new SignInPage();

    @Before
    public void openUrl() {
        open("https://librarius.md/ro/");
    }

    @Given("I go to website")
    public void iGoToWebsite() {
        openUrl();
    }

    @When("I select menu {string}")
    public void iSelectMenu(String arg0) {
        basicPage.clickDivBlock(arg0);
    }

    @And("I login with password")
    public void iLoginWithPassword() {
        signInPage.setLoginInput(UserConfig.USER_LOGIN);
        signInPage.setPasswordInput(UserConfig.USER_PASSW);
    }

    @And("Click {string} button")
    public void clickButton(String arg0) {
        signInPage.clickButton(arg0);
    }

    @Then("I should be logged on website with account")
    public void iShouldBeLoggedOnWebsiteWithAccount() {
        basicPage.checkIfLoggedAccountIsVisible(UserConfig.USER_LOGIN);
    }
}