package com.example.order_delivery.model;

import com.parse.Parse;
import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
@ParseClassName("Employee")
public class Employee extends ParseObject {
    public static final String KEY_NAME = "username";
    public static final String KEY_EMPLOYEEID = "employeeId";
    public static final String KEY_TITLE = "title";
    public static final String KEY_SALARY = "salary";
    public static final String KEY_RATING = "rating";
    public static final String KEY_WARNING = "warning";

    public int getWarning(){
        return getNumber(KEY_WARNING).intValue();
    }

    public void setWarning(int warning){
        put(KEY_WARNING, warning);
    }

    public String getName() {
        return getString(KEY_NAME);
    }
    public String getEmployId() {
        return getString(KEY_EMPLOYEEID);
    }
    public String getEmployTitle() {
        return getString(KEY_TITLE);
    }
    public double getSalary() {return getNumber(KEY_SALARY).doubleValue();}
    public double getRating() {
        return getNumber(KEY_RATING).doubleValue();
    }
}
