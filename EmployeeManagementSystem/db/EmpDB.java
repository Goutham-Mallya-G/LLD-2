package db;

import Model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmpDB {

    static EmpDB db;

    private EmpDB() {};

    Employee emp1 = new Employee(1,"Sriram",45,"Management","CEO",null);
    Employee emp2 = new Employee(2,"Mukund",42,"HR","HR Manager",emp1);
    Employee emp3 = new Employee(3,"Sebastian",38,"Finance","Finance Manager",emp1);
    Employee emp4 = new Employee(4,"Aashritha",32,"Product Management","Dev Manager",emp1);
    Employee emp5 = new Employee(5,"Mohammad Rafi",35,"HR","HR Lead",emp2);
    Employee emp6 = new Employee(6,"Anjali Kumar",29,"HR","HR Associate",emp5);
    Employee emp7 = new Employee(7,"Joseph",40,"Finance","Finance Associate",emp3);
    Employee emp8 = new Employee(8,"Ramachandran",27,"Product Development","Team Lead",emp4);
    Employee emp9 = new Employee(9,"Abhinaya Shankar",23,"Product Development","System Developer",emp8);
    Employee emp10 = new Employee(10,"Imran Khan",28,"Product Testing","QA Lead",emp8);

    List<Employee> employeeList = new ArrayList<>();


    public void generateEmployeeList() {
        employeeList = new ArrayList<>();
        employeeList.add(emp1);
        employeeList.add(emp2);
        employeeList.add(emp3);
        employeeList.add(emp4);
        employeeList.add(emp5);
        employeeList.add(emp6);
        employeeList.add(emp7);
        employeeList.add(emp8);
        employeeList.add(emp9);
        employeeList.add(emp10);
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public static EmpDB getDBInstance(){
        if(db == null){
            db = new EmpDB();
        }
        return db;
    }

}
