package com.example.budgetapp

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.remote.MobileCapabilityType
import org.junit.After
import org.junit.Before
import org.openqa.selenium.remote.DesiredCapabilities
import java.net.URL
import java.nio.file.Paths
import java.util.concurrent.TimeUnit

open class AppiumTest {

    var driver: AppiumDriver<MobileElement>? = null

    @Before
    fun setup() {
        val capabilities = DesiredCapabilities()
        val userDir = System.getProperty("user.dir")
        val serverAddress = URL("http://127.0.0.1:4723/wd/hub")
        capabilities.setCapability(MobileCapabilityType.APPIUM_VERSION, "1.7.1")
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android")
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android")

        val localApp = "release\\app-release.apk"
        val appPath = Paths.get(userDir, localApp).toAbsolutePath().toString()
        capabilities.setCapability(MobileCapabilityType.APP, appPath)

        driver = AndroidDriver(serverAddress, capabilities)

        driver?.let {
            it.manage()?.timeouts()?.implicitlyWait(30, TimeUnit.SECONDS)
        }
    }

    @After
    fun tearDown() {
        driver?.quit()
    }
}