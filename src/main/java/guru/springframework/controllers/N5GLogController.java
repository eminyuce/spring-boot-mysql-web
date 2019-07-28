package guru.springframework.controllers;

import guru.springframework.domain.Log;
import guru.springframework.entity.LogSearch;
import guru.springframework.entity.N5gLogLevel;
import guru.springframework.entity.NfType;
import guru.springframework.services.LogService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("/log")
public class N5GLogController {

	private LogService logService;

	@Autowired
	public N5GLogController(LogService logService) {
		this.logService = logService;

	}

	@RequestMapping({ "/log/list", "/log" })
	public String listLogs(@ModelAttribute LogSearch logSearch,
			@RequestParam(value = "selectedNfNames", required = false) String[] selectedNfNames,
			@RequestParam(value = "loglevelNames", required = false) String[] loglevelNames, Model model) {
		List<Log> logsResult = new ArrayList<Log>();

		List<NfType> nfTypes = new ArrayList<NfType>();
		nfTypes.add(new NfType("AMF", false));
		nfTypes.add(new NfType("SMF", false));
		nfTypes.add(new NfType("UDM", false));
		nfTypes.add(new NfType("UDR", false));
		nfTypes.add(new NfType("PCF", false));
		nfTypes.add(new NfType("NRF", false));
		if (selectedNfNames != null && selectedNfNames.length > 0) {
			for (NfType nfType : nfTypes) {
				List<String> selectedNfNameList = Arrays.asList(selectedNfNames);
				nfType.setSelected(selectedNfNameList.stream().anyMatch(t1 -> nfType.getName().equals(t1)));
			}
		} 
		

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

		logSearch.setLogLevels(n5gLogLevels);
		logSearch.setNfTypes(nfTypes);

		model.addAttribute("LogSearch", logSearch);
		if (StringUtils.isNotEmpty(logSearch.getSearch())) {
			System.out.println(logSearch.getSearch());
			logsResult = logService.listAllByMessageContains(logSearch.getSearch());

		} else {
			logsResult = logService.listAll();
		}
		if (selectedNfNames != null && selectedNfNames.length > 0) {
			List<String> selectedNfNameList = Arrays.asList(selectedNfNames);
			logsResult =	logsResult.stream()
	         .filter(t ->  selectedNfNameList.stream().anyMatch(t1 -> t.getNf_type().equals(t1) ))
	         .collect(Collectors.toList());
		}
		if (loglevelNames != null && loglevelNames.length > 0) {
			List<String> loglevelNameList = Arrays.asList(loglevelNames);
			logsResult = logsResult.stream()
			         .filter(t ->  loglevelNameList.stream().anyMatch(t1 -> t.getLevel().equals(t1) ))
			         .collect(Collectors.toList());
		}
		model.addAttribute("logs", logsResult);
		return "log/list";
	}

}
