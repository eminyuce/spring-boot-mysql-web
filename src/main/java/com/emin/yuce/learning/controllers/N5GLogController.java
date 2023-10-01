package com.emin.yuce.learning.controllers;

import com.emin.yuce.learning.domain.Log;
import com.emin.yuce.learning.entity.LogSearch;
import com.emin.yuce.learning.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller("/log")
public class N5GLogController {

    private LogService logService;

    @Autowired
    public N5GLogController(LogService logService) {
        this.logService = logService;
    }

    @RequestMapping({"/log/list", "/log"})
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
