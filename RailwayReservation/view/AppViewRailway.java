package view;

import enums.BerthType;
import enums.Gender;
import model.Children;
import model.Passenger;
import service.AllocationService;
import service.ListPrinting;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class AppViewRailway {
    Scanner sc = new Scanner(System.in);
    AllocationService allocationService = new AllocationService();
    ListPrinting listPrinting = new ListPrinting();
    public void start() {
        boolean loop = true;
        while(loop){
            printDashBoard();
            int option = getIntInput("Enter the option : ");
            switch (option){
                case 1:
                    printBooking();
                    break;
                case 2:
                    printCancelling();
                    break;
                case 3:
                    printBookedTickets();
                    break;
                case 4:
                    printAvailableTickets();
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Enter the correct input");
            }
        }
    }

    private void printCancelling() {
        int seatNo = getIntInput("Enter the Ticket no to cancel : ");
        allocationService.cancel(seatNo);
    }

    private void printBookedTickets() {
        listPrinting.printBookedTickets();
    }

    private void printAvailableTickets(){
        listPrinting.printAvailableTickets();
    }

    private void printBooking() {
        String name = getStringInput("Enter the name of the passenger : ");
        int age = getIntInput("Enter the age of the passenger : ");
        Gender gender = getGenderInput("Enter the gender of the passenger : ");
        BerthType berthPreference = getBerthPreferenceInput("Enter the preferred berth : ");

        Children child = null;
        if(gender.equals(Gender.FEMALE)){
            boolean hasChild = getBooleanInput("Do you have children under 5 ? (Y/N) : ");
            if(hasChild){
                String childName = getStringInput("Enter the name of the child : ");
                Gender childGender = getGenderInput("Enter the gender of the child : ");

                child = new Children(childName, childGender);
            }
        }
        Passenger passenger = new Passenger(name, age, gender, berthPreference, child);
        allocationService.bookSeat(passenger);
    }

    //scanners
    private boolean getBooleanInput(String message) {
        System.out.print(message);
        String val = "";
        while(true) {
            try {
                val = sc.nextLine().toUpperCase();
                if (val.equals("Y") || val.equals("YES")) {
                    return true;
                } else if (val.equals("N") || val.equals("NO")) {
                    return false;
                }else{
                    System.out.println("Enter Yes or No");
                }
            } catch (NoSuchElementException | IllegalStateException e) {
                val = "";
                System.out.println("Error in boolean : " + e.getMessage());
            }
        }
    }


    public int getIntInput(String message){
        int num = -1;
        while(true){
            try {
                System.out.print(message);
                num = sc.nextInt();
                return num;
            }catch(NoSuchElementException | IllegalStateException e){
                sc.nextLine();
                System.out.println("Error in getting int input : " + e.getMessage());
            }
        }
    }

    public String getStringInput(String message){
        System.out.print(message);
        String str = null;
        try {
            str = sc.nextLine();
        }catch (NoSuchElementException | IllegalStateException e){
            sc.nextLine();
            System.out.println("Error in getting String input : " + e.getMessage());
        }
        return str;
    }

    public Gender getGenderInput(String message){
        Gender gender = null;
        System.out.println();
        System.out.println("options");
        System.out.println("--------------");
        System.out.println("Male");
        System.out.println("Female");
        while(true){
            System.out.print(message);
            String genderStr = sc.nextLine();
            try{
                gender = Gender.valueOf(genderStr.trim().toUpperCase());
                return gender;
            }catch (IllegalArgumentException e){
                System.out.println("Enter the correct value for gender");
            }
        }
    }

    public BerthType getBerthPreferenceInput(String message){
        BerthType berthPreference= null;
        System.out.println("options");
        System.out.println("--------------");
        System.out.println("Lower");
        System.out.println("Middle");
        System.out.println("Upper");
        System.out.println("Side upper");
        System.out.println("None");
        while(true){
            System.out.print(message);
            String berthPreferenceStr = sc.nextLine();
            try{
                berthPreference = BerthType.valueOf(berthPreferenceStr.trim().toUpperCase().replace(" ", "_"));
                return berthPreference;
            }catch (IllegalArgumentException e){
                System.out.println("Please enter the correct seat type");
            }
        }
    }

    // menus
    private void printDashBoard() {
        System.out.println("--------------------------------------------------");
        System.out.println("1.Book Ticket");
        System.out.println("2.Cancel Ticket");
        System.out.println("3.Print Booked Ticket");
        System.out.println("4.Print Available Ticket");
    }

    //printer
    public static void printMessage(String message){
        System.out.println(message);
    }
}