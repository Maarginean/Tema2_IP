package ro.mta.se.lab.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class WeatherInfo {
    private ArrayList<Country> countries;

    public WeatherInfo() {
        countries=new ArrayList<>();
    }

    public ArrayList<Country> getCountries() {
        return countries;
    }

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
}
