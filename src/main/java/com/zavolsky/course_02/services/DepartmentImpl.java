package com.zavolsky.course_02.services;

import com.zavolsky.course_02.DepartmentService;
import com.zavolsky.course_02.domain.Employee;
import com.zavolsky.course_02.exceptions.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentImpl implements DepartmentService {
    private final EmployeeImpl employees;

    public DepartmentImpl(EmployeeImpl employees) {
        this.employees = employees;
    }

    public List<Employee> getEmployeesByDepID(int depID) {
        return employees.getAll().stream()
                .filter(employee -> employee.getDepartmentID() == depID)
                .collect(Collectors.toList());
    }

    public Employee getEmployeesSalaryMaxByDepID(int depID) {
        return employees.getAll().stream()
                .filter(employee -> employee.getDepartmentID() == depID)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new BadRequestException("Can't find the employee."));
    }

    public Employee getEmployeesSalaryMinByDepID(int depID) {
        return employees.getAll().stream()
                .filter(employee -> employee.getDepartmentID() == depID)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new BadRequestException("Can't find the employee."));
    }

    public double getEmployeesSalarySumByDepID(int depID) {
        return employees.getAll().stream()
                .filter(employee -> employee.getDepartmentID() == depID)
                .mapToDouble(employees -> employees.getSalary())
                .sum();
    }

    public Map<Integer, List<Employee>> showEmployeesByDepartment() {
        return employees.getAll().stream().
                collect(Collectors.groupingBy(Employee::getDepartmentID));
    }
}
