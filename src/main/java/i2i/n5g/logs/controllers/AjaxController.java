package i2i.n5g.logs.controllers;

import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import i2i.n5g.logs.domain.Log;
import i2i.n5g.logs.entity.LogParameter;
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
}
