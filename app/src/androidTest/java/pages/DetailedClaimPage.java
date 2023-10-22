package pages;

import static androidx.test.espresso.matcher.ViewMatchers.withId;

import ru.iteco.fmhandroid.R;
import utils.EspressoBaseTest;

public class DetailedClaimPage extends EspressoBaseTest {

    public void detailedClaim(){
        elementWaiting(withId(R.id.claim_list_recycler_view), 10000);
        clickRecyclerView(R.id.claim_list_recycler_view, 3);
        /*        ViewInteraction recyclerView = onView(allOf(withId(R.id.claim_list_recycler_view)));
        recyclerView.check(matches(isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(3, click()));*/

        checkById(R.id.status_label_text_view);
/*        ViewInteraction textView = onView(allOf(withId(R.id.status_label_text_view)));
        elementWaiting(withId(R.id.status_label_text_view), 10000);
        textView.check(matches(isDisplayed()));*/
    }
}
