package ru.iteco.fmhandroid.ui.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.pages.Logged;
import ru.iteco.fmhandroid.ui.pages.LoginPage;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

@RunWith(AllureAndroidJUnit4.class)

public class LoginAndLogoutTest extends LoginPage {
    LoginPage loginPage = new LoginPage();
    Logged logged = new Logged();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void checkLogout() {
        try {
            logged.loggedOut();
        } catch (Exception e) {
            loginPage.logout();
            logged.loggedOut();
        }
    }

    @Test
    @DisplayName("2.Valid login and password authorization")
    @Description("User logs in the app after inputting valid login and password")
    public void validLoginAndPassword() {
        login();
        checkById(R.id.authorization_image_button);
    }

    @Test
    @DisplayName("3.Only valid password input")
    @Description("Error message shown after not inputting login")
    public void emptyLoginAndValidPassword() {
        emptyLogin();
        checkByString(R.string.empty_login_or_password, "Login and password cannot be empty");
    }

    @Test
    @DisplayName("4.Only valid login input")
    @Description("Error message shown after not inputting password")
    public void validLoginAndEmptyPassword() {
        emptyPassword();
        checkByString(R.string.empty_login_or_password, "Login and password cannot be empty");
    }

    @Test
    @DisplayName("5.Invalid password authorization")
    @Description("Error message shown after inputting invalid password")
    public void validLoginAndInvalidPassword() {
        invalidPassword();
        checkByString(R.string.wrong_login_or_password, "Wrong login or password");
    }

    @Test
    @DisplayName("6.Invalid login authorization")
    @Description("Error message shown after inputting invalid login")
    public void invalidLoginAndValidPassword() {
        invalidLogin();
        checkByString(R.string.wrong_login_or_password, "Wrong login or password");
    }

    @Test
    @DisplayName("7.Logout of app")
    @Description("Authorization screen opens after tapping Logout button")
    public void shouldLogout() {
        login();
        logout();
    }
}
