package org.yandrut.gmail_at.pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;

public class MailModalWindow extends AbstractPage {

    private static final SelenideElement addressInput = $("input[autocapitalize='off'][role='combobox']");
    private static final SelenideElement subjectInput = $("[spellcheck='true']");
    private static final SelenideElement emailBodyInput = $("div[role='textbox']");
    private static final SelenideElement quitEditing = $("td:has([data-tooltip-delay='800']) img:nth-of-type(3)");
    private static final SelenideElement submitMail = $x("//*[contains(@aria-label, 'Enter')]");
    private static final SelenideElement subjectLabel = $("div[style='position: absolute;'] h2 span");
    private static final SelenideElement sentPageLink = $x("//*[contains(@*, '#sent')]");

    public void createNewEmail(String address, String subject, String body) {
        sendKeys(addressInput, address);
        sendKeys(subjectInput, subject);
        sendKeysUsingActions(emailBodyInput, body);
        elementShouldHaveText(subjectLabel, subject);
        clickUsingActions(quitEditing);
        waitForElementNotVisible(quitEditing);
    }

    public void clickOnSendMail() {
        click(submitMail, "Submit mail in the modal window");
    }

    public void navigateToSentPageLink() {
        click(sentPageLink, "Sent mails page link");
    }

    public String getDraftEmailInfo() {
        return getText(subjectLabel) + "; " + getText(emailBodyInput);
    }
}