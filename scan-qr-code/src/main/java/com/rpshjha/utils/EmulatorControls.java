package com.rpshjha.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * User: rupeshkumar
 * Date: 18/10/21
 * Time: 5:38 PM
 * To change this template use File | Settings | File and Code Templates.
 */
public class EmulatorControls {

    static String homeDirectory = System.getProperty("user.home");
    static String sdkPath = homeDirectory + File.separator + "Library" + File.separator + "Android" + File.separator + "sdk";
    static String emulatorPath = sdkPath + File.separator + "emulator" + File.separator + "emulator";
    static String adbPath = sdkPath + File.separator + "platform-tools" + File.separator + "adb";

    public static void launchEmulator(String nameOfAVD) {

        String[] aCommand = new String[]{emulatorPath, "-avd", nameOfAVD, "-no-snapshot", "-no-boot-anim"};
        try {
            Process process = new ProcessBuilder(aCommand).start();
            process.waitFor(40, TimeUnit.SECONDS);
            if (isEmulatorOrDeviceRunning())
                System.out.println("Emulator ->> " + nameOfAVD + " launched successfully!");
            else
                throw new Exception("Emulator ->> " + nameOfAVD + " could not be launched");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isEmulatorOrDeviceRunning() {
        Process process;
        String[] commandDevices;
        boolean isRunning = false;
        String output = "";
        String line = null;
        try {
            commandDevices = new String[]{adbPath, "devices"};
            process = new ProcessBuilder(commandDevices).start();
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));

            while ((line = inputStream.readLine()) != null) {
                output = output + line;
            }
            if (!output.replace("List of devices attached", "").trim().equals("")) {
                isRunning = true;
                System.out.println("List of devices attached" + "\n" + output.replace("List of devices attached", ""));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return isRunning;
    }


}
