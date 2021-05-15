package ro.mta.se.lab.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Stocheaza datele din fisierul de configurare.
 * @author Marginean Florin
 */
public class WeatherInfo {

    /**
     * @param countries stocheaza o lista cu tarile din fisierul initial.
     */
    private ArrayList<Country> countries;

    /**
     * Getters
     */
    public ArrayList<Country> getCountries() { return countries; }

    /**
     * readFile citeste tot fisierul de initializare si adauga in lista de Country toate tarile cu orasele ei.
     * @param path calea definita catre fisierul de configurare.
     */
    public void readFile(String path){

        try {
            File fileOrase=new File(path);
            Scanner reader=new Scanner(fileOrase);
            String date=reader.nextLine();
            while(reader.hasNextLine()){
                String data=reader.nextLine();
                String []info;
                info=data.split(" ");
                City city=new City(info[0],info[1]);
                Country country=new Country(info[2]);
                boolean flag=false;
                for(Country country1:countries){
                    if(country1.getCountry().equals(country.getCountry())){
                        country1.setCities(city);
                        flag=true;
                    }
                }
                if(flag==false) {
                    country.setCities(city);
                    countries.add(country);
                }
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Error at opening file");
            e.printStackTrace();
        }
    }

    /**
     * Constructor
     */
    public WeatherInfo() {
        countries=new ArrayList<>();
    }
}
