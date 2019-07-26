package com.teamtreehouse;

//Open source library Apache Commons CSV for csv file handling
import com.teamtreehouse.KyCrime.CrimeDatum;
import com.teamtreehouse.KyIncome.IncomeDatum;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;

class CrimeIncomeData {
    //Create List variables of type CrimeDatum and IncomeDatum
    private List<CrimeDatum> crimeDatumList;
    private List<IncomeDatum> incomeDatumList;

    //Store user choice in an enumeration
    public enum UserSelection {INCOME, CRIME, COMPARISON};

    //Pass CrimeDatum and IncomeDatum lists to constructor
    public CrimeIncomeData(List<CrimeDatum> crimeDatumList,
                           List<IncomeDatum> incomeDatumList, UserSelection selection) {
        this.crimeDatumList = crimeDatumList;
        this.incomeDatumList = incomeDatumList;

        //Pass user selection to printData method
        this.printData(selection);
    }

    //Call appropriate print method according to user selection
    private void printData(UserSelection selection) {
        if (selection == UserSelection.CRIME) {
            printCrimeData(crimeDatumList);
        }
        else if (selection == UserSelection.INCOME) {
            printIncomeData(incomeDatumList);
        }
        else if (selection == UserSelection.COMPARISON) {
            printComparisonData(crimeDatumList, incomeDatumList);
        }
        else {
            printCrimeData(crimeDatumList);
        }
    }

    //Print counties with crime rate information
    private void printCrimeData(Collection<CrimeDatum> data) {
        System.out.println();
        System.out.println("INDEX___COUNTY___CRIME RATE(/100,000)");
        System.out.println();
        for (var datum : data) {
            double cr = Double.parseDouble(datum.getCrimeRate());
            System.out.println(datum.getIndex() + ". " +
                    datum.getCounty() + " county, KY, " +
                    Math.round(cr));
        }

        //Call write method for crime data
        System.out.println("Writing data");
        writeCrimeData(data);
    }

    //Print counties with income and poverty information
    private void printIncomeData(Collection<IncomeDatum> data) {
        System.out.println();
        System.out.println("COUNTY___AVERAGE INCOME($)___POVERTY RATE(%)");
        System.out.println();
        for (var datum : data) {
            double i = Double.parseDouble(datum.getIncome());
            System.out.println(datum.getCounty() + " county, KY, $" +
                    Math.round(i) + ", %" +
                    datum.getPovertyRate());
        }

        //Call write method for income/poverty
        System.out.println("Writing data");
        writeIncomeData(data);
    }

    //Print counties with a combination of crime and income/poverty data
    //so that the two data sets can be easily compared by county
    private void printComparisonData(Collection<CrimeDatum> crimeData,
                                     Collection<IncomeDatum> incomeData) {
        System.out.println();
        System.out.println("INDEX___COUNTY___CRIME RATE(/100,000)___AVERAGE INCOME($)" +
                "___POVERTY " +
                "RATE(%)");
        System.out.println();

        for (var crimeDatum : crimeData) {

            //For each county in the crime data, look for the matching county in
            //the income/poverty data, and print a combination of data from
            //the two data sets
                    for (var incomeDatum : incomeData) {
                        String a = crimeDatum.getCounty();
                        String b = incomeDatum.getCounty();
                        if (a.equals(b)) {
                            double cr = Double.parseDouble(crimeDatum.getCrimeRate());
                            double i = Double.parseDouble(incomeDatum.getIncome());
                            System.out.println(crimeDatum.getIndex() + ". " + a + " county," +
                                    " " + "KY, " +
                                    Math.round(cr) + ", $" +
                                    Math.round(i) + ", %" +
                                    incomeDatum.getPovertyRate()
                            );
                        }
            }
        }

        //Call appropriate write method
        System.out.println("Writing data");
        writeComparisonData(crimeDatumList, incomeDatumList);
    }

    //Write the crime data using Apache Commons CSV
    private void writeCrimeData(Collection<CrimeDatum> data) {
        try {
            var writer = Files.newBufferedWriter(Paths.get("crimeData.csv"));
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(
                    "index", "county", "crime_rate"));
            for (var datum : data) {
                csvPrinter.printRecord(datum.getIndex(), datum.getCounty(),
                        datum.getCrimeRate());
            }
            csvPrinter.flush();
        } catch (IOException e) {
            System.err.println("Error writing output");
            System.err.println(e.getMessage());
            e.printStackTrace(System.err);
        }
    }

    //Write the income data
    private void writeIncomeData(Collection<IncomeDatum> data) {
        try {
            var writer = Files.newBufferedWriter(Paths.get("incomeData.csv"));
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(
                    "county", "average_income", "poverty_rate"));
            for (var datum : data) {
                csvPrinter.printRecord(datum.getCounty(), datum.getIncome(), datum.getPovertyRate());
            }
            csvPrinter.flush();
        } catch (IOException e) {
            System.err.println("Error writing output");
            System.err.println(e.getMessage());
            e.printStackTrace(System.err);
        }
    }

    //Write the combined version of the two data sets
    private void writeComparisonData(Collection<CrimeDatum> crimeData,
                                     Collection<IncomeDatum> incomeData) {
        try {
            var writer = Files.newBufferedWriter(Paths.get("KyCrimeIncomeReport.csv"));
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(
                    "index", "county", "crime_rate", "average_income", "poverty_rate"));

            for (var crimeDatum : crimeData) {
                for (var incomeDatum : incomeData) {
                    String a = crimeDatum.getCounty();
                    String b = incomeDatum.getCounty();
                    if (a.equals(b)) {
                        double cr = Double.parseDouble(crimeDatum.getCrimeRate());
                        double i = Double.parseDouble(incomeDatum.getIncome());
                        csvPrinter.printRecord(crimeDatum.getIndex(), a, cr, i,
                                incomeDatum.getPovertyRate());
                    }
                }
            }
            csvPrinter.flush();
        } catch (IOException e) {
            System.err.println("Error writing output");
            System.err.println(e.getMessage());
            e.printStackTrace(System.err);
        }

    }
}
