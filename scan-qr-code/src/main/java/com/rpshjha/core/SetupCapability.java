package com.rpshjha.core;

import com.testvagrant.commons.entities.DeviceDetails;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.rpshjha.utils.ConfigReader.getProperty;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.*;
import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static io.appium.java_client.remote.MobileCapabilityType.*;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;

/**
 * Created by IntelliJ IDEA.
 * User: rupeshkumar
 * Date: 18/10/21
 * Time: 8:33 PM
 * To change this template use File | Settings | File and Code Templates.
 */
public class SetupCapability {

    public static DesiredCapabilities setCapability(DeviceDetails deviceDetails) {

        DesiredCapabilities cap = new DesiredCapabilities();

        cap.setCapability(APP, getProperty("appPath"));
        cap.setCapability(APP_PACKAGE, getProperty("appPackage"));
        cap.setCapability(APP_ACTIVITY, getProperty("appActivity"));

        cap.setCapability(PLATFORM_NAME, ANDROID);
        cap.setCapability(UDID, deviceDetails.getUdid());
        cap.setCapability(DEVICE_NAME, deviceDetails.getDeviceName());
        cap.setCapability(PLATFORM_VERSION, deviceDetails.getOsVersion());
        cap.setCapability(NEW_COMMAND_TIMEOUT, getProperty("commandTimeOut"));
        cap.setCapability(AUTOMATION_NAME, ANDROID_UIAUTOMATOR2);
        cap.setCapability(AUTO_GRANT_PERMISSIONS, true);
        cap.setCapability(NO_RESET, false);

        return cap;
    }
}
