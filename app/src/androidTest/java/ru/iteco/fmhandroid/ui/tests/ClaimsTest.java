package ru.iteco.fmhandroid.ui.tests;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotFocusable;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.pages.ClaimPage;
import ru.iteco.fmhandroid.ui.pages.DetailedClaimPage;
import ru.iteco.fmhandroid.ui.pages.Logged;
import ru.iteco.fmhandroid.ui.pages.LoginPage;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.utils.EspressoBaseTest;
import ru.iteco.fmhandroid.ui.utils.EspressoHelper;

@RunWith(AllureAndroidJUnit4.class)
public class ClaimsTest extends EspressoBaseTest {
    Logged logged = new Logged();
    LoginPage loginPage = new LoginPage();
    ClaimPage claimPage = new ClaimPage();
    DetailedClaimPage detailedClaimPage = new DetailedClaimPage();

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

    String menu_item = "Claims";

    @Test
    @DisplayName("9.Разворачивание/Сворачивание заявок")
    @Description("Заявка развернется и свернется при клике на СТРЕЛКУ в заголовке Claims")
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
    @DisplayName("10.Открытие заявки из списка ALL CLAIMS")
    @Description("Откроется подробная информация о заявке при клике на кнопку ALL CLAIMS и стрелку заявки")
    public void claimDetailedInfo() {
        elementWaiting(withId(R.id.all_claims_text_view), 1000);

        clickButton(R.id.all_claims_text_view);
        clickRecyclerView(R.id.claim_list_recycler_view, 0);

        checkById(R.id.title_label_text_view);
        checkByIdNoWait(R.id.executor_name_label_text_view);
        checkByIdNoWait(R.id.plane_date_label_text_view);
        checkByIdNoWait(R.id.status_label_text_view);
        checkByIdNoWait(R.id.author_label_text_view);
        checkByIdNoWait(R.id.create_data_text_view);
        checkByIdNoWait(R.id.description_material_card_view);
    }

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
/*        checkMenuButton(text);
        claimPage.fullInfoAboutClaims();
        claimPage.addComment();*/
        claimPage.fullInfoAboutClaim();
    }

    @Test
    @DisplayName("20.Изменение комментария к заявке")
    @Description("Появится комментарий \"edit\" внизу карточки с указанием имени, под которым был добавлен комментарий, даты и времени добавления коментария")
    public void editComment() {
        String edit = "edit";
        clickAllOfChild(R.id.claim_list_recycler_view, R.id.all_claims_cards_block_constraint_layout, 4, 0);

        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.edit_comment_image_button), withContentDescription("button edit comment"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.claim_comments_list_recycler_view),
                                        0),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction textInputEditText5 = onView(
                allOf(childAtPosition(
                                childAtPosition(
                                        withId(R.id.comment_text_input_layout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText5.perform(replaceText(edit));

        ViewInteraction textInputEditText6 = onView(
                allOf(withText("edit"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.comment_text_input_layout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText6.perform(closeSoftKeyboard());

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.save_button), withText("Save"), withContentDescription("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                1)));
        materialButton4.perform(scrollTo(), click());
        elementWaiting(withId(R.id.add_comment_image_button), 3000);
        onView(withId(R.id.claim_comments_list_recycler_view))
                .check(matches(hasDescendant(allOf(
                        withId(R.id.comment_description_text_view),
                        withText(edit)))));
    }
    @Test
    @DisplayName("21.Отказ в сохранении пустого комментария")
    @Description("Появится сообщение, что поле не может быть пустым")
    public void shouldNotAddEmptyComment() {
        tapHamburger(menu_item);
        detailedClaimPage.detailedClaim();
        claimPage.clickAddCommentInputText("");
        onView(withText(R.string.toast_empty_field)).inRoot(new EspressoHelper.ToastMatcher())
                .check(matches(withText("The field cannot be empty.")));
    }
}
