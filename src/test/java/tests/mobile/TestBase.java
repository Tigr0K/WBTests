package tests.mobile;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import drivers.BrowserstackMobileDriver;
import drivers.LocalMobileDriver;
import helpers.Attach;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.className;
import static io.qameta.allure.Allure.step;

public class TestBase {
    public static String runType = System.getProperty("runType");


    @BeforeAll
    static void beforeAll() {
        switch (runType) {
            case "remote":
                Configuration.browser = BrowserstackMobileDriver.class.getName();
                break;
            case "local":
                Configuration.browser = LocalMobileDriver.class.getName();
                break;
        }
        Configuration.browserSize = null;
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
        step("Подготовка к тестированию", () -> {
            $$(className("android.widget.TextView"))
                    .findBy(text("Россия")).click();
            $(AppiumBy.id("com.wildberries.ru:id/remindLaterButton")).click();
        });
    }

    @AfterEach
    void addAttachments() {
        String sessionId = sessionId().toString();
        Attach.pageSource();
        if (runType.equals("local")) {
            Attach.screenshotAs("Last screenshot");
        }
        if (runType.equals("remote")) {
            Attach.addVideo(sessionId);
        }
        closeWebDriver();
    }
}
