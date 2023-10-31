package ru.iteco.fmhandroid.ui.runner;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import ru.iteco.fmhandroid.ui.tests.AboutTest;
import ru.iteco.fmhandroid.ui.tests.CitationsTest;
import ru.iteco.fmhandroid.ui.tests.ClaimsTest;
import ru.iteco.fmhandroid.ui.tests.FilterClaimTest;
import ru.iteco.fmhandroid.ui.tests.LoginAndLogoutTest;
import ru.iteco.fmhandroid.ui.tests.NewsTest;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {AboutTest.class, CitationsTest.class, ClaimsTest.class, FilterClaimTest.class, LoginAndLogoutTest.class, NewsTest.class})
public class TestSuite {
}
