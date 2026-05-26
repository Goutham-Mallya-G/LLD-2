package RailwayReservation.db;

import RailwayReservation.enums.BerthType;
import RailwayReservation.model.Berth;
import RailwayReservation.model.Passenger;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DB {

    public static DB db;

    private DB() {}

    List<Berth> lower = new ArrayList<>();
    List<Berth> middle = new ArrayList<>();
    List<Berth> upper = new ArrayList<>();
    List<Berth> sideLower = new ArrayList<>();
    List<Berth> sideUpper = new ArrayList<>();

    List<Passenger> confirmedTicket = new ArrayList<>();
    Queue<Passenger> rac = new LinkedList<>();
    Queue<Passenger> waitingList = new LinkedList<>();

    //seat numbers
    private int lowerSeatNo = 1;
    private int middleSeatNo = 2;
    private int upperSeatNo = 3;
    private int sideLowerSeatNo = 4;
    private int sideUpperSeatNo = 5;

    //seat Limits
    private final int lowerSeatLimit = 0;
    private final int middleSeatLimit = 0;
    private final int upperSeatLimit = 0;
    private final int sideLowerSeatLimit = 1;
    private final int sideUpperSeatLimit = 1;
    private final int waitingListLimit = 1;

    public void generateBerths(){
        for(int i = 0 ; i < lowerSeatLimit; i++){
            lower.add(new Berth(lowerSeatNo, BerthType.LOWER));
            if(lowerSeatNo % 2 == 0){
                lowerSeatNo += 3;
            }else {
                lowerSeatNo += 5;
            }
        }
        for(int i = 0 ; i < middleSeatLimit ; i++){
            middle.add(new Berth(middleSeatNo, BerthType.MIDDLE));
            if(middleSeatNo % 2 == 0){
                middleSeatNo += 3;
            }else {
                middleSeatNo += 5;
            }
        }
        for(int i = 0 ; i < upperSeatLimit ; i++){
            upper.add(new Berth(upperSeatNo, BerthType.UPPER));
            if(upperSeatNo % 2 == 0){
                upperSeatNo += 3;
            }else {
                upperSeatNo += 5;
            }
        }
        for(int i = 0 ; i < sideLowerSeatLimit ; i++){
            sideLower.add(new Berth(sideLowerSeatNo, BerthType.SIDE_LOWER));
            sideLowerSeatNo += 7;
        }
        for(int i = 0 ; i < sideUpperSeatLimit ; i++){
            sideUpper.add(new Berth(sideUpperSeatNo, BerthType.SIDE_UPPER));
            sideUpperSeatNo += 7;
        }

    }
    public List<Berth> getMiddle() {
        return middle;
    }

    public List<Berth> getLower() {
        return lower;
    }

    public List<Berth> getUpper() {
        return upper;
    }

    public List<Berth> getSideLower() {
        return sideLower;
    }

    public List<Berth> getSideUpper() {
        return sideUpper;
    }

    public List<Passenger> getConfirmedTicket() {
        return confirmedTicket;
    }

    public Queue<Passenger> getRac() {
        return rac;
    }

    public Queue<Passenger> getWaitingList() {
        return waitingList;
    }

    public int getLowerSeatLimit() {
        return lowerSeatLimit;
    }

    public int getMiddleSeatLimit() {
        return middleSeatLimit;
    }

    public int getUpperSeatLimit() {
        return upperSeatLimit;
    }

    public int getSideLowerSeatLimit() {
        return sideLowerSeatLimit;
    }

    public int getSideUpperSeatLimit() {
        return sideUpperSeatLimit;
    }

    public int getWaitingListLimit() {
        return waitingListLimit;
    }

    public static DB getDBInstance(){
        if(db == null){
            db = new DB();
            db.generateBerths();
        }
        return db;
    }

    public void setRac(Queue<Passenger> rac) {
        this.rac = rac;
    }

    public void setConfirmedTicket(List<Passenger> confirmedTicket) {
        this.confirmedTicket = confirmedTicket;
    }

    public void setWaitingList(Queue<Passenger> waitingList) {
        this.waitingList = waitingList;
    }
}
