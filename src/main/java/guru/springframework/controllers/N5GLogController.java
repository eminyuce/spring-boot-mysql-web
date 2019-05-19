package guru.springframework.controllers;

import guru.springframework.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("/log")
public class N5GLogController {

    private LogService logService;

    @Autowired
    public N5GLogController(LogService logService) {
        this.logService = logService;
    }


    @RequestMapping({"/log/list", "/log"})
    public String listLogs(Model model){
        model.addAttribute("logs", logService.listAll());
        return "log/list";
    }

}


