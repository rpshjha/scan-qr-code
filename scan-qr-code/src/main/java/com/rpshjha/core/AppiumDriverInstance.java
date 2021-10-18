package com.rpshjha.core;

import com.rpshjha.utils.AppiumServer;
import com.testvagrant.commons.entities.DeviceDetails;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

import java.net.URL;

import static com.rpshjha.core.SetupCapability.setCapability;

public class AppiumDriverInstance {

    private AppiumDriverInstance() {
    }

    private static final ThreadLocal<io.appium.java_client.AppiumDriver<WebElement>> appiumDriver = new ThreadLocal<>();

    public static void setupAppium(DeviceDetails deviceDetails) {
        URL appiumServerURL = AppiumServer.startServer().getUrl();
        appiumDriver.set(new AndroidDriver<>(appiumServerURL, setCapability(deviceDetails)));
    }

    public static io.appium.java_client.AppiumDriver<WebElement> getAppiumDriver() {
        return appiumDriver.get();
    }

    public static void quitAppiumDriver() {
        if (appiumDriver.get() != null) {
            appiumDriver.get().quit();
            appiumDriver.remove();
            AppiumServer.stopServer();
        }
    }
}