package ro.mta.se.lab;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ro.mta.se.lab.controller.WeatherController;
import ro.mta.se.lab.model.WeatherInfo;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws MalformedURLException {
        FXMLLoader loader = new FXMLLoader(new File("D:\\Anul IV semestru II\\WeatherApp\\src\\main\\resources\\view\\WeatherView.fxml").toURI().toURL());
        try {
            WeatherInfo w=new WeatherInfo();
            w.readFile("D:\\Anul IV semestru II\\WeatherApp\\src\\main\\resources\\Orase.txt");
            loader.setController(new WeatherController(w));
            primaryStage.setScene(new Scene(loader.load()));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}