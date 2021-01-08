package com.example.workflow.service;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@Component
public class FirstServiceTask implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        Random random = new Random();
        boolean conditionVariable = random.nextBoolean();
        int value = random.nextInt(10);
        execution.setVariable("conditionVariable", conditionVariable);
        execution.setVariable("value", value);
        log.info(" First service task with boolean variable {} and value is {} !!!!", conditionVariable, value);
    }

}