package com.zavolsky.course_02.services;

import com.zavolsky.course_02.domain.Employee;
import com.zavolsky.course_02.exceptions.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentImplTest {

    @Mock
    private EmployeeImpl employees;

    @InjectMocks
    private DepartmentImpl departments;

    @BeforeEach
    public void beforeEach() {
        when(employees.getAll()).thenReturn(
                List.of(
                        new Employee("Name", "FName", 1, 10_000),
                        new Employee("Robo", "Model", 2, 20_000),
                        new Employee("RoboMax", "ModelMax", 2, 25_000),
                        new Employee("Vasya", "K", 3, 30_000),
                        new Employee("Gosha", "G", 3, 40_000)
                )
        );
    }

    public static Stream<Arguments> getEmployeesSalaryMaxByDepIDTestParams() {
        return Stream.of(
                Arguments.of(2, new Employee("RoboMax", "ModelMax", 2, 25_000)),
                Arguments.of(3, new Employee("Gosha", "G", 3, 40_000))
        );
    }

    @ParameterizedTest
    @MethodSource("getEmployeesSalaryMaxByDepIDTestParams")
    public void getEmployeesSalaryMaxByDepIDTest(int depID, Employee expected) {
        assertThat(departments.getEmployeesSalaryMaxByDepID(depID))
                .usingRecursiveComparison()
                .comparingOnlyFields("name", "fname", "department", "salary")
                .isEqualTo(expected);
    }

    @Test
    public void getEmployeesSalaryMaxByDepIDTest() {
        assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> departments.getEmployeesSalaryMaxByDepID(4));
    }

    public static Stream<Arguments> getEmployeesSalaryMinByDepIDTestParams() {
        return Stream.of(
                Arguments.of(2, new Employee("Robo", "Model", 2, 20_000)),
                Arguments.of(3, new Employee("Vasya", "K", 3, 30_000))
        );
    }

    @ParameterizedTest
    @MethodSource("getEmployeesSalaryMinByDepIDTestParams")
    public void getEmployeesSalaryMinByDepIDTest(int depID, Employee expected) {
        assertThat(departments.getEmployeesSalaryMinByDepID(depID))
                .usingRecursiveComparison()
                .comparingOnlyFields("name", "fname", "department", "salary")
                .isEqualTo(expected);
    }

    @Test
    public void getEmployeesSalaryMinByDepIDTest() {
        assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> departments.getEmployeesSalaryMinByDepID(4));
    }

    public static Stream<Arguments> getEmployeesByDepIDTestParams() {
        return Stream.of(
                Arguments.of(
                        1,
                        List.of(
                                new Employee("Name", "FName", 1, 10_000)
                        )
                ),
                Arguments.of(
                        2,
                        List.of(
                                new Employee("Robo", "Model", 2, 20_000),
                                new Employee("RoboMax", "ModelMax", 2, 25_000)
                        )
                ),
                Arguments.of(
                        3,
                        List.of(
                                new Employee("Vasya", "K", 3, 30_000),
                                new Employee("Gosha", "G", 3, 40_000)
                        )
                ),
                Arguments.of(
                        4,
                        List.of()
                )
        );
    }

    @ParameterizedTest
    @MethodSource("getEmployeesByDepIDTestParams")
    public void getEmployeesByDepIDTest(int depID, List<Employee> expected) {
        assertThat(departments.getEmployeesByDepID(depID))
                .usingRecursiveComparison()
                .comparingOnlyFields("name", "fname", "department", "salary")
                .isEqualTo(expected);
    }

    @Test
    public void showEmployeesByDepartmentIDTest() {
        assertThat(departments.showEmployeesByDepartment())
                .usingRecursiveComparison()
                .comparingOnlyFields("name", "fname", "department", "salary")
                .isEqualTo(
                        Map.of(
                                1,
                                List.of(
                                        new Employee("Name", "FName", 1, 10_000)
                                ),

                                2,
                                List.of(
                                        new Employee("Robo", "Model", 2, 20_000),
                                        new Employee("RoboMax", "ModelMax", 2, 25_000)
                                ),

                                3,
                                List.of(
                                        new Employee("Vasya", "K", 3, 30_000),
                                        new Employee("Gosha", "G", 3, 40_000)
                                )
                        )
                );
    }

    public static Stream<Arguments> getEmployeesSalarySumByDepIDTestParam() {
        return Stream.of(
                Arguments.of(1,10_000),
                Arguments.of(2,45_000),
                Arguments.of(3,70_000)
        );
    }

    @ParameterizedTest
    @MethodSource("getEmployeesSalarySumByDepIDTestParam")
    public void getEmployeesSalarySumByDepIDTest(int depID, int expected) {
        assertThat(departments.getEmployeesSalarySumByDepID(depID))
                .isEqualTo(expected);
    }
}
