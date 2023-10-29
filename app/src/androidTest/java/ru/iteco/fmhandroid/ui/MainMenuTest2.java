package ru.iteco.fmhandroid.ui;


import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static androidx.test.InstrumentationRegistry.getInstrumentation;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import ru.iteco.fmhandroid.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainMenuTest2 {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Test
    public void mainMenuTest2() {
        ViewInteraction textInputEditText = onView(
allOf(childAtPosition(
childAtPosition(
withId(R.id.login_text_input_layout),
0),
0),
isDisplayed()));
        textInputEditText.perform(replaceText("login2"), closeSoftKeyboard());
        
        ViewInteraction textInputEditText2 = onView(
allOf(childAtPosition(
childAtPosition(
withId(R.id.password_text_input_layout),
0),
0),
isDisplayed()));
        textInputEditText2.perform(replaceText("password2"), closeSoftKeyboard());
        
        ViewInteraction materialButton = onView(
allOf(withId(R.id.enter_button), withText("Sign in"), withContentDescription("Save"),
childAtPosition(
childAtPosition(
withClassName(is("android.widget.RelativeLayout")),
1),
2),
isDisplayed()));
        materialButton.perform(click());
        
        ViewInteraction appCompatImageButton = onView(
allOf(withId(R.id.main_menu_image_button), withContentDescription("Main menu"),
childAtPosition(
allOf(withId(R.id.container_custom_app_bar_include_on_fragment_main),
childAtPosition(
withClassName(is("android.widget.LinearLayout")),
0)),
0),
isDisplayed()));
        appCompatImageButton.perform(click());
        
        ViewInteraction materialTextView = onView(
allOf(withId(android.R.id.title), withText("News"),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
0),
isDisplayed()));
        materialTextView.perform(click());
        
        ViewInteraction appCompatImageButton2 = onView(
allOf(withId(R.id.main_menu_image_button), withContentDescription("Main menu"),
childAtPosition(
allOf(withId(R.id.container_custom_app_bar_include_on_fragment_news_list),
childAtPosition(
withClassName(is("android.widget.LinearLayout")),
0)),
0),
isDisplayed()));
        appCompatImageButton2.perform(click());
        
        ViewInteraction textView = onView(
allOf(withId(android.R.id.title), withText("Claims"),
withParent(withParent(withId(android.R.id.content))),
isDisplayed()));
        textView.check(matches(isDisplayed()));
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
                        && view.equals(((ViewGroup)parent).getChildAt(position));
            }
        };
    }
    }
