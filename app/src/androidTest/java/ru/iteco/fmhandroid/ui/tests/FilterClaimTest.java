package ru.iteco.fmhandroid.ui.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.pages.FilterClaimPage;
import ru.iteco.fmhandroid.ui.pages.Logged;
import ru.iteco.fmhandroid.ui.pages.LoginPage;
import ru.iteco.fmhandroid.ui.utils.EspressoBaseTest;

@RunWith(AllureAndroidJUnit4.class)
public class FilterClaimTest extends EspressoBaseTest {
    Logged logged = new Logged();
    LoginPage loginPage = new LoginPage();
    FilterClaimPage filterClaimPage = new FilterClaimPage();

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
        tapHamburger("Claims");
    }

    @Test
    @DisplayName("22.Изменение статуса с Open на In progress")
    @Description("Статус изменится на In progress")
    public void changeOpenToInProgressStatus() {
        String text = "Open";
        String oldStatus = "take to work";
        String newStatus = "In progress";

        filterClaimPage.changeOpen(text, oldStatus, newStatus);
    }
    @Test
    @DisplayName("23.Изменение статуса с Open на Canceled")
    @Description("Статус изменится на Canceled")
    public void changeOpenToCanceledStatus() {
        String text = "Open";
        String oldStatus = "Cancel";
        String newStatus = "Canceled";

        filterClaimPage.changeOpen(text, oldStatus, newStatus);
    }
    @Test
    @DisplayName("24.Изменение статуса с In progress на Open")
    @Description("Статус изменится на Open")
    public void changeInProgressToOpenStatus() {
        String oldStatus = "Throw off";
        String newStatus = "Open";

        filterClaimPage.changeInProgress(oldStatus, newStatus);
    }

    @Test
    @DisplayName("25.Изменение статуса с In progress на Executed")
    @Description("Статус изменится на Executed")
    public void changeInProgressToExecutedStatus() {
        String oldStatus = "To execute";
        String newStatus = "Executed";

        filterClaimPage.changeInProgress(oldStatus, newStatus);
    }
}
