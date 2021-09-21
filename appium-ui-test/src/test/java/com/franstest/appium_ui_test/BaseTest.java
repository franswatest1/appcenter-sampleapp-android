package com.franstest.appium_ui_test;

import io.appium.java_client.service.local.AppiumDriverLocalService;

import org.junit.After;
import org.junit.Before;

import java.io.IOException;
import java.net.URL;

public abstract class BaseTest {

    private static AppiumDriverLocalService service;

    @Before
    public void globalSetup () throws IOException {
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();
    }

    @After
    public void globalTearDown () {
        if (service != null) {
            service.stop();
        }
    }

    public static URL getServiceUrl () {
        return service.getUrl();
    }

}
