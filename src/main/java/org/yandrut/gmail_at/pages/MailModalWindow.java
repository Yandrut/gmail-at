package org.yandrut.gmail_at.pages;

import static org.yandrut.gmail_at.drivers.DriverWaiter.waitForJSComplete;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MailModalWindow extends AbstractPage {

    @FindBy(xpath = "//div[@aria-multiselectable='true']/div/input[@role='combobox']")
    private WebElement addressInput;

    @FindBy(xpath = "//input[@spellcheck='true']")
    private WebElement subjectInput;

    @FindBy(css = "[g_editable='true']")
    private WebElement emailBodyInput;

    @FindBy(xpath = "//*[contains(@data-tooltip, 'Shift')]/following-sibling::img")
    private WebElement quitEditing;

    @FindBy(xpath = "//*[contains(@aria-label, 'Enter')]")
    private WebElement submitMail;

    @FindBy(xpath = "//img[contains(@aria-label, 'Shift')]/../..//span")
    private WebElement subjectLabel;

    @FindBy(xpath = "//*[contains(@*, '#sent')]")
    private WebElement sentPageLink;

    public MailModalWindow(WebDriver driver) {
        super(driver);
    }

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
        waitForJSComplete();
        click(sentPageLink, "Sent mails page link");
        waitForJSComplete();
    }

    public String getDraftEmailInfo() {
        return getText(subjectLabel) + "; " + getText(emailBodyInput);
    }
}