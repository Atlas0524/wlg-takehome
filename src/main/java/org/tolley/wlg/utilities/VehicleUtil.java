package org.tolley.wlg.utilities;

import org.tolley.wlg.vehicle.Vehicle;

import java.util.Comparator;

public class VehicleUtil {
    //first name comparator
    public static Comparator<Vehicle> compareByFirstName = Comparator.comparing( Vehicle::getFirstName );

    //last name comparator
    public static Comparator<Vehicle> compareByLastName = Comparator.comparing( Vehicle::getLastName );

    //Compare by last name and then first name (full name compare)
    public static Comparator<Vehicle> compareByFullName = compareByLastName.thenComparing(compareByFirstName);

    //vehicle type comparator
    public static Comparator<Vehicle> compareByVehicleType = Comparator.comparing( Vehicle::getVehicleType, String.CASE_INSENSITIVE_ORDER );

    //vehicle type and full name comparator
    public static Comparator<Vehicle> compareByVehicleTypeAndFullName = compareByVehicleType.thenComparing(compareByFullName);
}
