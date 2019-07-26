package com.teamtreehouse.KyCrime;

//import java.text.ParseException;

public class CrimeDatum {
    private String county;
    private String crimeRate;
    private String index;

    CrimeDatum(String county, String crimeRate, String index) {
        this.county = county;
        this.crimeRate = crimeRate;
        this.index = index;
    }

    public String getCounty() {
        return county;
    }

    public String getCrimeRate() {
        return crimeRate;
    }

    public String getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return index + ". " + county + " county, KY, " + crimeRate + " (crime rate)";
    }

}
