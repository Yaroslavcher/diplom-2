package pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static utils.EspressoHelper.elementWaiting;
import static utils.EspressoHelper.waitForElement;

import androidx.test.espresso.ViewInteraction;

import utils.EspressoBaseTest;
import ru.iteco.fmhandroid.R;

public class LoginPage extends EspressoBaseTest {


/*    ViewInteraction loginField = onView(withHint("Login"));
    ViewInteraction passwordField = onView(withHint("Password"));
    public static int getLoginInputId() {
        return R.id.login_text_input_layout;
    }
    public static int getPasswordInputId() {
        return R.id.password_text_input_layout;
    }
    public static int getLoginButtonId() {
        return R.id.enter_button;
    }
    public ViewInteraction getLoginField() {
        return loginField;
    }

    public ViewInteraction getPasswordField() {
        return passwordField;
    }*/

    ViewInteraction loginField = onView(withHint("Login"));
    ViewInteraction passwordField = onView(withHint("Password"));
    public static int getLoginInputId() {
        return R.id.login_text_input_layout;
    }
    public ViewInteraction getLoginField() {
        return loginField;
    }
    public static int getPasswordInputId() {
        return R.id.password_text_input_layout;
    }
    public ViewInteraction getPasswordField() {
        return passwordField;
    }
    public static int getLoginButtonId() {
        return R.id.enter_button;
    }
    public void login() {
        LoginPage loginPage = new LoginPage();
        loginPage.getLoginField().perform(typeText("login2"), closeSoftKeyboard());
        LoginPage loginPage2 = new LoginPage();
        loginPage2.getPasswordField().perform(typeText("password2"), closeSoftKeyboard());
        clickButton(getLoginButtonId());
    }

    public void invalidLogin() {
        LoginPage loginPage = new LoginPage();
        loginPage.getLoginField().perform(typeText("inv"), closeSoftKeyboard());
        LoginPage loginPage2 = new LoginPage();
        loginPage2.getPasswordField().perform(typeText("password2"), closeSoftKeyboard());
        clickButton(getLoginButtonId());
    }

    public void invalidPassword() {
        LoginPage loginPage = new LoginPage();
        loginPage.getLoginField().perform(typeText("login2"), closeSoftKeyboard());
        LoginPage loginPage2 = new LoginPage();
        loginPage2.getPasswordField().perform(typeText("inv"), closeSoftKeyboard());
        clickButton(getLoginButtonId());
    }

    public void emptyLogin() {
        LoginPage loginPage = new LoginPage();
        loginPage.getPasswordField().perform(typeText("password2"), closeSoftKeyboard());
        clickButton(getLoginButtonId());
    }

    public void emptyPassword() {
        LoginPage loginPage = new LoginPage();
        loginPage.getLoginField().perform(typeText("login2"), closeSoftKeyboard());
        clickButton(getLoginButtonId());
    }

    public void logout() {
        LoginPage loginPage = new LoginPage();
        elementWaiting(withId(R.id.authorization_image_button), 2000);
        clickButton(R.id.authorization_image_button);
        onView(isRoot()).perform(waitForElement(withText("Log out"), 1000));
        onView(withText("Log out")).perform(click());
        elementWaiting(withId(R.id.enter_button), 3000);
        checkById(R.id.enter_button);
    }
}
