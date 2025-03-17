package org.yandrut.gmail_at;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

@SpringBootTest
public class MailTest extends BaseTest {

    @Order(1)
    @DisplayName("Successful user login")
    @Test
    public void isUserLoggedIn() {
        boolean isUserLoggedIn = mailServicePage.isUserLoggedIn();
        assertThat(isUserLoggedIn)
            .describedAs("User should be logged in to the mail service")
            .isTrue();
    }

    @Order(2)
    @DisplayName("Allows to create new email as a draft")
    @MethodSource(value = "mailData")
    @ParameterizedTest
    public void allowsToCreateNewMailAsDraft(String address, String subject, String body) {
        mailServicePage.clickOnWriteNewMail();
        modalWindow.createNewEmail(address, subject, body);
        mailServicePage.openDraftsFolder();
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
        mailServicePage.openDraftsFolder();
        assertTrue(draftsPage.isDraftPageOpen());
        draftsPage.clickToTheDraftWith(subject);
        modalWindow.clickOnSendMail();
        modalWindow.navigateToSentPageLink();
        assertTrue(sentMailsPage.isSentPageOpen());

        boolean isMailPresentInTheSentPage = sentMailsPage.isEmailWithSubjectPresent(subject);
        assertThat(isMailPresentInTheSentPage)
            .as("Mail with a: " + subject + " should be present in the sent page")
            .isTrue();
    }

    @Order(4)
    @DisplayName("Is mail present in the Inbox page")
    @Test
    public void isMailPresentInTheInboxPage() {
        String subject = "Please help, Im a bot";
        boolean isMailPresent = mailServicePage.isMailWithSubjectPresent(subject);
        assertThat(isMailPresent)
            .as("Mail with a: " + subject + "subject should be present in the inbox page")
            .isTrue();
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