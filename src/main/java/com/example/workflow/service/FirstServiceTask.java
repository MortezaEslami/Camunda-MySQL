package com.example.workflow.service;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class FirstServiceTask implements JavaDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(FirstServiceTask.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Random random =  new Random();
        boolean conditionVariable = random.nextBoolean();
        int value = random.nextInt(10);
        execution.setVariable("conditionVariable", conditionVariable );
        execution.setVariable("value", value );
        LOGGER.info(" First Service Task with boolean variable {} and value is {} !!!!" , conditionVariable , value );
    }

}