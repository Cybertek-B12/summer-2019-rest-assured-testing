package com.bookit.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RoomPage extends NavigationMenu {

    @FindBy(xpath = "(//p[@class='subtitle is-7'])[1]")
    public WebElement capacityQuote;

    @FindBy(className = "room-info-icon")
    public WebElement capacityImg;

    @FindBy(xpath = "(//p[@class='title is-6'])[2]")
    public WebElement equipment;

}
