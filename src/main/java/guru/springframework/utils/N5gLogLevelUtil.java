package guru.springframework.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.logging.LogLevel;

import guru.springframework.domain.Log;
import guru.springframework.entity.N5gLogLevel;

public class N5gLogLevelUtil {
	public static List<N5gLogLevel> getLogLevels(String[] loglevelNames) {
		List<N5gLogLevel> n5gLogLevels = new ArrayList<N5gLogLevel>();
		for (LogLevel l : LogLevel.values()) {
			n5gLogLevels.add(new N5gLogLevel(l.toString(), false));
		}
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
			         .filter(t ->  loglevelNameList.stream().anyMatch(t1 -> t.getLevel().equals(t1) ))
			         .collect(Collectors.toList());
		}
		return logsResult;
	}
}
