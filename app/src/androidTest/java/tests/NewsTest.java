package tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import pages.ClaimPage;
import pages.DetailedClaimPage;
import pages.Logged;
import pages.LoginPage;
import ru.iteco.fmhandroid.ui.AppActivity;
import utils.EspressoBaseTest;

@RunWith(AndroidJUnit4.class)
public class NewsTest extends EspressoBaseTest {
    Logged logged = new Logged();
    LoginPage loginPage = new LoginPage();
    ClaimPage claimPage = new ClaimPage();
    DetailedClaimPage detailedClaimPage = new DetailedClaimPage();

    String date = getCurrentDate();
    String time = getCurrentTime();

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

    String menu_item = "News";

    @Test
    @DisplayName("45.Создание новости")
    @Description("Откроется карточка \"Объявление\" с датой публикации и датой создания и автором, равными текущей дате и автору соответственно, и описанием \"тест\"")
    public void createNews() {
        tapHamburger(menu_item);

    }

}
