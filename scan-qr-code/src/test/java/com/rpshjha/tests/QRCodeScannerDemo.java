package com.rpshjha.tests;

import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.rpshjha.utils.GenerateQR;
import com.testvagrant.commons.entities.DeviceDetails;
import com.testvagrant.mdb.android.Android;
import org.junit.*;
import org.openqa.selenium.By;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import static com.rpshjha.core.AppiumDriverInstance.*;
import static com.rpshjha.utils.EmulatorControls.launchEmulator;

/**
 * Created by IntelliJ IDEA.
 * User: rupeshkumar
 * Date: 18/10/21
 * Time: 5:45 PM
 * To change this template use File | Settings | File and Code Templates.
 */
public class QRCodeScannerDemo {

    @BeforeClass
    public static void placeQRCode() throws IOException, WriterException {
        String filename = "custom.png";
        String fromFile = generateQR(getQRCodeText(), filename);
        String toFile = System.getProperty("user.home") + "/Library/Android/sdk/emulator/resources/" + filename;
        moveFileToResources(fromFile, toFile);
    }

    @Before
    public void setup() {
        String nameOfAVD = "Pixel";
        launchEmulator(nameOfAVD);

        DeviceDetails deviceDetails = new Android().getDevices().stream().findAny().get();
        setupAppium(deviceDetails);
    }

    @Test
    public void demoTestForQR() throws InterruptedException {
        Thread.sleep(8000);
        String scannedText = getAppiumDriver().findElement(By.id("scan_result_TV")).getText();
        Assert.assertEquals(getQRCodeText(), scannedText);
        Thread.sleep(5000);
    }

    @After
    public void tearDown() {
        quitAppiumDriver();
    }

    private static String getQRCodeText() {
        return "we are testing QR code scanning from emulator";
    }

    private static String generateQR(String qrCodeText, String filename) throws IOException, WriterException {
        String path = System.getProperty("user.dir") + "/src/test/resources/qrcode/" + filename;
        String charset = "UTF-8";
        Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<>();
        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        GenerateQR.generate(qrCodeText, path, charset, hashMap, 100, 100);
        System.out.println("QR Code created successfully and placed at " + path);
        return path;
    }

    private static void moveFileToResources(String fromFile, String toFile) {
        Path source = Paths.get(fromFile);
        Path target = Paths.get(toFile);
        try {
            System.out.println("Moving file from " + fromFile + " to " + toFile);
            Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

