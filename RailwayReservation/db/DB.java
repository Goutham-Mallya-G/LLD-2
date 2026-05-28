package db;

import enums.BerthType;
import model.Berth;
import model.Passenger;

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


    public void generateBerths(){
        for (int i = 0; i < 9; i++) {
            int base = i * 8;

            lower.add(new Berth(base + 1, BerthType.LOWER));
            middle.add(new Berth(base + 2, BerthType.MIDDLE));
            upper.add(new Berth(base + 3, BerthType.UPPER));

            lower.add(new Berth(base + 4, BerthType.LOWER));
            middle.add(new Berth(base + 5, BerthType.MIDDLE));
            upper.add(new Berth(base + 6, BerthType.UPPER));

            sideLower.add(new Berth(base + 7, BerthType.SIDE_LOWER));
            sideUpper.add(new Berth(base + 8, BerthType.SIDE_UPPER));
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

    public int getWaitingListLimit() {
        return 10;
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
