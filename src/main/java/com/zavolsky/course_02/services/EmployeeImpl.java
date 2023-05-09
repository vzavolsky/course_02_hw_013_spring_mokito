package com.zavolsky.course_02.services;

import com.zavolsky.course_02.EmployeeService;
import com.zavolsky.course_02.domain.Employee;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class EmployeeImpl implements EmployeeService {
    private final List<Employee> employees = new ArrayList<>();
    private final DepartmentImpl departments;

    public EmployeeImpl(@Lazy DepartmentImpl departments) {
        this.departments = departments;
    }

    public Employee createEmployee() {
        String[] names = {"John", "Sarah", "Mike", "Bob", "Robert", "Donna", "Anna", "Lisa", "George", "Peter", "Denny"};
        String[] fNames = {"Melory", "Gray", "Berg", "Davis", "Wild", "Shield", "Chain", "Chan"};

        Random s = new Random();
        Employee employee = new Employee();
        employee.setName(names[s.nextInt(names.length - 1)]);
        employee.setFName(fNames[s.nextInt(fNames.length - 1)]);
        employee.setSalary(s.nextInt(100_000) + 50_000);
        int depLength = departments.getDepartments().size();
        employee.setDepartmentID(departments.getDepartments().get(s.nextInt(depLength)).getID());
        employees.add(employee);

        return employee;
    }

    public List<Employee> getAll() {
        return employees;
    }

    public Employee add(String name, String fName, double salary, int depID) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setFName(fName);
        employee.setSalary(salary);
        employee.setDepartmentID(depID);
        employees.add(employee);
        return employee;
    }
}
