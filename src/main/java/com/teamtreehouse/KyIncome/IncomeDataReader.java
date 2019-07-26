package com.teamtreehouse.KyIncome;

//Apache Commons CSV library
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVFormat;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

//Read the income/poverty data from the appropriate data set (csv file)
public class IncomeDataReader {

    //Store IncomeDatum in an ArrayList
    private ArrayList<IncomeDatum> data = new ArrayList<>();

    //Use Apache Commons CSV library to read in data fields
    public IncomeDataReader(String fileName) {
        System.out.println("Reading income data");
        try {
            Reader in = new FileReader(fileName);
            Iterable<CSVRecord> records =
                    CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
            for (CSVRecord record : records) {
                String county = record.get("County").trim();
                String income = record.get("Income").trim();
                String povertyRate = record.get("Poverty").trim();
                IncomeDatum incomeDatum = new IncomeDatum(county, income, povertyRate);
                data.add(incomeDatum);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Income file not found");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("IO Exception");
            System.exit(1);
        }
    }

    //Get method to return income/poverty data
    public ArrayList<IncomeDatum> getData() {
        return data;
    }
}

