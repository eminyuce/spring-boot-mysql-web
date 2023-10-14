package com.emin.yuce.learning.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {

    public static final String DATE_FORMAT_PATTERN = "yyyyMMddHHmmss";

    public static String getDateTimeFormatted(String strDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_PATTERN);
        Date dateStr = formatter.parse(strDate);
        String formattedDate = formatter.format(dateStr);
        Date date1 = formatter.parse(formattedDate);
        formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return formatter.format(date1);

    }

}
