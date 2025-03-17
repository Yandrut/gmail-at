package org.yandrut.gmail_at.pages;

import static org.yandrut.gmail_at.drivers.DriverWaiter.waitForJSComplete;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy
@Component
public class MailModalWindow extends AbstractPage {

    @FindBy(xpath = "//div[@role='presentation']/input[1]")
    private WebElement addressInput;

    @FindBy(xpath = "//input[@spellcheck='true']")
    private WebElement subjectInput;

    @FindBy(xpath = "//table[@cellpadding='0']/tbody/tr/td/div/h2/div/following::div/span")
    private WebElement subjectLabel;

    @FindBy(css = "[g_editable='true']")
    private WebElement emailBodyInput;

    @FindBy(xpath = "//td/img[3]")
    private WebElement quitEditing;

    @FindBy(xpath = "//div[contains(@aria-label, 'Enter')]")
    private WebElement submitMail;

    @FindBy(xpath = "//*[contains(@href, '#sent')]")
    private WebElement sentPageLink;

    public void createNewEmail(String address, String subject, String body) {
        sendKeys(addressInput, address);
        sendKeys(subjectInput, subject);
        sendKeys(emailBodyInput, body);
        click(quitEditing, "Exit editing");
    }

    public void clickOnSendMail() {
        waitForJSComplete();
        click(submitMail, "Submit mail in the modal window");
    }

    public void navigateToSentPageLink() {
        click(sentPageLink, "Sent mails page link");
    }

    public String getDraftEmailInfo() {
        return getText(subjectLabel) + "; " + getText(emailBodyInput);
    }
}