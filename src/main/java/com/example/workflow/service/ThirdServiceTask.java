package com.example.workflow.service;

import com.example.workflow.repository.EmployeeJDBCRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ThirdServiceTask implements JavaDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThirdServiceTask.class);

    @Autowired
    private EmployeeJDBCRepository employeeRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        int value = (int) execution.getVariable("value");
        LOGGER.info(" Third Service Task with value {}!!!!" , value);
        int result =  employeeRepository.deleteById(value);
        execution.setVariable("deleteResult", result);
        LOGGER.info( "result  returns {}  ", result);
    }

}