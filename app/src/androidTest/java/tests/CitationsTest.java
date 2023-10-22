package tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import pages.CitationPage;
import pages.Logged;
import pages.LoginPage;
import ru.iteco.fmhandroid.ui.AppActivity;

@RunWith(AndroidJUnit4.class)
public class CitationsTest extends CitationPage {
    Logged logged = new Logged();
    LoginPage loginPage = new LoginPage();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    @Before
    public void setUp() {
        try {
            logged.loggedIn();
        }
        catch (AssertionError e) {
            loginPage.login();
        }
    }
    @Test
    @DisplayName("51.Разворачивание карточки цитаты")
    @Description("Раскроется карточка с дополнительным текстом цитаты")
    public void openFullCitation() {
        openCitations();
        fullCitations();
    }
}
