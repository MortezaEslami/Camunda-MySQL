package com.example.workflow.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping(value = "/api/v1/processes")
public class StartProcessRestController {

    private final RuntimeService runtimeService;

    @GetMapping(value = "/start")
    public ResponseEntity<String> startProcess() {
        log.info(" ******** Start Process **********");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("diagram");
        return new ResponseEntity<>(" Service is called by id = " + processInstance.getId(), HttpStatus.OK);
    }
}
