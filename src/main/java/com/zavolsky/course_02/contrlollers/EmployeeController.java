package com.zavolsky.course_02.contrlollers;

import com.zavolsky.course_02.services.EmployeeImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
    private final EmployeeImpl employees;

    public EmployeeController (EmployeeImpl employees) {
        this.employees = employees;
    }
}
