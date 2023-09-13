package ru.iteco.fmhandroid;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;

public class EspressoBaseTest {
    public static void inputText(Integer resourceId, String inputText) {
        Allure.step("Ввод строки в поле с id: " + resourceId);
        ViewInteraction textInputEditText = onView(withId(resourceId));
        textInputEditText.check(matches(isDisplayed()));
        textInputEditText.perform(replaceText(inputText), closeSoftKeyboard());
    }
    public static void clickButton(Integer resourceId) {
        Allure.step("Тап по кнопке с id: " + resourceId);
        onView(withId(resourceId)).perform(click()).check(matches(isDisplayed()));
    }

    public void scrollAndClickButton(Integer resourceId) {
        Allure.step("Скролл и тап по кнопке с id: " + resourceId);
        onView((withId(resourceId))).perform(scrollTo(), click());
    }

    public void checkById(Integer resourceId) {
        Allure.step("Проверка того, что отображен элемент с id: " + resourceId);
        onView(withId(resourceId)).check(matches(isDisplayed()));
    }
}
