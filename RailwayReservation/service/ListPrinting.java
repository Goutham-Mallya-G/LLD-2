package service;

import db.DB;
import model.Berth;
import model.Passenger;import view.AppViewRailway;

import java.util.List;
import java.util.Queue;
import java.util.TreeMap;

public class ListPrinting {
    DB db = DB.getDBInstance();

    public void printBookedTickets() {
        List<Passenger> confirmedList = db.getConfirmedTicket();
        System.out.printf("%-10s | %-10s | %-10s | %-10s | %-10s | %-15s | %-10s" ,"Ticket No", "Name", "Gender", "Age", "Seat No" , "Allocated Berth", "Preferred Berth");
        AppViewRailway.printMessage(" ");
        AppViewRailway.printMessage("-----------------------------------------------------------------------------------------------------");
        for(Passenger passenger : confirmedList){
            System.out.printf("%-10s | %-10s | %-10s | %-10s | %-10s | %-15s | %-10s",passenger.getTicketNo(), passenger.getName() , passenger.getGender() , passenger.getAge() , passenger.getBerth().getSeatNo() , passenger.getBerth().getBerthType() , passenger.getBerthPreference());
            AppViewRailway.printMessage(" ");
            if(passenger.getChild() != null){
                System.out.printf("%-10s | %-10s | %-10s" ,"", passenger.getChild().getName(), passenger.getChild().getGender());
                AppViewRailway.printMessage(" ");
            }
        }
        Queue<Passenger> racList = db.getRac();
        for(Passenger passenger : racList){
            System.out.printf("%-10s  | %-10s | %-10s | %-10s | %-10s | %-15s | %-10s",passenger.getTicketNo(), passenger.getName() , passenger.getGender() , passenger.getAge() , passenger.getBerth().getSeatNo() , passenger.getBerth().getBerthType() , passenger.getBerthPreference());
            AppViewRailway.printMessage(" ");
            if(passenger.getChild() != null){
                System.out.printf("%-10s  | %-10s | %-10s" ,"", passenger.getChild().getName(), passenger.getChild().getGender());
                AppViewRailway.printMessage(" ");
            }
        }
        AppViewRailway.printMessage(" ");
        AppViewRailway.printMessage("Tickets filled : " + confirmedList.size());
        AppViewRailway.printMessage("RAC filled : " + racList.size());
    }

    public void printAvailableTickets(){
        List<Berth> upper = db.getUpper();
        List<Berth> middle = db.getMiddle();
        List<Berth> lower = db.getLower();
        List<Berth> sideUpper = db.getSideUpper();
        List<Berth> sideLower = db.getSideLower();
        int upperCount = 0;
        int middleCount = 0;
        int lowerCount = 0;
        int sideUpperCount = 0;
        int sideLowerCount = 0;

        TreeMap<Integer , Berth> map = new TreeMap<>();
        for(Berth berth : upper){
            if(berth.getAvailability()){
                map.put(berth.getSeatNo(), berth);
                upperCount++;
            }
        }
        for(Berth berth : middle){
            if(berth.getAvailability()){
                map.put(berth.getSeatNo(), berth);
                middleCount++;
            }
        }
        for(Berth berth : lower){
            if(berth.getAvailability()){
                map.put(berth.getSeatNo(), berth);
                lowerCount++;
            }
        }
        for(Berth berth : sideUpper){
            if(berth.getAvailability()){
                map.put(berth.getSeatNo(), berth);
                sideUpperCount++;
            }
        }
        for(Berth berth : sideLower){
            if(berth.getAvailability()){
                map.put(berth.getSeatNo(), berth);
                sideLowerCount++;
            }
        }
        System.out.printf("%-10s | %-10s" , "Seat No" , "Berth Type");
        AppViewRailway.printMessage(" ");
        AppViewRailway.printMessage("-----------------------------------------");
        for(Berth berth : map.values()){
            System.out.printf("%-10s | %-10s" , berth.getSeatNo() , berth.getBerthType());
            AppViewRailway.printMessage(" ");
        }
        AppViewRailway.printMessage(" ");

        AppViewRailway.printMessage("Available upper seat : " + upperCount);
        AppViewRailway.printMessage("Available middle seat : " + middleCount);
        AppViewRailway.printMessage("Available lower seat : " + lowerCount);
        AppViewRailway.printMessage("Available side upper seat : " + sideUpperCount);
        AppViewRailway.printMessage("Available side lower seat : " + sideLowerCount);
    }
}
