package org.tolley.wlg.vehicleprocessor;

import org.apache.commons.cli.*;
import org.tolley.wlg.utilities.VehicleUtil;
import org.tolley.wlg.vehicle.Vehicle;
import org.tolley.wlg.vehicle.VehicleCSVFileParser;
import org.tolley.wlg.vehicle.VehicleParser;
import org.tolley.wlg.vehicle.VehiclePipeFileParser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VehicleProcessor {

    /**
     * Entrypoint for the VehicleProcessor app
     * @param args -file -sort
     */
    public static void main(String[] args) {
        String fileName = "";
        String sortMethod = "";
        List<Vehicle> sortedVehicles;
        VehicleParser vehicleParser = null;
        Options options = new Options();
        Option file = Option.builder("file")
                .argName("file")
                .hasArg()
                .desc("use given file")
                .build();
        Option sort = Option.builder("sort")
                .argName("sort")
                .hasArg()
                .desc("sorts by supplied way. -sort full_name by full name, -sort vehicle_length by vehicle length")
                .build();
        options.addOption(file);
        options.addOption(sort);
        // create the cli parser
        CommandLineParser parser = new DefaultParser();
        try {
            // parse the command line arguments
            CommandLine line = parser.parse(options, args);
            if(line.hasOption("file")) {
                // initialise the file variable
                fileName = line.getOptionValue("file");
            }else{
                throw new IllegalArgumentException("Error: The file must be specified with the argument -file file_name");
            }
            if(line.hasOption("sort")) {
                // sets how to sort the parsed data
                sortMethod = line.getOptionValue("sort");
            }else{
                throw new IllegalArgumentException("Error: The sort method must be specified with the argument -sort sort_name");
            }
        }
        catch (ParseException exp) {
            // oops, something went wrong
            System.err.println("Parsing failed.  Reason: " + exp.getMessage());
            System.exit(1);
        }
        
        switch (fileName){
            case "commas.txt":
                vehicleParser = new VehicleCSVFileParser(fileName);
                break;
            case "pipes.txt": 
                vehicleParser = new VehiclePipeFileParser(fileName);
                break;
            default: {
                throw new IllegalArgumentException("Invalid file given: " + fileName);
            }
        }
        ArrayList<Vehicle> vehicles = vehicleParser.parseVehicles();
        switch (sortMethod){
            case "full_name": {
                sortedVehicles = vehicles.stream()
                        .sorted(VehicleUtil.compareByFullName)
                        .collect(Collectors.toList());
                break;
            }
            case "vehicle_type": {
                sortedVehicles = vehicles.stream()
                        .sorted(VehicleUtil.compareByVehicleTypeAndFullName)
                        .collect(Collectors.toList());
                break;
            }
            default: {
                throw new IllegalArgumentException("Invalid file given: " + fileName);
            }
        }
        System.out.println("Sorted: " + fileName + " " + "By: " + sortMethod);
        for(Vehicle vehicle: sortedVehicles){
            System.out.println(vehicle.toString());
        }
    }

}

