package com.rpshjha.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.io.File.separator;

/**
 * Created by IntelliJ IDEA.
 * User: rupeshkumar
 * Date: 18/10/21
 * Time: 5:38 PM
 * To change this template use File | Settings | File and Code Templates.
 */
public class EmulatorControls {

    private EmulatorControls() {
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(EmulatorControls.class);

    private static final String ANDROID_SDK_PATH = System.getenv("ANDROID_HOME");
    private static final String EMULATOR_PATH = ANDROID_SDK_PATH + separator + "emulator" + separator + "emulator";
    private static final String ADB_PATH = ANDROID_SDK_PATH + separator + "platform-tools" + separator + "adb";
    private static final String AVD_MANAGER_PATH = ANDROID_SDK_PATH + separator + "tools" + separator + "bin" + separator + "avdmanager";
    private static final String SDK_MANAGER_PATH = ANDROID_SDK_PATH + separator + "tools" + separator + "bin" + separator + "sdkmanager";

    public static void launchAVD(String nameOfAVD) {
        String[] aCommand = new String[]{EMULATOR_PATH, "-avd", nameOfAVD, "-no-snapshot", "-no-boot-anim"};
        String line;
        try {
            LOGGER.info("executing command " + String.join(" ", aCommand));
            Process process = new ProcessBuilder(aCommand).start();
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = input.readLine()) != null) {
                LOGGER.info(line);
            }
            process.waitFor(40, TimeUnit.SECONDS);
            if (isEmulatorOrDeviceRunning())
                LOGGER.info("Emulator ->> " + nameOfAVD + " launched successfully!");
            else
                throw new Exception("Emulator ->> " + nameOfAVD + " could not be launched");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void launchAVD() {
        createAVD();
        String[] command = new String[]{"sh", "bashscript/launch_avd.sh"};
        String line;
        try {
            Process process = new ProcessBuilder(command).start();
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isEmulatorOrDeviceRunning() {
        Process process;
        String[] aCommand = new String[]{ADB_PATH, "devices"};
        boolean isRunning = false;
        String output = "";
        String line;
        try {
            LOGGER.info("executing command " + String.join(" ", aCommand));
            process = new ProcessBuilder(aCommand).start();
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));

            while ((line = inputStream.readLine()) != null) {
                output += line;
            }
            if (!output.replace("List of devices attached", "").trim().equals("")) {
                isRunning = true;
                LOGGER.info("List of devices attached" + "\n" + output.replace("List of devices attached", ""));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return isRunning;
    }

    private static void createAVD() {
        String[] command = new String[]{"sh", "bashscript/create_avd.sh"};
        String line;
        try {
            Process process = new ProcessBuilder(command).start();
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void deleteAVD() {
        String[] command = new String[]{"sh", "bashscript/delete_avd.sh"};
        String line;
        try {
            Process process = new ProcessBuilder(command).start();
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = input.readLine()) != null) {
                LOGGER.info(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void listAVDs() {
        String command = "emulator -list-avds";
        String line;
        Set<String> listOfAVDs = new HashSet<>();
        try {
            Process p = Runtime.getRuntime().exec(command);
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                listOfAVDs.add(line);
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(listOfAVDs);
    }

    private static Set<String> listSystemImages() {
        String[] command = new String[]{"sh", "bashscript/list_system_image_android.sh"};

        String line;
        Set<String> listOfSystemImages = new HashSet<>();
        try {
            Process process = new ProcessBuilder(command).start();
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = input.readLine()) != null) {
                listOfSystemImages.add(line.split("\\|")[0].trim());
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOfSystemImages;
    }

    private static void downloadSystemImages(String androidPackageName) {
        String[] command = new String[]{SDK_MANAGER_PATH, "--install", androidPackageName};

        String line;
        Set<String> listOfSystemImages = new HashSet<>();
        try {
            Process process = new ProcessBuilder(command).start();
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = input.readLine()) != null) {
                listOfSystemImages.add(line.split("\\|")[0].trim());
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
