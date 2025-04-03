package org.yandrut.gmail_at.steps;

import static com.codeborne.selenide.Selenide.open;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.yandrut.gmail_at.configuration.SelenideConfiguration;
import org.yandrut.gmail_at.model.User;

public class ActionSteps extends AbstractSteps {

    @And("I login to the mail service as {string} user")
    public void iLoginToTheMailServiceAsUser(String userEmail) {
        var user = new User(userEmail, PASSWORD);
        homePage.loginToMailServiceAs(user);
    }

    @Given("I open the Google mail service page")
    public void iOpenTheGoogleMailServicePage() {
        SelenideConfiguration.configureSelenide();
        open(BASE_URL);
    }

    @When("I create new mail with {string} {string} and {string}")
    public void iCreateNewMailWithAddressSubjectAndBody(String address, String subject, String body) {
        homePage.clickOnWriteNewMail();
        modalWindow.createNewEmail(address, subject, body);
    }

    @And("I click on the drafts link")
    public void iClickOnTheDraftsLink() {
        homePage.openDraftsFolder();
    }

    @And("I click to the draft with {string}")
    public void iClickToTheDraftWithSubject(String subject) {
        draftsPage.clickToTheDraftWith(subject);
    }

    @And("Click on the send mail button")
    public void clickOnTheSendMailButton() {
        modalWindow.clickOnSendMail();
    }

    @And("Navigate to the sent page")
    public void navigateToTheSentPage() {
        modalWindow.navigateToSentPageLink();
    }
}
