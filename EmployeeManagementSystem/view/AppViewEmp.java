package view;

import Model.Employee;
import service.FilteringAndUpdateService;
import service.PrintingService;

import java.util.List;
import java.util.Scanner;

public class AppViewEmp {
    Scanner sc = new Scanner(System.in);
    PrintingService printingService = new PrintingService();
    FilteringAndUpdateService filteringAndUpdateService = new FilteringAndUpdateService();

    public void start() {
        while(true){
            printDashBoard();
            int choice = getIntInput("Enter your choice : ");
            switch(choice){
                case 1:
                    List<Employee> list = printingService.getAllEmployees();
                    printAllEmployees(list);
                    break;
                case 2:
                    printFilterAndUpdateEmployee(printingService.getAllEmployees());
                    break;
                case 3:
                    printManagerOperations();
                    break;
                case 4:
                    System.out.println("Thank you for using our app");
                    System.exit(0);
                default:
                    System.out.println("Please enter the valid option...");
            }
        }
    }

    //Operations
    private void printManagerOperations() {
        boolean loop = true;
        while (loop){
            System.out.println("1.Print employees under manager");
            System.out.println("2.Promote Employee");
            int choice = getIntInput("Enter the option : ");
            if(choice == 1){
                String managerName = getStringInput("Enter the manager name : ");
                printingService.printEmployeesUnderManager(managerName);
            }else{
                int empId = getIntInput("Enter the Id of the employee : ");
                String designation = getStringInput("Enter the designation : ");
                filteringAndUpdateService.promoteEmployee(empId, designation);
            }
        }
    }

    private void printFilterAndUpdateEmployee(List<Employee> allEmployees) {
        boolean willContinue = true;
        while(willContinue){
            System.out.println("Filter by");
            System.out.println("----------------");
            System.out.println("1.Name");
            System.out.println("2.Age");
            System.out.println("3.Department");
            System.out.println("4.Designation");
            System.out.println("5.Reporting to");
            int option = -1;
            int num = -1;
            String str = "";
            boolean loop = true;
            while(loop){
                int choice = getIntInput("Enter your option : ");
                if(choice == 2){
                    System.out.println("1.Equal");
                    System.out.println("2.Not Equal");
                    System.out.println("3.Greater than");
                    System.out.println("4.Lesser than");
                    option = getIntInput("Enter your option : ");
                    num = getIntInput("Enter the age : ");
                    loop = false;
                }else if(choice == 1 || choice == 3 || choice == 4 || choice == 5){
                    System.out.println("1.Equal");
                    System.out.println("2.Not Equal");
                    System.out.println("3.Contains");
                    System.out.println("4.Not Contains");
                    System.out.println("5.Starting with");
                    System.out.println("6.Ending with");
                    option = getIntInput("Enter your option : ");
                    str = getStringInput("Enter the string : ");
                    loop = false;
                }else{
                    System.out.println("Enter the valid option...");
                    break;
                }
                List<Employee> listOfFilteredEmployee = filteringAndUpdateService.filter(allEmployees , choice , option , num , str);
                printAllEmployees(listOfFilteredEmployee);
                allEmployees = listOfFilteredEmployee;
                willContinue = getBooleanInput("Do you want to continue Filtering ? (Y/N) : " );
                if(allEmployees.isEmpty()) willContinue = false;
            }
        }
        boolean willUpdate = getBooleanInput("Do you want to update details of employee ? (Y/N) : ");
        if(willUpdate){
            int empID = getIntInput("Enter the employee ID : ");
            Employee emp = null;
            for(Employee employee : printingService.getAllEmployees()){
                if(employee.getId() == empID){
                    emp = employee;
                }
            }
            if(emp == null){
                System.out.println("There are no Employee with this ID");
            }else{
                System.out.println("1.Name");
                System.out.println("2.Age");
                System.out.println("3.Department");
                System.out.println("4.Designation");
                System.out.println("5.Reporting To");
                int num = -1;
                String str = "";
                boolean loop = true;
                while(loop) {
                    int option = getIntInput("Enter the option : ");
                    if (option == 2) {
                        num = getIntInput("Enter the age : ");
                    } else if (option == 1 || option == 3 || option == 4 || option == 5) {
                        str = getStringInput("Enter the string : ");
                    }else{
                        System.out.println("Enter the correct option...");
                }
                    filteringAndUpdateService.updateEmployee(emp , option,num , str );
                    System.out.println(emp.getId() + " emp data updated");
                    loop = getBooleanInput("Do you want to update ? (y/n) : ");
                }
            }
        }
    }


    private void printAllEmployees(List<Employee> list) {
        System.out.printf("%-5s | %-25s | %-5s | %-25s | %-25s | %-25s" , "ID", "Name", "Age", "Department", "Designation", "Reporting to");
        System.out.println();
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        for(Employee emp : list){
            String reportingTo = "-";
            if(emp.getReportingTo() != null) {
                reportingTo = emp.getReportingTo().getName();
            }
            System.out.printf("%-5s | %-25s | %-5s | %-25s | %-25s | %-25s" , emp.getId(), emp.getName(), emp.getAge(), emp.getDepartment(), emp.getDesignation(), reportingTo);
            System.out.println();
        }
    }

    //Getters
    private int getIntInput(String s) {
        int num = -1;
        while(true){
            try{
                System.out.print(s);
                num = sc.nextInt();
                return num;
            }catch (RuntimeException e){
                sc.nextLine();
                System.out.println("Try again");
            }
        }
    }

    private String getStringInput(String s) {
        String str = "";
        while(true){
            try{
                sc.nextLine();
                System.out.print(s);
                str = sc.nextLine();
                return str;
            }catch (RuntimeException e){
                sc.nextLine();
                System.out.println("Error : " + e.getMessage());
                System.out.println("Try again");
            }
        }
    }

    private boolean getBooleanInput(String s) {
        String val = "";
        while(true){
            try{
                System.out.print(s);
                val = sc.nextLine().trim().toUpperCase();
                if(val.equals("Y") || val.equals("YES")){
                    return true;
                }else if(val.equals("N") || val.equals("NO")){
                    return false;
                }
            }catch (RuntimeException e){
                sc.nextLine();
                System.out.println("Error : " + e.getMessage());
                System.out.println("Try again");
            }
        }
    }

    //printers
    private void printDashBoard() {
        System.out.println("------------------------------");
        System.out.println("1.Dispaly all Employees");
        System.out.println("2.Filter and Update Employees");
        System.out.println("3.Manager Operations");
        System.out.println("4.Exit");
    }
    public static void printMessage(String message) {
        System.out.println(message);
    }
}
