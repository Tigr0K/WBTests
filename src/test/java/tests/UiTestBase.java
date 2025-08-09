package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.*;
import helpers.WebAttachUtils;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class UiTestBase {
    public static final WebConfig webConfig = WebProvider.getWebConfig();


    @BeforeAll
    public static void setupBaseTestConfiguration() {
        BaseConfig baseConfig = new BaseConfig(webConfig);
        baseConfig.setConfig();
    }

    @BeforeEach
    public void setupAllureListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void shutDown() {
        WebAttachUtils.screenshotAs("Скриншот результата");
        WebAttachUtils.pageSource();
        if (!Configuration.browser.equals("firefox"))
            WebAttachUtils.browserConsoleLogs();
        if (webConfig.isRemote()) {
            WebAttachUtils.addVideo();
        }
        closeWebDriver();
    }
}
