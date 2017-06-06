package com.dazzlingstreak.util.domain;

import com.dazzlingstreak.domain.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/6.
 */
public class TestImportEntity {

    private Employee employee = new Employee();

    private List<Employee> employees= new ArrayList<>();

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
