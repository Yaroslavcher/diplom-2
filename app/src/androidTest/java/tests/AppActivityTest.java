package tests;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static org.hamcrest.Matchers.allOf;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.LoginPage;
import ru.iteco.fmhandroid.R;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AppActivityTest extends TestBase {



    @Test
    public void appActivityTest() {
        onView(isRoot()).perform(waitDisplayed(R.id.login_text_input_layout, 5000));
        //ViewInteraction textInputEditText = onView(withId(R.id.login_text_input_layout));

 //       ViewInteraction loginField = onView(withHint("Login"));

        LoginPage loginPage = new LoginPage();
        loginPage.getLoginField().perform(typeText("login2"), closeSoftKeyboard());

//        ViewInteraction passwordField = onView(withHint("Password"));

        LoginPage loginPage2 = new LoginPage();
        loginPage2.getPasswordField().perform(typeText("password2"), closeSoftKeyboard());

        //textInputEditText.perform(replaceText("login2"), closeSoftKeyboard());

//        ViewInteraction textInputEditText2 = onView(
//                allOf(withText("login2"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.login_text_input_layout),
//                                        0),
//                                0),
//                        isDisplayed()));
//        textInputEditText2.perform(click());
//
//        ViewInteraction textInputEditText3 = onView(
//                allOf(childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.password_text_input_layout),
//                                        0),
//                                0),
//                        isDisplayed()));
//        textInputEditText3.perform(replaceText("password2"), closeSoftKeyboard());
//
//        ViewInteraction textInputEditText4 = onView(
//                allOf(withText("password2"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.password_text_input_layout),
//                                        0),
//                                0),
//                        isDisplayed()));
//        textInputEditText4.perform(pressImeActionButton());

        LoginPage.clickButton(LoginPage.getLoginButtonId());

//        ViewInteraction materialButton = onView(
//                allOf(withId(R.id.enter_button), withText("Sign in"), withContentDescription("Save"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withClassName(is("android.widget.RelativeLayout")),
//                                        1),
//                                2),
//                        isDisplayed()));
//        materialButton.perform(click());

        onView(isRoot()).perform(waitDisplayed(R.id.authorization_image_button, 5000));
        ViewInteraction imageButton = onView(
                allOf(withId(R.id.authorization_image_button), withContentDescription("Authorization"),
                        withParent(allOf(withId(R.id.container_custom_app_bar_include_on_fragment_main),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class)))),
                        isDisplayed()));
        imageButton.check(matches(isDisplayed()));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}