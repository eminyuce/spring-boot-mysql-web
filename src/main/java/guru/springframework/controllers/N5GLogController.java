package guru.springframework.controllers;

import guru.springframework.domain.Log;
import guru.springframework.entity.LogSearch;
import guru.springframework.entity.N5gLogLevel;
import guru.springframework.entity.NfType;
import guru.springframework.services.LogService;
import guru.springframework.utils.N5gLogLevelUtil;
import guru.springframework.utils.NfTypeUtil;

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
		logsResult = logService.listAllByLogSearch(logSearch,selectedNfNames,loglevelNames);
		model.addAttribute("LogSearch", logSearch);
		model.addAttribute("logs", logsResult);
		return "log/list";
	}







}
