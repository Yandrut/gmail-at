package org.yandrut.gmail_at.pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.yandrut.gmail_at.model.User;

public class HomePage extends AbstractPage {

    private static final SelenideElement emailField = $("#identifierId");
    private static final SelenideElement submitEmail = $x("//div[@id='identifierNext']//button");
    private static final SelenideElement passwordField = $("[type='password']");
    private static final SelenideElement submitPassword = $x("//div[@id='passwordNext']//button");
    private static final SelenideElement gmailLogo = $x("//a[@title='Gmail']/img");
    private static final SelenideElement writeNewMail = $x("//div[@role='navigation']//div[@role='button']");
    private static final SelenideElement draftFolderLink = $x("//*[contains(@href, '#draft')]");
    private static final ElementsCollection inboxMail =
        $$(By.xpath("//span[@class='bog']/span"));

    private static final Logger log = LogManager.getLogger(HomePage.class);

    public void loginToMailServiceAs(User user) {
        sendKeys(emailField, user.getEmail());
        click(submitEmail, "Submit email button");
        sendKeysUsingActions(passwordField, user.getPassword());
        click(submitPassword, "Submit password button");
    }

    public boolean isUserLoggedIn() {
        return isElementPresent(gmailLogo, "Gmail logo");
    }

    public void clickOnWriteNewMail() {
        writeNewMail.click();
    }

    public void openDraftsFolder() {
        click(draftFolderLink, "Draft folder link");
    }

    public boolean isEmailWithASubjectPresent(String subject) {
        return Arrays.stream(getAllMailsFromInbox()
                         .split(";"))
                     .anyMatch(s -> s.contains(subject));
    }

    private String getAllMailsFromInbox() {
        String visibleMails = getTextOfAllElements(inboxMail);
        log.debug("Mails found on the page: {}", visibleMails);
        return visibleMails;
    }
}