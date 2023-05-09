package com.zavolsky.course_02.domain;

public class Employee {
    private String name;
    private String fName;
    private int departmentID;
    private double salary;

    public Employee(String name, String fName, int departmentID, double salary) {
        this.name = name;
        this.fName = fName;
        this.departmentID = departmentID;
        this.salary = salary;
    }

    public Employee() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
