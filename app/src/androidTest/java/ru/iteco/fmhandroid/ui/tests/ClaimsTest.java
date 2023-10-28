package ru.iteco.fmhandroid.ui.tests;


import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.pages.ClaimPage;
import ru.iteco.fmhandroid.ui.pages.DetailedClaimPage;
import ru.iteco.fmhandroid.ui.pages.Logged;
import ru.iteco.fmhandroid.ui.pages.LoginPage;
import ru.iteco.fmhandroid.ui.utils.EspressoBaseTest;

@RunWith(AllureAndroidJUnit4.class)
public class ClaimsTest extends EspressoBaseTest {
    Logged logged = new Logged();
    LoginPage loginPage = new LoginPage();
    ClaimPage claimPage = new ClaimPage();
    DetailedClaimPage detailedClaimPage = new DetailedClaimPage();



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
        claimPage.minimize();
    }

    @Test
    @DisplayName("10.Открытие заявки из списка ALL CLAIMS")
    @Description("Откроется подробная информация о заявке при клике на кнопку ALL CLAIMS и стрелку заявки")
    public void claimDetailedInfo() {
        claimPage.openDetailedClaim();
    }

    @Test
    @DisplayName("11.Создание заявки на главном экране")
    @Description("При нажатии на кнопку добавления заявки на главном экране и сохранении информации, заявка создается")
    public void shouldCreateClaimOnMainScreen() {
        String title = "title";
        String date = getCurrentDate();
        claimPage.createClaim(title, date);
        claimPage.created(title);
    }

    @Test
    @DisplayName("12.Невозможность ввода темы длиннее максимальной на странице \"Заявки\"")
    @Description("Приложение ограничивает тему 50 символами")
    public void shouldNotCreateTooLongClaimTitle() {
        String tooLongTitle = "Этот заголовок длинее 50 символов должен быть обрезан";
        String title = tooLongTitle.substring(0, 50);
        String date = getCurrentDate();
        claimPage.createClaim(title, date);
        claimPage.created(title);
    }

    @Test
    @DisplayName("13.Создание заявки с пустой датой на странице \"Заявки\"")
    @Description("Появится сообщение о том, что необходимо заполнить пустые поля, и значок ошибки в поле Дата")
    public void shouldNotCreateClaimWithEmptyDate() {
        String title = "title";
        String date = "";
        claimPage.createClaim(title, date);
        claimPage.displayToastFillEmpty();
    }

    @Test
    @DisplayName("19.Добавление комментария к заявке")
    @Description("Появится введенный комментарий внизу карточки заявки с указанием имени, под которым был вход в приложение, текущей даты и времени добавления коментария")
    public void shouldAddComment() {
        claimPage.addComment();
    }

    @Test
    @DisplayName("20.Изменение комментария к заявке")
    @Description("Появится комментарий (случайные 10 букв) внизу карточки с указанием имени, под которым был добавлен комментарий, даты и времени добавления коментария")
    public void shouldEditComment() {
        claimPage.editComment();

    }
    @Test
    @DisplayName("21.Отказ в сохранении пустого комментария")
    @Description("Появится сообщение, что поле не может быть пустым")
    public void shouldNotAddEmptyComment() {
        tapHamburger(menu_item);
        detailedClaimPage.detailedClaim();
        claimPage.clickAddCommentInputText("");
        claimPage.displayToastForEmptyField();
    }
}
