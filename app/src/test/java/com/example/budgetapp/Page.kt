package com.example.budgetapp

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.pagefactory.AndroidFindBy

class Page(val driver: AppiumDriver<*>) {
    @AndroidFindBy(id = "etEmail")
    private var emailTextField: MobileElement? = null

    @AndroidFindBy(id = "etPassword")
    private var passwordTextField: MobileElement? = null

    @AndroidFindBy(id = "btnLogin")
    private var loginButton: MobileElement? = null

    @AndroidFindBy(id = "tvLoginStatus")
    private var statusTextView: MobileElement? = null

    fun loginWith(email: String, password: String): Page {
        emailTextField?.setValue(email)
        passwordTextField?.setValue(password)
        driver.hideKeyboard()
        loginButton?.click()
        return this
    }

    fun isLoginSuccess(): Boolean {
        return statusTextView?.text == "Login Successfully"
    }
}