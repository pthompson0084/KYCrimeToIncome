package com.teamtreehouse;

import com.teamtreehouse.KyCrime.CrimeDataReader;
import com.teamtreehouse.KyCrime.CrimeDatum;
import com.teamtreehouse.KyIncome.IncomeDataReader;
import com.teamtreehouse.KyIncome.IncomeDatum;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
        System.out.println();

        //A title
        System.out.println("KENTUCKY COUNTY CRIME RATES COMPARED TO COUNTY AVERAGE INCOME " +
                "AND POVERTY RATES");
        System.out.println("_______________________________________________");
        System.out.println();

        //Variables to hold the data read from the two data sets and get user selection
        List<CrimeDatum> crimeData = new CrimeDataReader(
                "crime_data_w_population_and_crime_rate.csv").getData();
        List<IncomeDatum> incomeData = new IncomeDataReader(
                "acs2015_county_data.csv").getData();
        CrimeIncomeData.UserSelection selection = getUserInput();
        CrimeIncomeData merged = new CrimeIncomeData(crimeData, incomeData, selection);
    }

    //Method to get user selection
    private static CrimeIncomeData.UserSelection getUserInput() {
        System.out.println();

        //User instructions
        System.out.println("Enter the number of your selection:");
        System.out.println();
        System.out.println("1. Alphabetical list of Kentucky counties with average income " +
                "and poverty rates.");
        System.out.println("2. List of Kentucky counties indexed according to crime rate " +
                "per 100,000 people.");
        System.out.println("3. List of Kentucky counties indexed for comparison of crime " +
                "rate to average income and proverty rates.");
        System.out.println("4. Exit.");
        System.out.print(">  ");

        //Get the user's choice
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();

        //Choice made according to UserSelection enumeration in CrimeIncomeData class
        switch(choice) {
            case 1:
                return CrimeIncomeData.UserSelection.INCOME;
            case 2:
                return CrimeIncomeData.UserSelection.CRIME;
            case 3:
                return CrimeIncomeData.UserSelection.COMPARISON;
            default:
                System.out.println("Exiting");
                System.exit(0);
        }

        return CrimeIncomeData.UserSelection.CRIME;
    }
}
