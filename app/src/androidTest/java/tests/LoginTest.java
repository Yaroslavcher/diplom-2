package tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static org.hamcrest.Matchers.allOf;

import android.view.View;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import data.DataHelper;
import page.LoginPage;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

@RunWith(AndroidJUnit4.class)
public class LoginTest {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule = new ActivityScenarioRule<>(AppActivity.class);

    @Test
    public void validLoginTest(DataHelper login, DataHelper password) {
        LoginPage.inputText(LoginPage.getLoginInputId(), String.valueOf(login));
        LoginPage.inputText(LoginPage.getPasswordInputId(), String.valueOf(password));
        LoginPage.clickButton(LoginPage.getLoginButtonId());

        ViewInteraction imageButton = onView(
                allOf(withId(R.id.authorization_image_button), withContentDescription("Authorization"),
                        withParent(allOf(withId(R.id.container_custom_app_bar_include_on_fragment_main),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class)))),
                        isDisplayed()));
        imageButton.check(matches(isDisplayed()));
    }
}
