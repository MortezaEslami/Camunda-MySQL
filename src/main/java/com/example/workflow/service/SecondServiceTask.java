package com.example.workflow.service;

import com.example.workflow.repository.EmployeeJDBCRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SecondServiceTask implements JavaDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecondServiceTask.class);

    @Autowired
    private EmployeeJDBCRepository employeeRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        int value = (int) execution.getVariable("value");
        LOGGER.info("Second Service Task wit value {} !!!!" , value);
        int result =  employeeRepository.deleteById(value);
        execution.setVariable("deleteResult", result);
        LOGGER.info( "result  returns {}  ", result);

    }

}