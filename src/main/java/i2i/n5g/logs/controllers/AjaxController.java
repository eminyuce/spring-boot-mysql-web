package i2i.n5g.logs.controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import i2i.n5g.logs.domain.Log;
import i2i.n5g.logs.entity.LogParameter;
import i2i.n5g.logs.entity.LogSearch;
import i2i.n5g.logs.services.LogService;

@RestController
public class AjaxController {

	private LogService logService;

	@Autowired
	public AjaxController(LogService logService) {
		this.logService = logService;

	}

	@RequestMapping(value = "/ajax/getLog", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Log getLog(@Valid @RequestBody LogParameter logParameter) {
	   return logService.getById(logParameter.getId());
	}
	
	@RequestMapping(value = "/ajax/list", method = RequestMethod.GET, produces = "application/json" )
	public @ResponseBody List<Log> listLogs2(@ModelAttribute LogSearch logSearch,
			@RequestParam(value = "selectedNfNames", required = false) String[] selectedNfNames,
			@RequestParam(value = "loglevelNames", required = false) String[] loglevelNames,
			@RequestParam(value = "fromList", required = false) String[] fromList,
			@RequestParam(value = "toList", required = false) String[] toList, Model model) {
		return logService.listAllByLogSearch(logSearch, selectedNfNames, loglevelNames, fromList, toList);
	}
 
}
