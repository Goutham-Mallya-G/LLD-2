package service;

import Model.Employee;
import view.AppViewRailway;

import java.util.ArrayList;
import java.util.List;


public class FilteringAndUpdateService {
    PrintingService printingService = new PrintingService();

    public void promoteEmployee(int empId, String designation) {
        Employee emp = null;
        for(Employee employee : printingService.getAllEmployees()){
            if(employee.getId() == empId){
                emp = employee;
            }
        }
        if(emp != null){
            emp.setDesignation(designation);
            AppViewRailway.printMessage("Employee with " + empId + "is promoted to "+ designation);
        }else{
            AppViewRailway.printMessage("No employee found with this ID");
        }
    }

    public void updateEmployee(Employee employee, int option, int num, String str) {
        changeDetails(employee, option , num , str);
    }

    private void changeDetails(Employee employee, int option, int num, String str) {
        switch (option){
            case 1:
                if(str.length() < 2){
                    AppViewRailway.printMessage("Employee name should be more than size 2");
                }else{
                    employee.setName(str);
                }
                break;
            case 2:
                if(num < 18){
                    AppViewRailway.printMessage("No employee is less than 18 years old");
                }else if(num > 99){
                    AppViewRailway.printMessage("No employee is greater than 99");
                }else{
                    employee.setAge(num);
                }
                break;
            case 3:
                employee.setDepartment(str);
                break;
            case 4:
                employee.setDesignation(str);
                break;
            case 5:
                Employee reportingTo = null;
                for(Employee emp : printingService.getAllEmployees()){
                    if(emp.getName().equals(str)){
                        reportingTo = emp;
                        break;
                    }
                }
                employee.setReportingTo(reportingTo);
                break;
        }
    }


    public List<Employee> filter(List<Employee> list, int choice , int option , int num, String str){
        switch(choice){
            case 1,3,4,5:
                return stringFilter(list, option, choice, str);
            case 2:
                return filterByAge(list, option, num);
            default:
                AppViewRailway.printMessage("Please enter the valid option...");
        }
        return null;
    }

    private List<Employee> filterByAge(List<Employee> list, int option ,int num) {
        List<Employee> filteredEmployeeList = new ArrayList<>();
        switch (option){
            case 1:
                for(Employee employee : list){
                    if(employee.getAge() == num){
                        filteredEmployeeList.add(employee);
                    }
                }
                break;
            case 2:
                for(Employee employee : list){
                    if(employee.getAge() != num){
                        filteredEmployeeList.add(employee);
                    }
                }
                break;
            case 3:
                for(Employee employee : list){
                    if(employee.getAge() > num){
                        filteredEmployeeList.add(employee);
                    }
                }
                break;
            case 4:
                for(Employee employee : list){
                    if(employee.getAge() < num){
                        filteredEmployeeList.add(employee);
                    }
                }
                break;
        }
        return filteredEmployeeList;
    }

    public String helper(Employee emp, int choice){
        switch (choice){
            case 1:
                return emp.getName();
            case 3:
                return emp.getDepartment();
            case 4:
                return emp.getDesignation();
            case 5:
                if(emp.getReportingTo() != null){
                    return emp.getReportingTo().getName();
                }
                return "-";
        }
        return "";
    }

    private List<Employee> stringFilter(List<Employee> list, int option, int choice, String str) {
        List<Employee> filteredEmployeeList = new ArrayList<>();
        switch (option){
            case 1:
                for(Employee employee : list){
                    String s = helper(employee , choice);
                    if(s.equals(str)){
                        filteredEmployeeList.add(employee);
                    }
                }
                break;
            case 2:
                for(Employee employee : list){
                    String s = helper(employee , choice);
                    if(!s.equals(str)){
                        filteredEmployeeList.add(employee);
                    }
                }
                break;
            case 3:
                for(Employee employee : list){
                    String s = helper(employee , choice);
                    if(s.contains(str)){
                        filteredEmployeeList.add(employee);
                    }
                }
                break;
            case 4:
                for(Employee employee : list){
                    String s = helper(employee , choice);
                    if(!s.contains(str)){
                        filteredEmployeeList.add(employee);
                    }
                }
                break;
            case 5:
                for(Employee employee : list){
                    String s = helper(employee , choice);
                    if(s.startsWith(str)){
                        filteredEmployeeList.add(employee);
                    }
                }
                break;
            case 6:
                for(Employee employee : list){
                    String s = helper(employee , choice);
                    if(s.endsWith(str)){
                        filteredEmployeeList.add(employee);
                    }
                }
                break;
        }
        return filteredEmployeeList;
    }
}
