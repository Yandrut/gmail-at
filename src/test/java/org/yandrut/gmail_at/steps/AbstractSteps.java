package org.yandrut.gmail_at.steps;

import org.yandrut.gmail_at.pages.DraftsPage;
import org.yandrut.gmail_at.pages.HomePage;
import org.yandrut.gmail_at.pages.MailModalWindow;
import org.yandrut.gmail_at.pages.SentMailsPage;
import org.yandrut.gmail_at.utils.DataReader;

public abstract class AbstractSteps {

    protected static final String BASE_URL = DataReader.getData("base.url");
    protected static final String PASSWORD = System.getenv("AT_GOOGLE_PASSWORD");
    protected HomePage homePage = new HomePage();
    protected MailModalWindow modalWindow = new MailModalWindow();
    protected DraftsPage draftsPage = new DraftsPage();
    protected SentMailsPage sentMailsPage = new SentMailsPage();
}
