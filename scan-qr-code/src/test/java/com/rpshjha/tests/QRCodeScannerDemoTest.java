package com.rpshjha.tests;

import com.google.zxing.WriterException;
import com.rpshjha.utils.FileUtils;
import com.rpshjha.utils.GenerateQR;
import com.testvagrant.commons.entities.DeviceDetails;
import com.testvagrant.mdb.android.Android;
import io.appium.java_client.MobileBy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static com.rpshjha.core.AppiumDriverInstance.*;
import static com.rpshjha.utils.EmulatorControls.*;
import static java.io.File.separator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Created by IntelliJ IDEA.
 * User: rupeshkumar
 * Date: 18/10/21 Time: 5:45 PM To
 * change this template use File | Settings | File and Code Templates.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class QRCodeScannerDemoTest {

    private WebDriverWait wait;
    private static final String EMULATOR_FILE_NAME = "custom.png";


    @BeforeEach
    public void setup() {
        launchAVD();
        DeviceDetails deviceDetails = new Android().getDevices().stream().findAny().get();
        setupAppium(deviceDetails);
        wait = new WebDriverWait(getAppiumDriver(), 30L);
    }

    @Test
    public void demoTestForQR() throws InterruptedException {
        String scannedText;

        Thread.sleep(8000);
        //placing first QR
        placeQRCode("FIRST QR");
        scannedText = wait.until(visibilityOfElementLocated(By.id("scan_result_TV"))).getText();
        System.out.println(scannedText);
        assertEquals("FIRST QR", scannedText);

        scannedText = "";

        Thread.sleep(8000);
        //Now replacing the QR Image
        placeQRCode("SECOND QR");
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.ByAccessibilityId.AccessibilityId("Navigate up"))).click();
        Thread.sleep(8000);
        scannedText = wait.until(visibilityOfElementLocated(By.id("scan_result_TV"))).getText();
        System.out.println(scannedText);
        assertEquals("SECOND QR", scannedText);

        Thread.sleep(8000);
    }

    @AfterEach
    public void tearDown() {
        quitAppiumDriver();
        killActiveEmulators();
    }

    private static String getQRCodeText() {
        FileUtils fileUtils = new FileUtils();
        String result = "";
        try {
            result = fileUtils.readXML("src/test/resources/qrcode-test-data/test-data.xml");
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void placeQRCode(String qrCodeText) {
        editEmulatorToren1BDFile();
        try {
            String fromFile = GenerateQR.generate(qrCodeText, EMULATOR_FILE_NAME);
            final String ANDROID_HOME = System.getenv("ANDROID_HOME");
            String toFile = ANDROID_HOME + separator + "emulator" + separator + "resources" + separator + EMULATOR_FILE_NAME;
            FileUtils.moveFileToResources(fromFile, toFile);
        } catch (IOException | WriterException e) {
            e.printStackTrace();
        }
    }
}
