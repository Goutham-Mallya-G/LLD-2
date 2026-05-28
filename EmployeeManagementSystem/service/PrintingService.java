package service;

import Model.Employee;
import db.EmpDB;

import java.util.ArrayList;
import java.util.List;

public class PrintingService {
    EmpDB db = EmpDB.getDBInstance();

    public List<Employee> getAllEmployees(){
        db.generateEmployeeList();
        return db.getEmployeeList();
    }

    public List<Employee> printEmployeesUnderManager(String managerName) {
        List<Employee> employeeList = new ArrayList<>();
        for(Employee employee : getAllEmployees()){
            Employee emp = employee.getReportingTo();
            if(emp != null && emp.getName().equals(managerName)){
                employeeList.add(employee);
            }
        }
        return employeeList;
    }
}
