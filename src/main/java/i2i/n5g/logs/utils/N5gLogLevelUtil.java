package i2i.n5g.logs.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.logging.LogLevel;

import i2i.n5g.logs.domain.Log;
import i2i.n5g.logs.entity.N5gLogLevel;

public class N5gLogLevelUtil {
	public static List<N5gLogLevel> getLogLevels(String[] loglevelNames) {
		List<N5gLogLevel> n5gLogLevels = new ArrayList<N5gLogLevel>();
		//TRACE, DEBUG, INFO, WARN, ERROR, FATAL, OFF
		n5gLogLevels.add(new N5gLogLevel(LogLevel.TRACE.toString(), false));
		n5gLogLevels.add(new N5gLogLevel(LogLevel.DEBUG.toString(), false));
		n5gLogLevels.add(new N5gLogLevel(LogLevel.INFO.toString(), false));
		n5gLogLevels.add(new N5gLogLevel(LogLevel.WARN.toString(), false));
		n5gLogLevels.add(new N5gLogLevel(LogLevel.ERROR.toString(), false));
		n5gLogLevels.add(new N5gLogLevel(LogLevel.FATAL.toString(), false));
		if (loglevelNames != null && loglevelNames.length > 0) {
			List<String> loglevelNameList = Arrays.asList(loglevelNames);
			for (N5gLogLevel l : n5gLogLevels) {
				l.setSelected(loglevelNameList.stream().anyMatch(t1 -> l.getName().equals(t1)));
			}
		}  
		return n5gLogLevels;
	}
	public static  List<Log> extractByLogLevel(String[] loglevelNames, List<Log> logsResult) {
		if (loglevelNames != null && loglevelNames.length > 0) {
			List<String> loglevelNameList = Arrays.asList(loglevelNames);
			logsResult = logsResult.stream()
			         .filter(t ->  loglevelNameList.stream().anyMatch(t1 -> t.getLevel().equalsIgnoreCase(t1) ))
			         .collect(Collectors.toList());
		}
		return logsResult;
	}
}
