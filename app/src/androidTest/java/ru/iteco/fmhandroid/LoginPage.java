package ru.iteco.fmhandroid;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;

import androidx.test.espresso.ViewInteraction;

public class LoginPage extends EspressoBaseTest {


    ViewInteraction loginField = onView(withHint("Login"));
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
    }
}
