package com.teamtreehouse.KyIncome;

//Class for storing and getting individual row data for use in IncomeDataReader class
public class IncomeDatum {
    private String county;
    private String income;
    private String povertyRate;

    public IncomeDatum(String county, String income, String povertyRate) {
        this.county = county;
        this.income = income;
        this.povertyRate = povertyRate;
    }

    public String getCounty() {
        return county;
    }

    public String getIncome() {
        return income;
    }

    public String getPovertyRate() {
        return povertyRate;
    }

    @Override
    public String toString() {
        return county + " county, KY, $" + income + " (average income), " + povertyRate +
                " (poverty rate)";
    }

}

