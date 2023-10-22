package pages;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotEquals;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;
import utils.EspressoBaseTest;
import utils.EspressoHelper;

public class NewsPage extends EspressoBaseTest {
    public void checkEdit() {
        checkById(R.id.edit_news_material_button);
        clickButton(R.id.edit_news_material_button);
        checkById(R.id.add_news_image_view);
    }
    public void createNews() {
        String date = getCurrentDate();
        String time = getCurrentTime();
        String category = "Объявление";
        String title = "yaroslavcher";
        String description = "test";
        clickButton(R.id.add_news_image_view);
        clickDropDownCategory();
        inputTitle(title);
        inputCurrentDate();
        inputCurrentTime();
        inputText(R.id.news_item_description_text_input_edit_text, description);
        scrollAndClickButton(R.id.save_button);
        recyclerNews(title);
    }
    public void clickDropDownCategory() {
        ViewInteraction checkableImageButton = onView(
                allOf(withId(com.google.android.material.R.id.text_input_end_icon), withContentDescription("Show dropdown menu"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                0),
                        isDisplayed()));
        checkableImageButton.perform(click());
    }
    public void inputTitle(String title) {
        ViewInteraction textInputEditText = onView(withId(R.id.news_item_title_text_input_edit_text));
        textInputEditText.perform(click());
        textInputEditText.perform(replaceText(title));
    }
    public void inputCurrentDate() {
        DataInteraction materialTextView2 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(0);
        materialTextView2.perform(click());

        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.news_item_publish_date_text_input_edit_text),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.news_item_create_date_text_input_layout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText3.perform(click());

        ViewInteraction materialButton4 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton4.perform(scrollTo(), click());
    }
    public void inputCurrentTime() {
        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.news_item_publish_time_text_input_edit_text),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.news_item_publish_time_text_input_layout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText4.perform(click());

        ViewInteraction materialButton5 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton5.perform(scrollTo(), click());
    }
    public void recyclerNews(String title) {
        elementWaiting(withId(R.id.news_list_recycler_view), 5000);
        checkById(R.id.news_list_recycler_view);
        onView(withId(R.id.news_list_recycler_view))
                .check(matches(hasDescendant(allOf(
                        withId(R.id.news_item_title_text_view),
                        withText(title)))));
    }

    public void warnDeleteNews() {
        elementWaiting(withId(R.id.news_list_recycler_view), 8000);
        onView(withId(R.id.news_list_recycler_view))
                .perform(actionOnItemAtPosition(0, clickChildViewWithId(R.id.delete_news_item_image_view)));
        onView(withText(R.string.irrevocable_deletion))
                .check(matches(withText("Are you sure you want to permanently delete the document? These changes cannot be reversed in the future.")));
    }
    public void deleteNews() {
        int countBeforeDelete = EspressoHelper.getRecyclerViewItemCount();
        warnDeleteNews();
        scrollAndClickButton(R.string.fragment_positive_button);
        elementWaiting(withId(R.id.news_list_recycler_view), 4000);
        int countAfterDelete = EspressoHelper.getRecyclerViewItemCount();
        assertNotEquals(countBeforeDelete, countAfterDelete);
    }
    public void notCreateNews() {
        int countBeforeCancel = EspressoHelper.getRecyclerViewItemCount();
        scrollAndClickButton(R.id.cancel_button);
        onView(withText(R.string.cancellation))
                .check(matches(withText("The changes won't be saved, do you really want to log out?")));
        onView(withText("OK")).perform(click());
        elementWaiting(withId(R.id.news_list_recycler_view), 4000);
        int countAfterCancel = getRecyclerViewItemCount();
        assertEquals(countBeforeCancel, countAfterCancel);
    }
}
