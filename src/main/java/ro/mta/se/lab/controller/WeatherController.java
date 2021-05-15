package ro.mta.se.lab.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ro.mta.se.lab.model.City;
import ro.mta.se.lab.model.Country;
import ro.mta.se.lab.model.WeatherInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;

/**
 * Contime metodele necesare conectarii cu fisierul fxml si conectarea cu API-ul OpenWeatherMap
 * @author Marginean Florin
 */
public class WeatherController implements Initializable {

    /**
     * @param info contine informatiile extrase din fisierul de configurare.
     */
    private WeatherInfo info;

    private ObservableList<String> countryList= FXCollections.observableArrayList();

    /**
     * @param curentID contine Id-ul orasului selectate.
     */
    private String currentID=null;

    /**
     * Constructor
     * @param info
     */
    public WeatherController(WeatherInfo info) {
        this.info = info;
    }

    /**
     * Constructor
     */
    public WeatherController() {
        this.info = null;
    }

    @FXML
    public ComboBox<String> C_country;
    @FXML
    public ComboBox<String> C_city;
    @FXML
    public TextField info1,info2,info3,info4,info5;

    @FXML
    public void show_country(){
        if(C_country.getValue()!=null){
            ObservableList<String> cityList= FXCollections.observableArrayList();
            for(Country i: info.getCountries()){
                if(i.getCountry()==C_country.getValue()){
                    for(City j:i.getCities()){
                        cityList.add(j.getName());
                    }
                }
            }
            C_city.setItems(cityList);
        }
    }

    /**
     * getId_city afla Id-ul orasului daca este selectat ceva in ComboBox-uri.
     */
    @FXML
    public void getId_city(){
        if(C_city.getValue()!=null){
            for(Country i: info.getCountries()){
                if(i.getCountry().equals(C_country.getValue())){
                    for(City j:i.getCities()){
                        if(j.getName().equals(C_city.getValue()))
                            currentID=j.getId();
                    }
                }
            }
        }
    }

    /**
     * show_weather apeleaza functia de obtinere a vremii daca este selectat ceva in ComboBox-uri.
     */
    @FXML
    public void show_weather() throws IOException, ParseException {
        if(currentID!=null){
            this.weather(currentID);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(info != null)
        {
            for(Country i: info.getCountries()){
                countryList.add(i.getCountry());
            }
            C_country.setItems(countryList);
        }
    }

    /**
     * weather foloseste API-ul OpenWeatherMap pentru a obtine un Buffer formatat JSON.
     * Obiectele sunt preluate din JSON si afisate in mai multe TextField-uri.
     */
    public void weather(String ID) throws IOException, ParseException {
        try {
            //System.out.print(ID);
            JSONParser jsonParser = new JSONParser();
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?id=" + ID + "&appid=2cc581a29bcd6acca6944407f242a896");
            URLConnection connection = url.openConnection();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            JSONObject jsonObject = (JSONObject) jsonParser.parse(bufferedReader);

            JSONArray jsonArray = (JSONArray) jsonObject.get("weather");
            JSONObject weather = (JSONObject) jsonArray.get(0);

            JSONObject wind = (JSONObject) jsonObject.get("wind");
            JSONObject main=(JSONObject) jsonObject.get("main");
            JSONObject sys=(JSONObject) jsonObject.get("sys");

            String vreme = (String) weather.get("main");
            String description=(String)weather.get("description");
            String oras = (String) jsonObject.get("name");
            Object speed=wind.get("speed");
            Object humidity=main.get("humidity");
            Object pressure=main.get("pressure");
            Object co=sys.get("country");
            if (oras != null) {
                info1.setText(C_city.getValue()+" "+C_country.getValue());
                info2.setText(vreme);
                info3.setText("Humidity: "+humidity.toString()+"%");
                info4.setText("Wind: "+speed.toString()+" km/h");
                info5.setText("Pressure: "+pressure.toString()+" mmHg");
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

}
