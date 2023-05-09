package com.zavolsky.course_02.services;

import com.zavolsky.course_02.domain.Department;
import com.zavolsky.course_02.domain.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class DepartmentImplTest {
    private final Department department = new Department("First");
    private final Employee employee = new Employee("Name", "FName", 10000, 1);

    private DepartmentImpl departments;

    @Mock
    private EmployeeImpl employees;

    @BeforeEach
    public void setUp() {
        departments = new DepartmentImpl(employees);
    }

    @Test
    public void getEmployeesByDepID() {
        assertNotNull(employees);

        Mockito.when(employees.add("Name", "FName", 10000, 1)).thenReturn(employee);

        List<Employee> expected = departments.getEmployeesByDepID(1);
        assertEquals(expected.get(0).getDepartmentID(),1);
    }
}
