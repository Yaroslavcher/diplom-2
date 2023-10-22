package pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;
import utils.EspressoBaseTest;

public class CitationPage extends EspressoBaseTest {
 public void openCitations() {
     checkById(R.id.our_mission_image_button);
     clickButton(R.id.our_mission_image_button);
     checkById(R.id.our_mission_title_text_view);
 }
 public void fullCitations() {
     ViewInteraction recyclerView = onView(
             allOf(withId(R.id.our_mission_item_list_recycler_view),
                     childAtPosition(
                             withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                             0)));
     recyclerView.perform(actionOnItemAtPosition(0, click()));

     checkById(R.id.our_mission_item_description_text_view);
 }
}
