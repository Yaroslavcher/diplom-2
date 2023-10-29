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
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
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
public class AppActivityTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Test
    public void appActivityTest() {
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
        
        ViewInteraction recyclerView = onView(
allOf(withId(R.id.claim_list_recycler_view),
childAtPosition(
withId(R.id.all_claims_cards_block_constraint_layout),
4)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));
        
        ViewInteraction appCompatImageButton = onView(
allOf(withId(R.id.add_comment_image_button), withContentDescription("button add comment"),
childAtPosition(
childAtPosition(
withId(R.id.comments_material_card_view),
0),
2),
isDisplayed()));
        appCompatImageButton.perform(click());
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
