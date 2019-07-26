package com.teamtreehouse.KyIncome;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVFormat;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class IncomeDataReader {
    private ArrayList<IncomeDatum> data = new ArrayList<>();

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

    public ArrayList<IncomeDatum> getData() {
        return data;
    }
}

