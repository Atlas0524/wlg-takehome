package org.tolley.wlg.vehicle;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.tolley.wlg.utilities.FileUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VehiclePipeFileParser extends VehicleParser {

    //The csv file the vehicles are listed in
    private File vehicleFile;
    //Logger
    Logger logger;

    /**
     * Constructs the Vehicle CSV File Parser with pipes as separator
     * @param fileName name of file which contains the vehicle CSV
     */
    public VehiclePipeFileParser(String fileName) {
        logger = Logger.getLogger(VehiclePipeFileParser.class.getName());
        logger.addHandler(new ConsoleHandler());
        try {
            vehicleFile = FileUtil.getFileFromResourceDirectory(fileName);
        } catch (URISyntaxException e) {
            logger.log(Level.SEVERE, "Error accessing " + vehicleFile.getName());
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Converts the vehicle file into a list of vehicle objects
     * @return an ArrayList of all parsed Vehicles
     */
    @Override
    public ArrayList<Vehicle> parseVehicles() {

        try {
            CSVParser parser = new CSVParserBuilder().withSeparator('|').build();
            Path myPath = Paths.get(vehicleFile.getAbsolutePath());
            BufferedReader br = Files.newBufferedReader(myPath,  StandardCharsets.UTF_8);
            CSVReader reader = new CSVReaderBuilder(br).withCSVParser(parser).build();
            csvFileLines = reader.readAll();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error accessing " + vehicleFile);
            e.printStackTrace();
            System.exit(1);
        } catch (CsvException e) {
            logger.log(Level.SEVERE, "Error processing CSV list from " + vehicleFile);
            e.printStackTrace();
            System.exit(1);
        }

        //processes the parsed vehicles
        processVehicles();

        return vehiclesList;
    }
}
