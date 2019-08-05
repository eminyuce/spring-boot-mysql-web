package i2i.n5g.logs.utils;

import java.util.ArrayList;

public class StringUtils {
	public static String formatINSql(String[] parameters) {
		String result = String.join(",", getSingleQuote(parameters)).trim();
		return result;
	}

	public static ArrayList<String> getSingleQuote(String[] logLevels) {
		ArrayList<String> s2 = new ArrayList<String>();
		if (logLevels != null) {
			for (int i = 0; i < logLevels.length; i++) {
				s2.add("'" + logLevels[i] + "'");
			}
		}
		return s2;
	}
}
