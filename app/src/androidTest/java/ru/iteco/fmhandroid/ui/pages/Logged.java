package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.matcher.ViewMatchers.withId;

import ru.iteco.fmhandroid.ui.utils.EspressoBaseTest;
import ru.iteco.fmhandroid.R;

public class Logged extends EspressoBaseTest {
    public void loggedIn() {
        elementWaiting(withId(R.id.main_menu_image_button), 5000);
/*        ViewInteraction appCompatImageButton = onView(allOf(withId(R.id.main_menu_image_button),
                withContentDescription("Main menu")));
        appCompatImageButton.check(matches(isDisplayed()));*/
        checkById(R.id.main_menu_image_button);

    }

    public void loggedOut() {
        elementWaiting(withId(R.id.enter_button), 2500);
        checkById(R.id.enter_button);
    }
}
