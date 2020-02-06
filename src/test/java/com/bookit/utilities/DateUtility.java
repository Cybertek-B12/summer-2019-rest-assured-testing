package com.bookit.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtility {

    public static String todaysDate(){
        String today = new SimpleDateFormat("MMMM dd, yyy").format(new Date());
        return today;
    }
}
// MMMM dd, yyy  --> February 17, 2019
// MM-dd-yy     --> 02-17-19
// dd-MM-yy    --> 17-02-19
// yyyy-MM-dd hh:mm:ss   --> 2019-02-17 03:45:11