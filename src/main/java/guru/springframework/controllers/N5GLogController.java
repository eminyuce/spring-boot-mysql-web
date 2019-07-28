package guru.springframework.controllers;

import guru.springframework.entity.LogSearch;
import guru.springframework.services.LogService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
	public String listLogs(@ModelAttribute LogSearch logSearch, Model model) {
		model.addAttribute("LogSearch", logSearch);
		if (StringUtils.isNotEmpty(logSearch.getSearch())) {
			System.out.println(logSearch.getSearch());
			model.addAttribute("logs", logService.listAllByMessageContains(logSearch.getSearch()));

		} else {
			model.addAttribute("logs", logService.listAll());
		}
		return "log/list";
	}

}
