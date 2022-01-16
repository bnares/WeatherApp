package controller;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import view.View;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.*;

public class DailyForecast extends BaseController{

    MainWindowController mainWindowController;

    @FXML
    private HBox dailyTest;

    @FXML
    private ScrollPane scrollPane;

    public DailyForecast(View view, String fxmlTemplateName, MainWindowController mainWindowController) {
        super(view, fxmlTemplateName);
        this.mainWindowController = mainWindowController;

    }

    private JSONObject getCurrentDayWeatherData(){
        String cityName = mainWindowController.getCityNameLabel();
        System.out.println("cutyName: "+cityName);
        String url = "https://api.openweathermap.org/data/2.5/weather?q="+ URLEncoder.encode(cityName, StandardCharsets.UTF_8)+"&appid=ac3ab545014509bfe6bd90e10adf9a94&cnt=46&units=metric";
        StringBuffer response = getStringBufer(url);
        JSONObject jsonObject = getJSONObject(response);
        return jsonObject;
    }

    public void displayFirstDayWeatherData(JSONObject jsonObject){
        try {
            //System.out.println("przed main");
            //System.out.println("jsonObjcet z empty: "+jsonObject);
            JSONObject mainJSONObject = jsonObject.getJSONObject("main");
            //System.out.println("za main");
            JSONArray weatherJSONArray = jsonObject.getJSONArray("weather");
            JSONObject weatherJSONObject = weatherJSONArray.getJSONObject(0);

            //System.out.println("Za wszystkim");
            Label title = new Label();
            Label temp = new Label();
            Label feelsLike = new Label();
            Label pressure = new Label();
            Label humidity = new Label();
            Label description = new Label();
            ImageView imageView = new ImageView();
            VBox vBox = new VBox();
            vBox.setPadding(new Insets(0, 10, 0, 0));
            Insets lebelInsets = new Insets(2, 0, 2, 0);
            vBox.setPadding(new Insets(20, 20, 20, 20));

            DecimalFormat df = new DecimalFormat("##");
            temp.setText("Daily Tempture: " + df.format(mainJSONObject.get("temp")) + " ℃");
            pressure.setText("Pressure: " + mainJSONObject.get("pressure") + " hPa");
            humidity.setText("Humidity: " + mainJSONObject.get("humidity") + " %");
            feelsLike.setText("Feels like: " + df.format(mainJSONObject.get("feels_like")) + " ℃");
            String icon = (String) weatherJSONObject.get("icon");
            Image image = prepareImageView(icon);
            imageView.setImage(image);
            description.setText((String) weatherJSONObject.get("description"));

            Date date = new Date();
            String todaysDay = returnDateAsString(date, 0);
            title.setText("Day: " + todaysDay);
            vBox.getChildren().addAll(title, imageView, description, temp, feelsLike, pressure, humidity);
            dailyTest.getChildren().add(vBox);
        }catch (JSONException e){
            System.out.println(e.getMessage());
        }

    }

    private void gatherAllNeededLabelsForOneDayForecast(String key, ArrayList<String> value){
        ArrayList<VBox> vBoxes = new ArrayList<>();
        Label title = new Label();
        Label temp = new Label();
        Label feelsLike = new Label();
        Label pressure = new Label();
        Label humidity = new Label();
        Label wind = new Label();
        Label description = new Label();
        ImageView imageView = new ImageView();
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(0,10,0,0));
        DecimalFormat df = setNumberRunding();
        Insets lebelInsets = new Insets(2,0,2,0);
        vBox.setPadding(new Insets(20,20,20,20));
        title.setPadding(lebelInsets);
        temp.setPadding(lebelInsets);
        feelsLike.setPadding(lebelInsets);
        pressure.setPadding(lebelInsets);
        humidity.setPadding(lebelInsets);
        wind.setPadding(lebelInsets);
        description.setPadding(lebelInsets);

        title.setText("Hour: "+key);
        temp.setText("Tempture: "+df.format(Double.parseDouble(value.get(0)))+" ℃");
        feelsLike.setText("Feels Like: "+df.format(Double.parseDouble(value.get(1)))+" ℃");
        pressure.setText("Pressure: "+value.get(2)+" hPa");
        humidity.setText("Humidity: "+value.get(3)+" %");
        wind.setText("Wind: "+df.format(Double.parseDouble(value.get(4)))+" m/s");
        description.setText(value.get(5));
        imageView.setImage(prepareImageView(value.get(6)));
        vBox.getChildren().addAll(title,imageView,description,temp,feelsLike, pressure,humidity,wind );
        vBoxes.add(vBox);
        dailyTest.getChildren().addAll(vBoxes);

    }

    private void setDailyLabels() throws JSONException{
        HashMap<String, ArrayList<String>> dataDayWeather = mainWindowController.getWeatherHashMapData();
        //System.out.println("dataDayWeather: "+dataDayWeather);
        if(dataDayWeather.isEmpty()){
            //System.out.println("has mapa pusta byla do 21 godzony. skorzystales z metody setDailyLabels klasa DailyForecast");
            JSONObject jsonObject = getCurrentDayWeatherData();
            displayFirstDayWeatherData(jsonObject);

        }else {
            Set<String> keys = dataDayWeather.keySet();
            Iterator<String> itr = keys.iterator();

            while (itr.hasNext()) {
                String key = itr.next();
                ArrayList<String> value = dataDayWeather.get(key);
                //System.out.println("Value: " + value);
                gatherAllNeededLabelsForOneDayForecast(key, value);
            }
        }
    }

    @FXML
    private void initialize() throws JSONException {

        scrollPane.setPannable(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        setDailyLabels();

    }


}
