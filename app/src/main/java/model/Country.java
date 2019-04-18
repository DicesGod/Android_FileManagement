package model;

public class Country implements Comparable{

    private String countryName;
    private String countryCapital;

    public Country(String countryName, String countryCapital) {
        this.countryName = countryName;
        this.countryCapital = countryCapital;
    }


    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCapital() {
        return countryCapital;
    }

    public void setCountryCapital(String countryCapital) {
        this.countryCapital = countryCapital;
    }

    @Override
    public String toString() {
        return countryName;
    }


    @Override
    public int compareTo(Object o){
    Country otherObject = (Country) o;
        if (countryName.compareTo(otherObject.countryName) > 0){
            return 1;
        }else if(countryName.compareTo(otherObject.countryName) == 0){
            return 0;
        }else{
            return -1;
        }


    }
}
