package org.yandrut.gmail_at.steps;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import io.cucumber.java.en.Then;

public class AssertionSteps extends AbstractSteps {

    @Then("User should be logged in to the mail service")
    public void userShouldBeLoggedInToTheMailService() {
        boolean isUserLoggedIn = homePage.isUserLoggedIn();

        assertThat(isUserLoggedIn)
            .describedAs("User should be logged in to the mail service")
            .isTrue();
    }

    @Then("The draft should contain the {string} and {string}")
    public void theDraftShouldContainTheSubjectAndBody(String subject, String body) {
        String draftEmailInfo = modalWindow.getDraftEmailInfo();

        assertThat(draftEmailInfo)
            .contains(subject, body)
            .as("Draft content should contain subject and body of the email");
    }

    @Then("Mail with {string} is present in the mails page")
    public void mailWithSubjectIsPresentInTheMailsPage(String subject) {
        boolean isMailPresentInTheSentPage = sentMailsPage.isEmailWithSubjectPresent(subject);

        assertThat(isMailPresentInTheSentPage)
            .as("Mail with a: \"" + subject + "\" should be present in the sent page")
            .isTrue();
    }

    @Then("Mail with subject {string} should be present on the main page")
    public void mailWithSubjectShouldBePresentOnTheMainPage(String subject) {
        boolean isMailPresent = homePage.isEmailWithASubjectPresent(subject);

        assertThat(isMailPresent)
            .as("Mail with a: \"" + subject + "\" subject should be present in the inbox page")
            .isTrue();
    }
}
