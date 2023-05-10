package com.zavolsky.course_02.services;

import com.zavolsky.course_02.EmployeeService;
import com.zavolsky.course_02.domain.Employee;
import com.zavolsky.course_02.exceptions.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeImpl implements EmployeeService {

    private final List<Employee> employees = new ArrayList<>();

    public Employee add(String name, String fName, int depID, double salary) {
        if (!name.matches("[a-zA-Zа-яА-Я]+") || !fName.matches("[a-zA-Zа-яА-Я]+")) {
            throw new BadRequestException("Wrong name.");
        }
        Employee employee = new Employee();
        employee.setName(name);
        employee.setFName(fName);
        employee.setSalary(salary);
        employee.setDepartmentID(depID);
        employees.add(employee);

        return employee;
    }

    public List<Employee> getAll() {
        return employees;
    }

    public Employee remove(String name, String fName) {
        Employee employee = find(name, fName);
        employees.remove(employee);
        return employee;
    }

    public Employee find(String name, String fName) {
        return employees.stream()
                .filter(e -> e.getName().equals(name) && e.getFName().equals(fName))
                .findFirst()
                .orElseThrow(() -> new BadRequestException("Can't find the employee."));
    }
}
