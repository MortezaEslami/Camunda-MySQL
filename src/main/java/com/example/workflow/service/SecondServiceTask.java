package com.example.workflow.service;

import com.example.workflow.domain.Employee;
import com.example.workflow.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Slf4j
@Component
public class SecondServiceTask implements JavaDelegate {

    private final EmployeeRepository employeeRepository;

    @Override
    public void execute(DelegateExecution execution) {
        Integer value = (Integer) execution.getVariable("value");
        log.info(" Second service task wit value {} !!!!", value);
        Employee employee = employeeRepository.save(new Employee(null, "test" + value, "test" + value, value));
        execution.setVariable("createResult", employee.getId());
        log.info(" Result  returns {}  ", employee.getId());

    }

}