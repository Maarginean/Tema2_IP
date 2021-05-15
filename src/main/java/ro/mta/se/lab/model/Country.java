package ro.mta.se.lab.model;

import java.util.ArrayList;

/**
 * Stocheaza datele tarilor din fisierul initial.
 * @author Marginean Florin
 */
public class Country {
    /**
     * @param cities stocheaza o lista cu orasele dintr-o tara.
     */
    private ArrayList<City> cities;
    /**
     * @param country stocheaza numele tarii respective.
     */
    private String country;

    /**
     * Setters
     */
    public void setCities(City city) {
        this.cities.add(city);
    }

    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Getters
     */
    public ArrayList<City> getCities() {
        return cities;
    }

    public String getCountry() {
        return country;
    }

    /**
     * Constructor
     * @param country
     */
    public Country(String country) {
        this.country = country;
        cities=new ArrayList<City>();
    }
}
