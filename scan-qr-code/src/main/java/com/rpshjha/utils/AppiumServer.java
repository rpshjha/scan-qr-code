package com.rpshjha.utils;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class AppiumServer {

	private AppiumServer() {
	}

	private static final String IP = ConfigReader.getProperty("ipAddress");

	private static final ThreadLocal<AppiumDriverLocalService> appiumService = new ThreadLocal<>();

	public static AppiumDriverLocalService startServer() {
		AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder();

		appiumServiceBuilder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");
		appiumServiceBuilder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
		appiumServiceBuilder.usingAnyFreePort();
		appiumServiceBuilder.withIPAddress(IP);

		appiumService.set(AppiumDriverLocalService.buildService(appiumServiceBuilder));
		appiumService.get().start();
		return appiumService.get();
	}

	public static void stopServer() {
		if (appiumService.get() != null) {
			appiumService.get().stop();
			appiumService.remove();
		}
	}

}
