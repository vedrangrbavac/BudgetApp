package com.example.budgetapp

import io.appium.java_client.MobileElement
import org.junit.Test
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.util.concurrent.TimeUnit


class LoginAutomationTest : AppiumTest() {
    @Test
    fun login() {
        val el3 =
            driver!!.findElementById("com.example.budgetapp:id/etEmail") as MobileElement
        el3.click()
        el3.sendKeys("test@test.com")
        val el4 =
            driver!!.findElementById("com.example.budgetapp:id/etPassword") as MobileElement
        el4.click()
        el4.sendKeys("testTest")
        driver!!.hideKeyboard()
        val el5 =
            driver!!.findElementById("com.example.budgetapp:id/btnLogin") as MobileElement
        el5.click()
        val el6 =
            driver!!.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Profil\"]/android.widget.ImageView") as MobileElement
        el6.click()
        //just to see the screen properly
        Thread.sleep(5000)

    }
}