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
import ru.iteco.fmhandroid.ui.pages.AboutPage;
import ru.iteco.fmhandroid.ui.pages.Logged;
import ru.iteco.fmhandroid.ui.pages.LoginPage;

@RunWith(AllureAndroidJUnit4.class)
public class AboutTest extends AboutPage {
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
        tapHamburgerAbout();
    }

    @Test
    @DisplayName("53.Отображение версии")
    @Description("При выборе опции About в главном меню отобразится номер версии приложения")
    public void displayVersion() {
        version();
    }

    @Test
    @DisplayName("54.Ссылка на политику конфиденциальности")
    @Description("Откроется веб-страница с политикой конфиденциальности")
    public void openPageWithPrivacyPolicy() {
        privacyPolicy();
    }

    @Test
    @DisplayName("55.Ссылка на пользовательское соглашение")
    @Description("Откроется веб-страница с пользовательским соглашением")
    public void openPageWithTermsOfUse() {
        termsOfUse();
    }

    @Test
    @DisplayName("56.Отображение держателя авторских прав на приложение")
    @Description("При выборе опции About в главном меню отобразится наименование держателя авторских прав на приложение")
    public void displayCopyright() {
        copyright();
    }
}
