package com.example.workflow.controller;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class StartProcess {

    private static final Logger LOGGER = LoggerFactory.getLogger(StartProcess.class);

    @Autowired
    private RuntimeService runtimeService;

    private ProcessInstance processInstance;

    @RequestMapping(value = "/start", method = GET)
    @ResponseBody
    public String startProcess() {
        LOGGER.info(" Start Process");
        processInstance = runtimeService.startProcessInstanceByKey(   "diagram");
        return " Service Called with Id = " + processInstance.getId();
    }

}
