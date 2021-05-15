package ro.mta.se.lab.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class WeatherInfoTest {

    private WeatherInfo weather_test;

    @Before
    public void setUp() throws Exception {
        System.out.println("Inainte de test\n");
        weather_test=new WeatherInfo();
        weather_test.readFile("D:\\Anul IV semestru II\\WeatherApp\\src\\main\\resources\\Orase.txt");
    }

    @Test
    public void getCountries() {
        assertNotNull(weather_test.getCountries());
    }

    @Test
    public void readFile() {
        //System.out.println(weather_test.getCountries().size());
        assertEquals(weather_test.getCountries().size(),2);
    }
}