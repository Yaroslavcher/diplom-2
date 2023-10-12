package pages;

import ru.iteco.fmhandroid.EspressoBaseTest;
import ru.iteco.fmhandroid.R;

public class Logged extends EspressoBaseTest {
    public void loggedIn() {
        waitDisplayed(R.id.main_menu_image_button, 5000);
/*        ViewInteraction appCompatImageButton = onView(allOf(withId(R.id.main_menu_image_button),
                withContentDescription("Main menu")));
        appCompatImageButton.check(matches(isDisplayed()));*/
        checkById(R.id.main_menu_image_button);

    }

    public void loggedOut() {
        waitDisplayed(R.id.enter_button, 5000);
        checkById(R.id.enter_button);
    }
}
