package com.zavolsky.course_02.services;

import com.zavolsky.course_02.domain.Employee;
import com.zavolsky.course_02.exceptions.BadRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EmployeeImplTest {

    private final EmployeeImpl employees = new EmployeeImpl();

    @BeforeEach
    public void beforeEach() {
        employees.add("Name", "FName", 1, 10_000);
        employees.add("Robo", "Model", 2, 20_000);
    }

    public static Stream<Arguments> negativeNameTestParams() {
        return Stream.of(
                Arguments.of("Name1"),
                Arguments.of("Name@"),
                Arguments.of("Name?")
        );
    }

    public static Stream<Arguments> negativeFNameTestParams() {
        return Stream.of(
                Arguments.of("FName1"),
                Arguments.of("FName@"),
                Arguments.of("FName?")
        );
    }

    @Test
    public void addTest() {
        Employee expected = new Employee("Chat", "GPT", 3, 30_000);
        int beforeCount = employees.getAll().size();

        Employee actual = employees.add("Chat", "GPT", 3, 30_000);
        assertThat(actual)
                .usingRecursiveComparison()
                .comparingOnlyFields("name", "fname", "department", "salary")
                .isEqualTo(expected)
                .isIn(employees.getAll());

        assertThat(employees.getAll().size())
                .isEqualTo(beforeCount + 1);
    }

    @Test
    public void removeTest() {
        Employee expected = new Employee("Name", "FName", 1, 10_000);

        int beforeCount = employees.getAll().size();

        Employee actual = employees.remove("Name", "FName");
        assertThat(actual)
                .usingRecursiveComparison()
                .comparingOnlyFields("name", "fname", "department", "salary")
                .isEqualTo(expected)
                .isNotIn(employees.getAll());

        assertThat(employees.getAll().size())
                .isEqualTo(beforeCount - 1);
    }

    @Test
    public void removeNegativeTest() {
        assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> employees.remove("Name", "OtherFName"));
    }

    @Test
    public void findTest() {
        Employee expected = new Employee("Name", "FName", 1, 10_000);

        int beforeCount = employees.getAll().size();

        Employee actual = employees.find("Name", "FName");
        assertThat(actual)
                .usingRecursiveComparison()
                .comparingOnlyFields("name", "fname", "department", "salary")
                .isEqualTo(expected)
                .isIn(employees.getAll());

        assertThat(employees.getAll().size())
                .isEqualTo(beforeCount);
    }

    @Test
    public void findNegativeTest() {
        assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> employees.find("Name", "OtherFName"));
    }

    @Test
    public void getAllTest() {
        assertThat(employees.getAll())
                .usingRecursiveComparison()
                .comparingOnlyFields("name", "fname", "department", "salary")
                .isEqualTo(
                        List.of(
                                new Employee("Name", "FName", 1, 10_000),
                                new Employee("Robo", "Model", 2, 20_000)
                        )
                );
    }

    @ParameterizedTest
    @MethodSource("negativeNameTestParams")
    public void negativeNameTest(String incorrectName) {
        assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> employees.add(incorrectName, "Ivanov", 3, 13_000));
    }

    @ParameterizedTest
    @MethodSource("negativeFNameTestParams")
    public void negativeFNameTestParams(String incorrectFName) {
        Assertions.assertThrows(BadRequestException.class,
                () -> employees.add("Ivan", incorrectFName, 3, 13_000)
        );
    }
}
