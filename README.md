# KYCrimeToIncome
A CodeLouisville project

## Description

In this project, I've compared two sets of data on Kentucky counties to determine whether or not there is any apparent connection between crime rate and both average income and poverty rate. Given the results, it is difficult to see, at least on the county level, any connection between said data. The user has three choices, resulting in three possible console print-outs as well as three CSV files written to disk:
1. Alphabetical list of Kentucky counties with average income and poverty rates.
2. List of Kentucky counties indexed according to crime rate per 100,000 people.
3. List of Kentucky counties indexed for comparison of crime rate to average income and proverty rates.
The two datasets are from "United States crime rates by county" (https://www.kaggle.com/mikejohnsonjr/united-states-crime-rates-by-county) and "US Census and Demographic Data" (https://www.kaggle.com/muonneutrino/us-census-demographic-data#acs2015_county_data.csv).

### Dependencies
* JDK 12
* Maven

### Installing
1. Clone the project
bash
git clone https://github.com/pthompson0084/KYCrimeToIncome.git
cd KYCrimeToIncome

2. Run the project using maven
mvn clean package exec:java -Dexec.mainClass=com.teamtreehouse.App



