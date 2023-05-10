package com.zavolsky.course_02;

import com.zavolsky.course_02.domain.Employee;

import java.util.List;

public interface EmployeeService {

    Employee add(String name, String fName, int depID, double salary);
    List<Employee> getAll();
    Employee remove(String name, String fName);
    Employee find(String name, String fName);

}
