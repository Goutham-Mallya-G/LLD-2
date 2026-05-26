package RailwayReservation.model;

import RailwayReservation.enums.BerthType;
import RailwayReservation.enums.Gender;

public class Passenger {
    private final String name;
    private final int age;
    private final Gender gender;
    private BerthType berthPreference;
    private Berth berth;
    private final Children child;
    private int ticketNo;

    public Passenger(String name, int age, Gender gender, BerthType berthPreference, Children child) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.berthPreference = berthPreference;
        this.child = child;
    }

    public int getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(int ticketNo) {
        this.ticketNo = ticketNo;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public BerthType getBerthPreference() {
        return berthPreference;
    }

    public Berth getBerth() {
        return berth;
    }

    public Children getChild() {
        return child;
    }

    public void setBerthPreference(BerthType berthPreference) {
        this.berthPreference = berthPreference;
    }

    public void setBerth(Berth berth) {
        this.berth = berth;
    }
}
