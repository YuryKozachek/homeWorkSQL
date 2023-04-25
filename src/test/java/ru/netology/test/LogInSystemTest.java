package ru.netology.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.DataSQL;
import ru.netology.page.LoginPage;
import ru.netology.page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;

public class LogInSystemTest {

    @AfterAll
    static void clearDB() {
        DataSQL.cleanDataBase();
    }

    LoginPage loginPage;

    @Test
    void shouldLogInSystem() {
        loginPage = open("http://localhost:9999", LoginPage.class);
        DataHelper.AuthInfo authInfo = DataHelper.getAuthInfo();
        VerificationPage verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verificationPageVisible();
        DataHelper.VerificationCode verificationCode = DataSQL.getVerificationCode();
        verificationPage.validVerify(verificationCode.getCode());
    }

}
