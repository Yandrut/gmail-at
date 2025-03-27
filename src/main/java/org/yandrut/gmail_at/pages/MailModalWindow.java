package org.yandrut.gmail_at.pages;

import static org.yandrut.gmail_at.driver.waits.DriverWaiter.waitForJSComplete;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.yandrut.gmail_at.element.Button;
import org.yandrut.gmail_at.element.InputField;
import org.yandrut.gmail_at.element.Label;

public class MailModalWindow extends AbstractPage {

    @FindBy(css = "input[autocapitalize='off'][role='combobox']")
    private InputField addressInput;

    @FindBy(css = "[spellcheck='true']")
    private InputField subjectInput;

    @FindBy(css = "div[role='textbox']")
    private InputField emailBodyInput;

    @FindBy(css = "td:has([data-tooltip-delay='800']) img:nth-of-type(3)")
    private Button quitEditing;

    @FindBy(xpath = "//*[contains(@aria-label, 'Enter')]")
    private Button submitMail;

    @FindBy(css = "div[style='position: absolute;'] h2 span")
    private Label subjectLabel;

    @FindBy(xpath = "//*[contains(@*, '#sent')]")
    private Button sentPageLink;

    public MailModalWindow(WebDriver driver) {
        super(driver);
    }

    public void createNewEmail(String address, String subject, String body) {
        addressInput.sendKeys(address);
        subjectInput.sendKeys(subject);
        emailBodyInput.sendKeys(body);
        quitEditing.click("Exit editing");
    }

    public void clickOnSendMail() {
        waitForJSComplete();
        submitMail.click("Submit mail in the modal window");
    }

    public void navigateToSentPageLink() {
        waitForJSComplete();
        sentPageLink.click("Sent mails page link");
        waitForJSComplete();
    }

    public String getDraftEmailInfo() {
        return subjectLabel.getText() + "; " + emailBodyInput.getText();
    }
}