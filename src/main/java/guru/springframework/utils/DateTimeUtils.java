package guru.springframework.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {
	
	public static String getDateTimeFormatted(String strDate) throws ParseException
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		Date dateStr = formatter.parse(strDate);
		String formattedDate = formatter.format(dateStr);
		Date date1 = formatter.parse(formattedDate);
		formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		return  formatter.format(date1);
		
	}

}
