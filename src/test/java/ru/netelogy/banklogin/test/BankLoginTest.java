package ru.netelogy.banklogin.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netelogy.banklogin.data.DataHelper;
import ru.netelogy.banklogin.data.SQLHelper;
import ru.netelogy.banklogin.page.LoginPage;

import static ru.netelogy.banklogin.data.SQLHelper.cleanDatabase;

public class BankLoginTest {
    @AfterAll
    static void teardown() {cleanDatabase();}

    @Test
    @DisplayName("Should successfully login to dashboard with exist login and password from sut test data")
    void shouldSuccessfullyLogin() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfoWithTestData();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verifyVerificationPageVisibility();
        var verificationCode = SQLHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode.getCode());
    }
}
