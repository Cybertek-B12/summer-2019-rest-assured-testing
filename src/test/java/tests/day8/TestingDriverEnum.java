package tests.day8;

import org.junit.jupiter.api.Test;
import utilities.Browser;
import utilities.DriverUtilityEnum;

public class TestingDriverEnum
{
    @Test
    public void test(){
        String driver = DriverUtilityEnum.getDriver(Browser.CHROME_HEADLESS);
        System.out.println(driver);

    }
}
