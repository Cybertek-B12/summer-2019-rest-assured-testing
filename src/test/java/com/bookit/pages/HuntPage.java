package com.bookit.pages;

import com.bookit.utilities.BrowserUtils;
import com.bookit.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HuntPage extends NavigationMenu {

    @FindBy(tagName = "input")
    public WebElement date;

    @FindBy(css = "#mat-select-0 .mat-select-arrow")
    public WebElement from;

    @FindBy(css = "#mat-select-1 .mat-select-arrow")
    public WebElement to;

    @FindBy(css = ".mat-icon.material-icons")
    public WebElement search;

    @FindBy(xpath = "(//*[@class='tag is-transparent'])[1]")
    public WebElement availableSolidTime;

    @FindBy(xpath = "(//*[@class='tag is-transparent'])[1]")
    public WebElement availableIffiTime;

    public void selectFrom(String startTime) {
        from.click();
        String xpath = "//span[contains(.,'" + startTime + "')]";
        BrowserUtils.wait(1);
        Driver.get().findElement(By.xpath(xpath)).click();
    }

    public void selectTo(String endTime) {
        to.click();
        String xpath = "//span[contains(.,'" + endTime + "')]";
        BrowserUtils.wait(1);
        Driver.get().findElement(By.xpath(xpath)).click();
    }

    public void getAvailableSessions(String today, String startTime, String endTime) {
        selectFrom(startTime);
        selectTo(endTime);
        BrowserUtils.clickWithTimeOut(search, 5);
        WebDriverWait wait = new WebDriverWait(Driver.get(), 5);
        wait.until(ExpectedConditions.textToBePresentInElement(title, "free spots"));
        BrowserUtils.wait(3);
    }
}
