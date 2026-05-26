package RailwayReservation.model;

import RailwayReservation.enums.Gender;

public class Children {
    private String name;
    private Gender gender;

    public Children(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

    public Gender getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }
}