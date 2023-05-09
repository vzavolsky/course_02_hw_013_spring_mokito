package com.zavolsky.course_02.services;

import com.zavolsky.course_02.DepartmentService;
import com.zavolsky.course_02.domain.Department;
import com.zavolsky.course_02.domain.Employee;
import com.zavolsky.course_02.exceptions.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentImpl implements DepartmentService {
    private final List<Department> departments = new ArrayList<>();
    private final EmployeeImpl employees;

    public DepartmentImpl(EmployeeImpl employees) {
        this.employees = employees;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public Department createDepartment() {
        String[] departmentNames = {"Financial Department", "HR Department", "Marketing Department", "Sell Department", "General management Department"};

        for(String newDepName: departmentNames) {
            if (checkDepartment(newDepName)) {
                Department newDep = new Department(newDepName);
                departments.add(newDep);
                return newDep;
            }
        }
        throw new BadRequestException("List of prepared departments have finished.");
    }

    private boolean checkDepartment(String departmentName) {
        for (Department dep: departments) {
            if (dep.getDepartmentName().equals(departmentName)) {
                return false;
            }
        }
        return true;
    }

    public Department add(String departmentName) {
        if (checkDepartment(departmentName)) {
            Department department = new Department(departmentName);
            departments.add(department);
            return department;
        }
        throw new BadRequestException("Department already exists.");
    }

    public Department getDepByID(int ID) {
        for (Department dep: departments) {
            if (dep.getID() == ID) {
                return dep;
            }
        }
        throw new BadRequestException("Dep " + ID + " is not found.");
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
                .orElseThrow();
    }

    public Employee getEmployeesSalaryMinByDepID(int depID) {
        return employees.getAll().stream()
                .filter(employee -> employee.getDepartmentID() == depID)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow();
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
