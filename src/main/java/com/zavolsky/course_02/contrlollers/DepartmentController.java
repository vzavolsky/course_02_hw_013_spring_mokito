package com.zavolsky.course_02.contrlollers;

import com.zavolsky.course_02.domain.Department;
import com.zavolsky.course_02.domain.Employee;
import com.zavolsky.course_02.services.DepartmentImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/department")
public class DepartmentController {
    private final DepartmentImpl departments;

    public DepartmentController(DepartmentImpl departments) {
        this.departments = departments;
    }

    @GetMapping(path = "/add")
    public Department add(@RequestParam("name") String name) {
        return departments.add(name);
    }

    @GetMapping(path = "/create")
    public Department create() {
        return departments.createDepartment();
    }

    @GetMapping(path = "/{id}/employees")
    public List<Employee> getEmployeesByDepID(@PathVariable("id") int depID) {
        return departments.getEmployeesByDepID(depID);
    }

    @GetMapping(path = "/{id}/salary/max")
    public Employee getEmployeesSalaryMaxByDepID(@PathVariable("id") int depID) {
        return departments.getEmployeesSalaryMaxByDepID(depID);
    }

    @GetMapping(path = "/{id}/salary/min")
    public Employee getEmployeesSalaryMinByDepID(@PathVariable("id") int depID) {
        return departments.getEmployeesSalaryMinByDepID(depID);
    }

    @GetMapping(path = "/{id}/salary/sum")
    public double getEmployeesSalarySumByDepID(@PathVariable("id") int depID) {
        return departments.getEmployeesSalarySumByDepID(depID);
    }

    @GetMapping(path = "/show")
    public List<Department> showAllDepartments() {
        return departments.getDepartments();
    }

    @GetMapping(path = "/employees")
    public Map<Integer, List<Employee>> showEmployeesByDepartment() {
        return departments.showEmployeesByDepartment();
    }
}
