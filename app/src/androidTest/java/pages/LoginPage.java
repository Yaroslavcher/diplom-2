package pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.EspressoBaseTest;
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
}
