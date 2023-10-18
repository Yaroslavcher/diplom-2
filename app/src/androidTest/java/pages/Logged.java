package pages;

import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static utils.EspressoHelper.elementWaiting;

import utils.EspressoBaseTest;
import ru.iteco.fmhandroid.R;

public class Logged extends EspressoBaseTest {
    public void loggedIn() {
        elementWaiting(withId(R.id.main_menu_image_button), 7000);
/*        ViewInteraction appCompatImageButton = onView(allOf(withId(R.id.main_menu_image_button),
                withContentDescription("Main menu")));
        appCompatImageButton.check(matches(isDisplayed()));*/
        checkById(R.id.main_menu_image_button);

    }

    public void loggedOut() {
        elementWaiting(withId(R.id.enter_button), 5000);
        checkById(R.id.enter_button);
    }
}
