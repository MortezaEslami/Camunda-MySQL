package com.example.workflow.repository;

import com.example.workflow.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeJDBCRepository {


    @Autowired
    JdbcTemplate jdbcTemplate;

    public List< Employee > findAll() {
        return jdbcTemplate.query("select * from employees", new EmployeeRowMapper());
    }

    public Optional< Employee > findById(long id) {
        return Optional.of(jdbcTemplate.queryForObject("select * from employees where id=?", new Object[] {
                id
            },
            new BeanPropertyRowMapper< Employee >(Employee.class)));
    }

    public int deleteById(long id) {
        return jdbcTemplate.update("delete from employees where id=?", new Object[] {
            id
        });
    }

    public int insert(Employee employee) {
        return jdbcTemplate.update("insert into employees (id, first_name, last_name, email_address) " + "values(?, ?, ?, ?)",
            new Object[] {
                employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmailId()
            });
    }

    public int update(Employee employee) {
        return jdbcTemplate.update("update employees " + " set first_name = ?, last_name = ?, email_address = ? " + " where id = ?",
            new Object[] {
                employee.getFirstName(), employee.getLastName(), employee.getEmailId(), employee.getId()
            });
    }
}