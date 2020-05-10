package i2i.n5g.logs.controllers;

import i2i.n5g.logs.domain.Log;
import i2i.n5g.logs.entity.LogParameter;
import i2i.n5g.logs.entity.LogSearch;
import i2i.n5g.logs.entity.N5gLogLevel;
import i2i.n5g.logs.entity.NfType;
import i2i.n5g.logs.services.LogService;
import i2i.n5g.logs.utils.N5gLogLevelUtil;
import i2i.n5g.logs.utils.NfTypeUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sun.source.doctree.SerialDataTree;

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
			@RequestParam(value = "loglevelNames", required = false) String[] loglevelNames,
			@RequestParam(value = "fromList", required = false) String[] fromList,
			@RequestParam(value = "toList", required = false) String[] toList, Model model) {
		List<Log> logsResult = new ArrayList<Log>();

		logsResult = logService.listAllByLogSearch(logSearch, selectedNfNames, loglevelNames, fromList, toList);
		model.addAttribute("LogSearch", logSearch);
		model.addAttribute("logs", logsResult);
		// TEST MERGE

		Map<String, Long> counting = logsResult.stream().sorted(Comparator.comparing(p -> p.getLevel()))
				.collect(Collectors.groupingBy(Log::getLevel, Collectors.counting()));

		model.addAttribute("logsLevels", counting);
		model.addAttribute("logsJavaException",
				logsResult.stream().filter(c -> c.getException().length() > 0).collect(Collectors.toList()));
		return "log/list";
	}


}
