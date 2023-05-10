package com.zavolsky.course_02;

import com.zavolsky.course_02.domain.Employee;
import java.util.List;
import java.util.Map;

public interface DepartmentService {

     List<Employee> getEmployeesByDepID(int depID);
     Employee getEmployeesSalaryMaxByDepID(int depID);
     Employee getEmployeesSalaryMinByDepID(int depID);
     double getEmployeesSalarySumByDepID(int depID);
     Map<Integer, List<Employee>> showEmployeesByDepartment();

}
