package com.rpshjha.tests;

import com.google.zxing.WriterException;
import com.rpshjha.utils.FileUtils;
import com.rpshjha.utils.GenerateQR;
import com.testvagrant.commons.entities.DeviceDetails;
import com.testvagrant.mdb.android.Android;
import org.junit.*;
import org.openqa.selenium.By;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static com.rpshjha.core.AppiumDriverInstance.*;
import static com.rpshjha.utils.ConfigReader.getProperty;
import static com.rpshjha.utils.EmulatorControls.launchAVD;

/**
 * Created by IntelliJ IDEA.
 * User: rupeshkumar
 * Date: 18/10/21
 * Time: 5:45 PM
 * To change this template use File | Settings | File and Code Templates.
 */
public class QRCodeScannerDemo {

    @BeforeClass
    public static void placeQRCode() throws IOException, WriterException, ParserConfigurationException, SAXException {
        String filename = "custom.png";
        String fromFile = GenerateQR.generate(getQRCodeText(), filename);
        String toFile = System.getProperty("user.home") + "/Library/Android/sdk/emulator/resources/" + filename;
        FileUtils.moveFileToResources(fromFile, toFile);
    }

    @Before
    public void setup() {
        launchAVD(getProperty("nameOfAVD"));

        DeviceDetails deviceDetails = new Android().getDevices().stream().findAny().get();
        setupAppium(deviceDetails);
    }

    @Test
    public void demoTestForQR() throws InterruptedException, ParserConfigurationException, IOException, SAXException {
        Thread.sleep(8000);
        String scannedText = getAppiumDriver().findElement(By.id("scan_result_TV")).getText();
        Assert.assertEquals(getQRCodeText(), scannedText);
        Thread.sleep(5000);
    }

    @After
    public void tearDown() {
        quitAppiumDriver();
    }

    private static String getQRCodeText() throws ParserConfigurationException, IOException, SAXException {
//        FileUtils fileUtils = new FileUtils();
//        return fileUtils.readXML("src/test/resources/qrcode-test-data/test-data.xml");
        return  "FOR DEMO";
    }
}

