package utils;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

public class EspressoBaseTest extends EspressoHelper {
    public static void inputText(Integer resourceId, String inputText) {
        Allure.step("Ввод строки в поле с id: " + resourceId);

        ViewInteraction textInputEditText = onView(withId(resourceId));
        textInputEditText.check(matches(isDisplayed()));
        textInputEditText.perform(replaceText(inputText), closeSoftKeyboard());
    }
    public static void clickButton(Integer resourceId) {
        Allure.step("Тап по кнопке с id: " + resourceId);
        //onView(withId(resourceId)).perform(click()).check(matches(isDisplayed()));
        onView(withId(resourceId)).perform(click());
    }

    public static void clickRecyclerView(Integer resourceId, int position) {
        Allure.step("Тап на позицию: " + position + " в списке с id: " + resourceId);
        //onView(withId(resourceId)).perform(click()).check(matches(isDisplayed()));
        ViewInteraction recyclerView = onView(withId(resourceId));
        recyclerView.check(matches(isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(position, click()));
    }

    public static void findRecyclerView(Integer resourceId, String title) {
        Allure.step("Поиск " + title + " в списке с id: " + resourceId);
        elementWaiting(withId(resourceId), 8000);
        ViewInteraction recyclerView = onView(allOf(withId(resourceId)));
        recyclerView.check(matches(isDisplayed()));
        onView(withId(resourceId))
                .check(matches(not(hasDescendant(allOf(
                        withId(R.id.title_text_view),
                        withText(title),
                        isDisplayed()
                )))));
    }
    public void scrollAndClickButton(Integer resourceId) {
        Allure.step("Скролл и тап по кнопке с id: " + resourceId);
        onView((withId(resourceId))).perform(scrollTo(), click());
    }

    public void checkById(Integer resourceId) {
        Allure.step("Проверка того, что отображен элемент с id: " + resourceId);
        elementWaiting(withId(resourceId), 5000);
        onView(withId(resourceId)).check(matches(isDisplayed()));
    }

    public void checkByIdNoWait(Integer resourceId) {
        Allure.step("Проверка того, что отображен элемент с id: " + resourceId);
        onView(withId(resourceId)).check(matches(isDisplayed()));
    }

    public void checkByString(int resourceString, String text) {
        Allure.step("Проверка того, что отображен элемент с текстом: " + text);
        onView(withText(resourceString)).inRoot(new EspressoHelper.ToastMatcher())
                .check(matches(isDisplayed()));
        onView(withText(resourceString)).inRoot(new EspressoHelper.ToastMatcher())
                .check(matches(withText(text)));
    }

    public void checkMenuButton(String text) {
        elementWaiting(withId(R.id.main_menu_image_button), 8000);
        ViewInteraction appCompatImageButton = onView(allOf(withId(R.id.main_menu_image_button),
                withContentDescription("Main menu")));
        appCompatImageButton.check(matches(isDisplayed()));
        appCompatImageButton.perform(click());
        ViewInteraction materialTextView = onView(allOf(withId(android.R.id.title), withText(text)));
        materialTextView.check(matches(isDisplayed()));
        materialTextView.perform(click());
        ViewInteraction textView = onView(allOf(withText(text)));
        textView.check(matches(isDisplayed()));
        textView.check(matches(withText(text)));
    }


    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);




}
