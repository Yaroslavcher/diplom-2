package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.utils.EspressoBaseTest;

public class AboutPage extends EspressoBaseTest {
    public void version() {
        checkByIdNoWait(R.id.about_version_value_text_view);
    }

    public void copyright() {
        checkByIdNoWait(R.id.about_company_info_label_text_view);
    }

    public void privacyPolicy() {
        ViewInteraction materialTextView = onView(withId(R.id.about_privacy_policy_label_text_view));
        materialTextView.check(matches(isDisplayed()));
        materialTextView.perform(click());
        materialTextView.check(matches(withText("Privacy policy")));
    }

    public void termsOfUse() {
        ViewInteraction materialTextView = onView(withId(R.id.about_terms_of_use_label_text_view));
        materialTextView.check(matches(isDisplayed()));
        materialTextView.perform(click());
        materialTextView.check(matches(withText("Terms of use")));
    }
}
