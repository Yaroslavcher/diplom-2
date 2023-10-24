package ru.iteco.fmhandroid.ui.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.pages.Logged;
import ru.iteco.fmhandroid.ui.pages.LoginPage;
import ru.iteco.fmhandroid.ui.pages.NewsPage;
import ru.iteco.fmhandroid.ui.AppActivity;

@RunWith(AllureAndroidJUnit4.class)
public class NewsTest extends NewsPage {
    Logged logged = new Logged();
    LoginPage loginPage = new LoginPage();

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

    String menu_item = "News";

    @Test
    @DisplayName("45.Создание новости")
    @Description("Сохранится карточка в категории \"Объявление\" с датой публикации и датой создания и автором, равными текущей дате и автору соответственно, и наименованием yaroslavcher и описанием \"test\"")
    public void shouldCreateNews() {
        tapHamburger(menu_item);
        checkEdit();
        createNews();
    }
    @Test
    @DisplayName("46.Удалание новости")
    @Description("Первая карточка исчезнет и остальные карточки сместятся вверх")
    public void shouldDeleteNews() {
        tapHamburger(menu_item);
        checkEdit();
        deleteNews();
    }
    @Test
    @DisplayName("50.Отмена создания новости")
    @Description("Откроется окно подтверждения выхода без сохранения создаваемой новости")
    public void shouldNotCreateNews() {
        tapHamburger(menu_item);
        checkEdit();
        notCreateNews();
    }

}
