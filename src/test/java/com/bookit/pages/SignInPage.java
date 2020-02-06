package com.bookit.pages;

import com.bookit.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage {

    public SignInPage(){
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(name = "email")
    public WebElement email;

    @FindBy(name = "password")
    public WebElement password;

    @FindBy(css = "button[type='submit']")
    public WebElement signIn;

    @FindBy(tagName = "h1")
    public WebElement title;

    @FindBy(className = "subtitle")
    public WebElement subtitle;


    public static String currentUserEmail;

    public void login(String email, String password){
        currentUserEmail = email;
        this.email.sendKeys(email);
        this.password.sendKeys(password);
        signIn.click();

        WebDriverWait wait = new WebDriverWait(Driver.get(), 5);
        wait.until(ExpectedConditions.urlContains("map"));

    }
}
