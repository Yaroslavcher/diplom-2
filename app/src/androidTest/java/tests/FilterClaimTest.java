package tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import pages.ClaimPage;
import pages.FilterClaimPage;
import pages.Logged;
import pages.LoginPage;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import utils.EspressoBaseTest;

public class FilterClaimTest extends EspressoBaseTest {
    Logged logged = new Logged();
    LoginPage loginPage = new LoginPage();
    ClaimPage claimPage = new ClaimPage();
    //DetailedClaimPage detailedClaimPage = new DetailedClaimPage();
    FilterClaimPage filterClaimPage = new FilterClaimPage();

/*    String date = getCurrentDate();
    String time = getCurrentTime();*/

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

    String menu_item = "Claims";
    Integer inProgress = R.id.item_filter_in_progress;
    Integer open = R.id.item_filter_open;
    @Test
    @DisplayName("22.Изменение статуса с Open на In progress")
    @Description("Статус изменится на In progress")
    public void changeOpenToInProgressStatus() {
        String text = "Open";
        String oldStatus = "take to work";
        String newStatus = "In progress";
        tapHamburger(menu_item);

        filterClaimPage.filterScreen(inProgress);
        filterClaimPage.checkStatusClaimRecycler(text);
        filterClaimPage.changeOpenStatus(oldStatus, newStatus);
    }
    @Test
    @DisplayName("23.Изменение статуса с Open на Canceled")
    @Description("Статус изменится на Canceled")
    public void changeOpenToCanceledStatus() {
        String text = "Open";
        String oldStatus = "Cancel";
        String newStatus = "Canceled";
        tapHamburger(menu_item);
        filterClaimPage.filterScreen(inProgress);
        filterClaimPage.checkStatusClaimRecycler(text);
        filterClaimPage.changeOpenStatus(oldStatus, newStatus);
    }
    @Test
    @DisplayName("24.Изменение статуса с In progress на Open")
    @Description("Статус изменится на Open")
    public void changeInProgressToOpenStatus() {

        String text = "In progress";
        String oldStatus = "Throw off";
        String newStatus = "Open";
        tapHamburger(menu_item);
        filterClaimPage.filterScreen(open);
        //filterClaimPage.checkStatusClaimRecycler(text);
        filterClaimPage.changeInProgressStatus(oldStatus, newStatus);
    }

    @Test
    @DisplayName("25.Изменение статуса с In progress на Executed")
    @Description("Статус изменится на Executed")
    public void changeInProgressToExecutedStatus() {

        String text = "In progress";
        String oldStatus = "To execute";
        String newStatus = "Executed";
        tapHamburger(menu_item);
        filterClaimPage.filterScreen(open);
        //filterClaimPage.checkStatusClaimRecycler(text);
        filterClaimPage.changeInProgressStatus(oldStatus, newStatus);
    }


}
