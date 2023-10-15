package com.emin.yuce.learning.controllers;

import com.emin.yuce.learning.domain.Log;
import com.emin.yuce.learning.entity.LogParameter;
import com.emin.yuce.learning.entity.LogSearch;
import com.emin.yuce.learning.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AjaxController {

    private final LogService logService;

    @Autowired
    public AjaxController(LogService logService) {
        this.logService = logService;

    }

    @RequestMapping(value = "/ajax/getLog", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody Log getLog(@Valid @RequestBody LogParameter logParameter) {
        return logService.getById(logParameter.getId());
    }

    @RequestMapping(value = "/ajax/list", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Log> listLogs2(@ModelAttribute LogSearch logSearch,
                                             @RequestParam(value = "selectedNfNames", required = false) String[] selectedNfNames,
                                             @RequestParam(value = "loglevelNames", required = false) String[] loglevelNames,
                                             @RequestParam(value = "fromList", required = false) String[] fromList,
                                             @RequestParam(value = "toList", required = false) String[] toList, Model model) {
        return logService.listAllByLogSearch(logSearch, selectedNfNames, loglevelNames, fromList, toList);
    }

}
