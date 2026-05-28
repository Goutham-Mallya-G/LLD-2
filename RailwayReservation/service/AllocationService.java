package service;

import db.DB;
import enums.BerthType;
import model.Berth;
import model.Passenger;
import view.AppViewRailway;

import java.util.*;

public class AllocationService {

    DB db = DB.getDBInstance();
    static int ticketCounter = 1;

    public void bookSeat(Passenger passenger){
        List<Passenger> confirmedList = db.getConfirmedTicket();
        Queue<Passenger> racList = db.getRac();
        if(passenger.getBerthPreference().equals(BerthType.NONE)  && (passenger.getChild() != null || passenger.getAge() >= 60)){
            passenger.setBerthPreference(BerthType.LOWER);
        }
        Berth seat = getBerth(passenger.getBerthPreference());
        if(seat == null){
            seat = getRacBerth();
            if(seat != null){
                List<Passenger> passengers = seat.getPassengers();
                passenger.setTicketNo(ticketCounter++);
                passengers.add(passenger);
                passenger.setBerth(seat);
                if(passengers.size() == 2){
                    seat.changeAvailability();
                }
                racList.add(passenger);
                db.setRac(racList);
                AppViewRailway.printMessage("Ticket booked in RAC in " + seat.getSeatNo());
            }else if(db.getWaitingList().size() < db.getWaitingListLimit()){
                Queue<Passenger> waitingList = db.getWaitingList();
                passenger.setTicketNo(ticketCounter++);
                waitingList.add(passenger);
                db.setWaitingList(waitingList);
                AppViewRailway.printMessage("You are in waitingList " + passenger.getTicketNo());
            }else{
                AppViewRailway.printMessage("No tickets available");
            }
        }else{
            List<Passenger> passengers = seat.getPassengers();
            passenger.setTicketNo(ticketCounter++);
            passengers.add(passenger);
            passenger.setBerth(seat);
            confirmedList.add(passenger);
            db.setConfirmedTicket(confirmedList);
            AppViewRailway.printMessage("Your ticket has booked with seat no " + seat.getSeatNo() +" as "+ seat.getBerthType() + " berth");
        }
    }

    private Berth getRacBerth() {
        List<Berth> sideLower = db.getSideLower();
        for(Berth berth : sideLower){
            if(berth.getPassengers().size() < 2){
                return berth;
            }
        }
        return null;
    }

    private Berth getBerth(BerthType berthPreference) {
        List<Berth> lower = db.getLower();
        List<Berth> middle = db.getMiddle();
        List<Berth> upper = db.getUpper();
        List<Berth> sideUpper = db.getSideUpper();

        if(berthPreference.equals(BerthType.LOWER)){
            for(Berth berth : lower){
                if(berth.getAvailability()){
                    berth.changeAvailability();
                    return berth;
                }
            }
        }else if(berthPreference.equals(BerthType.MIDDLE)){
            for(Berth berth : middle){
                if(berth.getAvailability()){
                    berth.changeAvailability();
                    return berth;
                }
            }
        }
        else if(berthPreference.equals(BerthType.UPPER)){
            for(Berth berth : upper) {
                if (berth.getAvailability()) {
                    berth.changeAvailability();
                    return berth;
                }
            }
        }
        else if(berthPreference.equals(BerthType.SIDE_UPPER)){
            for(Berth berth : sideUpper) {
                if(berth.getAvailability()){
                    berth.changeAvailability();
                    return berth;
                }
            }
        }

        for(Berth berth : sideUpper) {
            if(berth.getAvailability()){
                berth.changeAvailability();
                return berth;
            }
        }
        for(Berth berth : upper) {
            if (berth.getAvailability()) {
                berth.changeAvailability();
                return berth;
            }
        }
        for(Berth berth : middle){
            if(berth.getAvailability()){
                berth.changeAvailability();
                return berth;
            }
        }
        for(Berth berth : lower){
            if(berth.getAvailability()){
                berth.changeAvailability();
                return berth;
            }
        }
        return null;
    }

    public void cancel(int ticketNo) {
        List<Passenger> confirmedList = db.getConfirmedTicket();
        Queue<Passenger> racList = db.getRac();
        Queue<Passenger> waitingList = db.getWaitingList();
        Berth racBerth = null;

        for(Passenger passenger : confirmedList){
            if(passenger.getTicketNo() == ticketNo){
                Berth berth = passenger.getBerth();
                berth.changeAvailability();
                berth.setPassengers(new ArrayList<>());
                confirmedList.remove(passenger);

                if(!racList.isEmpty()){
                    Passenger racPassenger = racList.poll();
                    racBerth = racPassenger.getBerth();
                    List<Passenger> passengerList = racBerth.getPassengers();
                    passengerList.removeFirst();
                    if(racBerth.getPassengers().size() == 1){
                        racBerth.changeAvailability();
                    }
                    racPassenger.setBerth(berth);
                    berth.changeAvailability();
                    confirmedList.add(racPassenger);
                }

                if(!waitingList.isEmpty()){
                    Passenger waitingListPassenger = waitingList.poll();
                    waitingListPassenger.setBerth(racBerth);
                    racList.add(waitingListPassenger);
                }

                System.out.println("Ticket cancelled for " + passenger.getTicketNo());
                return;
            }
        }
        for(Passenger passenger : racList){
            if(passenger.getTicketNo() == ticketNo){
                racBerth = passenger.getBerth();
                List<Passenger> passengerList = racBerth.getPassengers();
                if(racBerth.getPassengers().size() == 2){
                    racBerth.changeAvailability();
                }
                for(int i = 0 ; i < passengerList.size() ; i++){
                    if(passengerList.get(i).getTicketNo() == ticketNo){
                        passengerList.remove(i);
                        System.out.println(passengerList);
                        break;
                    }
                }
                racList = removeQueueElement(passenger , racList);
                db.setRac(racList);
                if(!waitingList.isEmpty()){
                    Passenger waitingListPassenger = waitingList.poll();
                    waitingListPassenger.setBerth(racBerth);
                    racList.add(waitingListPassenger);
                }
                System.out.println("Ticket cancelled for " + passenger.getTicketNo());
                return;
            }
        }
        for(Passenger passenger : waitingList){
            if(passenger.getTicketNo() == ticketNo){
                waitingList = removeQueueElement(passenger,waitingList);
                db.setWaitingList(waitingList);
                System.out.println("Ticket cancelled for " + passenger.getTicketNo());
                break;
            }
        }
    }

    private Queue<Passenger> removeQueueElement(Passenger passenger ,Queue<Passenger> queue){
        Queue<Passenger> updatedQueue = new LinkedList<>();
        for(Passenger passengerInQueue : queue){
            if(!passenger.equals(passengerInQueue)){
                updatedQueue.add(passengerInQueue);
            }
        }
        return updatedQueue;
    }
}
