package model;

import enums.BerthType;

import java.util.ArrayList;

public class Berth {
    private int seatNo;
    private BerthType berthType;
    private boolean availability;
    private ArrayList<Passenger> passengers;

    public Berth(int seatNo, BerthType berthType){
        this.seatNo = seatNo;
        this.berthType = berthType;
        availability = true;
        passengers = new ArrayList<>();
    }

    public void changeAvailability(){
        availability = !availability;
    }

    public int getSeatNo() {
        return seatNo;
    }

    public BerthType getBerthType() {
        return berthType;
    }

    public boolean getAvailability() {
        return availability;
    }

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(ArrayList<Passenger> passengers) {
        this.passengers = passengers;
    }
}
