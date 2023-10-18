package tests;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotFocusable;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import pages.ClaimPage;
import pages.Logged;
import pages.LoginPage;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import utils.EspressoBaseTest;

@RunWith(AndroidJUnit4.class)
public class ClaimsTest extends EspressoBaseTest {
    Logged logged = new Logged();
    LoginPage loginPage = new LoginPage();
    ClaimPage claimPage = new ClaimPage();

    String date = getCurrentDate();
    String time = getCurrentTime();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    @Before
    public void setUp() {
        try {
            logged.loggedIn();
        }
        catch (AssertionError e) {
            loginPage.login();
        }

    }

    String text = "Claims";

    @Test
    @DisplayName("9.Minimize and maximize the claims list")
    @Description("Claims minimized and maximized by tapping ARROW button on Claims header")
    public void claimsListMinimize() {
        elementWaiting(withId(R.id.main_menu_image_button), 1000);
        ViewInteraction materialButton = onView(allOf(withId(R.id.expand_material_button),
                childAtPosition(childAtPosition(
                        withId(R.id.container_list_claim_include_on_fragment_main), 0), 3)));
        materialButton.check(matches(isDisplayed()));
        materialButton.perform(click());
        onView(allOf(withId(R.id.claim_list_recycler_view ), isNotFocusable()));
        materialButton.perform(click());
        checkByIdNoWait(R.id.claim_list_recycler_view);
    }

    @Test
    @DisplayName("10.Open the claim from ALL CLAIMS list")
    @Description("The claim detailed info opened after tapping ALL CLAIMS button and the claim triangle")
    public void claimDetailedInfo() {
        elementWaiting(withId(R.id.all_claims_text_view), 1000);

        clickButton(R.id.all_claims_text_view);
/*        ViewInteraction materialTextView = onView(withId(R.id.all_claims_text_view));
        materialTextView.perform(click());*/

/*        ViewInteraction recyclerView = onView(withId(R.id.claim_list_recycler_view));
        recyclerView.perform(actionOnItemAtPosition(0, click()));*/
        clickRecyclerView(R.id.claim_list_recycler_view, 0);

/*        ViewInteraction textView = onView(
                allOf(withId(R.id.title_label_text_view), withText("Title"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class))),
                        isDisplayed()));
        textView.check(matches(isDisplayed()));*/

        checkByIdNoWait(R.id.title_label_text_view);
        checkByIdNoWait(R.id.executor_name_label_text_view);
        checkByIdNoWait(R.id.plane_date_label_text_view);
        checkByIdNoWait(R.id.status_label_text_view);
        checkByIdNoWait(R.id.author_label_text_view);
        checkByIdNoWait(R.id.create_data_text_view);
        checkByIdNoWait(R.id.description_material_card_view);
/*        ViewInteraction textView = onView(withId(R.id.title_label_text_view));
        textView.check(matches(isDisplayed()));*/

/*        ViewInteraction textView2 = onView(withId(R.id.executor_name_label_text_view));
        textView2.check(matches(isDisplayed()));

        ViewInteraction textView3 = onView(withId(R.id.plane_date_label_text_view));
        textView3.check(matches(isDisplayed()));

        ViewInteraction textView4 = onView(withId(R.id.status_label_text_view));
        textView4.check(matches(isDisplayed()));

        ViewInteraction textView5 = onView(withId(R.id.author_label_text_view));
        textView5.check(matches(isDisplayed()));

        ViewInteraction textView6 = onView(withId(R.id.create_data_label_text_view));
        textView6.check(matches(isDisplayed()));

        ViewInteraction cardView = onView(withId(R.id.description_material_card_view));
        cardView.check(matches(isDisplayed()));*/
    }

/*    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }*/

    @Test
    @DisplayName("11.Создание заявки на главном экране")
    @Description("При нажатии на кнопку добавления заявки на главном экране и сохранении информации, заявка создается")
    public void shouldCreateClaimOnMainScreen() {
        String newTitle = "newTitle";
        claimPage.createClaim(newTitle, date, time, "Description");
        elementWaiting(withId(R.id.claim_list_recycler_view), 5000);
        findRecyclerView(R.id.claim_list_recycler_view, newTitle);

    }

    @Test
    @DisplayName("12.Невозможность ввода темы длиннее максимальной на странице \"Заявки\"")
    @Description("Приложение ограничивает тему 50 символами")
    public void shouldNotCreateTooLongClaimTitle() {
        String tooLongTitle = "Этот заголовок длинее 50 символов должен быть обрезан";
        String subTitle = tooLongTitle.substring(0, 50);
        claimPage.createClaim(subTitle, date, time, "Description");
        elementWaiting(withId(R.id.claim_list_recycler_view), 5000);
        findRecyclerView(R.id.claim_list_recycler_view, subTitle);
    }

    @Test
    @DisplayName("13.Создание заявки с пустой датой на странице \"Заявки\"")
    @Description("Появится сообщение о том, что необходимо заполнить пустые поля, и значок ошибки в поле Дата")
    public void shouldNotCreateClaimWithEmptyDate() {
        String emptyDate = "";
        claimPage.createClaim("Новая заявка", emptyDate, time, "Description");
        onView(withText(R.string.empty_fields)).check(matches(withText("Fill empty fields")));
    }

    @Test
    @DisplayName("19.Добавление комментария к заявке")
    @Description("Появится введенный комментарий внизу карточки заявки с указанием имени, под которым был вход в приложение, текущей даты и времени добавления коментария")
    public void addComment() {
        checkMenuButton(text);
        claimPage.fullInfoAboutClaims();
        claimPage.addComment();
    }
}
