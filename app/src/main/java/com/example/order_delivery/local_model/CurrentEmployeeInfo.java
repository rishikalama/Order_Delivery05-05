package com.example.order_delivery.local_model;

import com.example.order_delivery.model.Employee;

public class CurrentEmployeeInfo {
    public static Employee employee;
    public static String currentEmployeeName;
    public static String currentEmployeeId;
    public static String currentEmployeeTitle;
    public static double currentEmployeeSalary;
    public static double currentEmployeeRating;
    public static int currentEmployeeWarning;

    public CurrentEmployeeInfo(Employee employee){
        this.employee = employee;
        this.currentEmployeeId = employee.getEmployId();
        this.currentEmployeeWarning = employee.getWarning();
        this.currentEmployeeName = employee.getName();
        this.currentEmployeeTitle = employee.getEmployTitle();
        this.currentEmployeeSalary = employee.getSalary();
        this.currentEmployeeRating = employee.getRating();
    }
}
