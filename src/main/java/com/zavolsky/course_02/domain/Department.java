package com.zavolsky.course_02.domain;

public class Department {
    private String departmentName;
    private static int incID = 0;
    private int ID = ++incID;

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getID() {
        return ID;
    }
}
