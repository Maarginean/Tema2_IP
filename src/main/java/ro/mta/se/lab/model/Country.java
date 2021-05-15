package ro.mta.se.lab.model;

import java.util.ArrayList;

public class Country {
    private ArrayList<City> cities;
    private String country;


    public Country(String country) {
        this.country = country;
        cities=new ArrayList<City>();
    }

    public void setCities(City city) {
        this.cities.add(city);
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public String getCountry() {
        return country;
    }
}
