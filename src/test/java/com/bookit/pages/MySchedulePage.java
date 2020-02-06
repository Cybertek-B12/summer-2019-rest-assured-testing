package com.bookit.pages;

import com.bookit.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MySchedulePage extends NavigationMenu {

    /**
     * it returns cell block based on time and days count startig from today up to 7 days.
     * 0 means today, 1 means tomorrow, 2 means day after tomorrow etc
     *
     * @param startTime
     * @param addDays
     * @return
     */
    public WebElement getCell(String startTime, int addDays) {
        String xpath = "//th[contains(.,'" + startTime + "')]/../td[" + (addDays + 1) + "]";
        return Driver.get().findElement(By.xpath(xpath));
    }

    /**
     * returns all the cell blocks for required day
     * days start from today upto next 7 days
     * 0 means today, 1 means tomorrow, 2 means day after tomorrow etc
     *
     * @param addDays
     * @return
     */
    public List<WebElement> getCellsForDay(int addDays) {
        return Driver.get().findElements(By.xpath("//tr/td[" + (addDays + 1) + "]"));
    }

    /**
     * tells if a single cell block is available based on time and days count starting from today up to 7 days.
     * 0 means today, 1 means tomorrow, 2 means day after tomorrow etc
     *
     * @param startTime
     * @param addDays
     * @return
     */
    public boolean isCellBlockAvailable(String startTime, int addDays) {
        return !getCell(startTime, addDays).getAttribute("class").contains("conference");
    }

    /**
     * tells if there is any available block for that day based days count starting from today up to 7 days.
     * 0 means today, 1 means tomorrow, 2 means day after tomorrow etc
     * @param addDays
     * @return
     */
    public boolean isDayAvailable(int addDays){
        for (WebElement cell: getCellsForDay(addDays)){
            if (!cell.getAttribute("class").contains("conference")){
                return  true;
            }
        }
        return false;
    }


}
