package com.teamtreehouse.KyCrime;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
//import java.text.ParseException;
import java.util.ArrayList;

public class CrimeDataReader {
    private ArrayList<CrimeDatum> data = new ArrayList<>();

    public CrimeDataReader(String fileName) {
        System.out.println("Reading crime rate data");
        try {
            Reader in = new FileReader(fileName);
            Iterable<CSVRecord> records =
                    CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
            for (CSVRecord record : records) {
                String county = record.get("county_name");
                String crimeRate = record.get("crime_rate_per_100000");
                String index = record.get("index");
                CrimeDatum crimeIndex = new CrimeDatum(county, crimeRate, index);
                data.add(crimeIndex);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Crime file not found");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("IO Exception");
            System.exit(1);
        } /*catch (ParseException e) {
            e.printStackTrace();
        }*/
    }

    public ArrayList<CrimeDatum> getData() {
        return data;
    }
}
