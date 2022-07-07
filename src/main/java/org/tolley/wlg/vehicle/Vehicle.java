package org.tolley.wlg.vehicle;

import java.util.Comparator;

public class Vehicle {
    //first name of customer
    private String firstName;
    //last name of customer
    private String lastName;
    //customer's email address
    private String email;
    //type of vehicle
    private String vehicleType;
    //name of the vehicle
    private String vehicleName;
    //vehicle length
    private String vehicleLength;
    //full name
    private String fullName;

    /**
     * Constructor for the Vehicle object
     * @param firstName
     * @param lastName
     * @param email
     * @param vehicleType
     * @param vehicleName
     * @param vehicleLength
     */
    public Vehicle(String firstName, String lastName, String email, String vehicleType, String vehicleName, String vehicleLength){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.vehicleType = vehicleType;
        this.vehicleName = vehicleName;
        this.vehicleLength = vehicleLength;
        this.fullName = firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public String getVehicleLength() {
        return vehicleLength;
    }

    public String getFullName() {
        return fullName;
    }

    /**
     * Custom toString method for Vehicles
     * @return String which displays the information in the following way:
     * Full name, Email,Vehicle type, Vehicle name, Vehicle length
     */
    @Override
    public String toString() {
        String result = getFullName() + "," + getEmail() + "," + getVehicleType() + "," +
                getVehicleName() + "," + getVehicleLength();
        return result;
    }
}
