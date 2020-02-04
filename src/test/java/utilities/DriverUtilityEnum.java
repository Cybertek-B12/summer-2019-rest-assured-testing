package utilities;

public class DriverUtilityEnum {

    public static String getDriver(Browser browser){
        // create a new webdriver based on given browser type
        switch (browser){
            case CHROME:
                return "chrome";
            case CHROME_HEADLESS:
                return "chrome-headless";
            case FIREFOX:
                return "firefox";
        }


        return "";
    }
}
