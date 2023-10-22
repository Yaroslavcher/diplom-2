package pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;
import utils.EspressoBaseTest;

public class ClaimPage extends EspressoBaseTest {

    public void clickCreateClaim() {
//        elementWaiting(withId(R.id.add_new_claim_material_button), 10000);
//        ViewInteraction materialButton = onView(allOf(withId(R.id.add_new_claim_material_button)));
//        materialButton.check(matches(isDisplayed()));
//        materialButton.perform(click());
        checkById(R.id.add_new_claim_material_button);
        clickButton(R.id.add_new_claim_material_button);
    }

    public void inputDate(String date) {
/*      ViewInteraction textInputEditText = onView(allOf(withId(R.id.date_in_plan_text_input_edit_text)));
        textInputEditText.check(matches(isDisplayed()));
        textInputEditText.perform(replaceText(date));*/

    }
    public void createClaim(String title, String date, String time, String description) {
        //String title = "Новая заявка";
        elementWaiting(withId(R.id.main_menu_image_button), 5000);
        clickCreateClaim();
        elementWaiting(withId(R.id.title_edit_text), 10000);
        inputText(R.id.title_edit_text, title);
        inputText(R.id.date_in_plan_text_input_edit_text, date);
        inputText(R.id.time_in_plan_text_input_edit_text, time);
        inputText(R.id.description_edit_text, description);
        scrollAndClickButton(R.id.save_button);

    }

    public void fullInfoAboutClaims(){
        elementWaiting(withId(R.id.claim_list_recycler_view), 10000);
        clickRecyclerView(R.id.claim_list_recycler_view, 3);
        checkById(R.id.status_label_text_view);
    }
    public void fullInfoAboutClaim() {
        elementWaiting(withId(R.id.claim_list_recycler_view), 3000);
        clickRecyclerView(R.id.claim_list_recycler_view, 0);
        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.add_comment_image_button), withContentDescription("button add comment"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.comments_material_card_view),
                                        0),
                                2),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction textInputEditText3 = onView(
                allOf(childAtPosition(
                                childAtPosition(
                                        withId(R.id.comment_text_input_layout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText3.perform(replaceText("add"), closeSoftKeyboard());
        scrollAndClickButton(R.id.save_button);
        checkText(R.id.comment_description_text_view, R.id.claim_comments_list_recycler_view, "add");
    }




    public void clickAddCommentInputText(String text) {
        elementWaiting(withId(R.id.claim_comments_list_recycler_view), 8000);
        clickButton(R.id.add_comment_image_button);
        ViewInteraction textInputEditText = onView(allOf(childAtPosition(childAtPosition(
                withId(R.id.comment_text_input_layout), 0), 1)));
        textInputEditText.perform(replaceText(text), closeSoftKeyboard());
        scrollAndClickButton(R.id.save_button);
    }

/*    public void addCommentWithoutText() {
        String text = " ";
        clickAddCommentInputText(text);
        clickSaveButton();
        onView(withText(R.string.toast_empty_field)).inRoot(new EspressoHelper.ToastMatcher())
                .check(matches(withText("The field cannot be empty.")));*/


    public void addComment() {
        String text = "new comment";
        clickAddCommentInputText(text);
        scrollAndClickButton(R.id.save_button);
        elementWaiting(withId(R.id.claim_comments_list_recycler_view), 8000);
        onView(withId(R.id.claim_comments_list_recycler_view))
                .check(matches(hasDescendant(allOf(
                        withId(R.id.comment_description_text_view),
                        withText(text)))));
    }

    public void editComment() {
        String text = "edit";

    }

}
