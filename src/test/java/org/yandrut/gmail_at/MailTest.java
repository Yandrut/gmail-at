package org.yandrut.gmail_at;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MailTest extends BaseTest {

    @Order(1)
    @DisplayName("Successful user login")
    @Test
    public void isUserLoggedIn() {
        boolean isUserLoggedIn = homePage.isUserLoggedIn();
        assertThat(isUserLoggedIn)
            .describedAs("User should be logged in to the mail service")
            .isTrue();
    }

    @Order(2)
    @DisplayName("Allows to create new email as a draft")
    @MethodSource(value = "mailData")
    @ParameterizedTest
    public void allowsToCreateNewMailAsDraft(String address, String subject, String body) {
        homePage.clickOnWriteNewMail();
        modalWindow.createNewEmail(address, subject, body);
        homePage.openDraftsFolder();
        draftsPage.clickToTheDraftWith(subject);
        String draftEmailInfo = modalWindow.getDraftEmailInfo();
        assertThat(draftEmailInfo)
            .contains(subject, body)
            .as("Draft content should contain subject and body of the email");
    }

    @Order(3)
    @DisplayName("Is mail present in the Sent page")
    @MethodSource(value = "mailSubjects")
    @ParameterizedTest
    public void isMailPresentInTheSentPage(String subject) {
        homePage.openDraftsFolder();
        draftsPage.clickToTheDraftWith(subject);
        modalWindow.clickOnSendMail();
        modalWindow.navigateToSentPageLink();
        boolean isMailPresentInTheSentPage = sentMailsPage.isEmailWithSubjectPresent(subject);
        assertThat(isMailPresentInTheSentPage)
            .as("Mail with a: \"" + subject + "\" should be present in the sent page")
            .isTrue();
    }

    @Order(4)
    @DisplayName("Is mail present in the Inbox page")
    @Test
    public void isMailPresentInTheInboxPage() {
        String subject = "Please help, I'm a bot";
        String allMailsFromInbox = homePage.getAllMailsFromInbox();
        assertThat(allMailsFromInbox)
            .as("Mail with a: " + subject + " subject should be present in the inbox page")
            .contains(subject);
    }

    public static Object[][] mailData() {
        return new Object[][]
            {
                {"mykola_koltutskyi@epam.com", "This is a test mail", "Just playing around"},
                {"nickolayko@yahoo.com", "Super important", "Write me back, buddy. Cheers!"},
                {"seleniumpilot@gmail.com", "Please help, I'm a bot", "It is hard to get out of this server"}
            };
    }

    public static Object[][] mailSubjects () {
        return new Object[][] {
                    {"This is a test mail"},
                    {"Super important"},
                    {"Please help, I'm a bot"}
                };
        }
}