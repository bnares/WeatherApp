package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import view.View;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.*;

public class MainWindowController extends BaseController{


    @FXML
    private MenuItem about;

    @FXML
    private TextField cityNameTextField;

    @FXML
    private Label firstDayDesc;

    @FXML
    private Label firstDayHumidity;

    @FXML
    private ImageView firstDayImage;

    @FXML
    private ImageView firstNightImage;

    @FXML
    private Label firstDayPressure;

    @FXML
    private Label firstDayTemp;

    @FXML
    private Label firstDayTempMax;

    @FXML
    private Label firstDayTempMin;

    @FXML
    private Label firstDayTiitle;

    @FXML
    private Label firstNIghtTempMax;

    @FXML
    private Label firstNightDesc;

    @FXML
    private Label firstNightHumidity;

    @FXML
    private Label firstNightPressure;

    @FXML
    private Label firstNightTemp;

    @FXML
    private Label firstNightTempMin;

    @FXML
    private Label firstNightFeelsLikeTemp;

    @FXML
    private Label firstDayFeelsLikeTemp;

    @FXML
    private Label errorLabel;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private HBox contentHBox;

    @FXML
    private MenuItem dailyForecast;

    @FXML
    private Label firstNightTiitle;

    @FXML
    private Label SecondDayDesc;

    @FXML
    private Label SecondDayFeelsLikeTemp;

    @FXML
    private Label SecondDayHumidity;

    @FXML
    private ImageView SecondDayImage;

    @FXML
    private Label SecondDayPressure;

    @FXML
    private Label SecondDayTemp;

    @FXML
    private Label SecondDayTempMax;

    @FXML
    private Label SecondDayTempMin;

    @FXML
    private Label SecondDayTiitle;

    @FXML
    private Label SecondNIghtTempMax;

    @FXML
    private Label SecondNightDesc;

    @FXML
    private Label SecondNightFeelsLikeTemp;

    @FXML
    private Label SecondNightHumidity;

    @FXML
    private ImageView SecondNightImage;

    @FXML
    private Label SecondNightPressure;

    @FXML
    private Label SecondNightTemp;

    @FXML
    private Label SecondNightTempMin;

    @FXML
    private Label SecondNightTiitle;

    @FXML
    private Label thirdDayDesc;

    @FXML
    private Label thirdDayFeelsLikeTemp;

    @FXML
    private Label thirdDayHumidity;

    @FXML
    private ImageView thirdDayImage;

    @FXML
    private Label thirdDayPressure;

    @FXML
    private Label thirdDayTemp;

    @FXML
    private Label thirdDayTempMax;

    @FXML
    private Label thirdDayTempMin;

    @FXML
    private Label thirdDayTiitle;

    @FXML
    private Label thirdNIghtTempMax;

    @FXML
    private Label thirdNightDesc;

    @FXML
    private Label thirdNightFeelsLikeTemp;

    @FXML
    private Label thirdNightHumidity;

    @FXML
    private ImageView thirdNightImage;

    @FXML
    private Label thirdNightPressure;

    @FXML
    private Label thirdNightTemp;

    @FXML
    private Label thirdNightTempMin;

    @FXML
    private Label thirdNightTiitle;


    @FXML
    private Label fourthDayDesc;

    @FXML
    private Label fourthDayFeelsLikeTemp;

    @FXML
    private Label fourthDayHumidity;

    @FXML
    private ImageView fourthDayImage;

    @FXML
    private Label fourthDayPressure;

    @FXML
    private Label fourthDayTemp;

    @FXML
    private Label fourthDayTempMax;

    @FXML
    private Label fourthDayTempMin;

    @FXML
    private Label fourthDayTiitle;

    @FXML
    private Label fourthNIghtTempMax;

    @FXML
    private Label fourthNightDesc;

    @FXML
    private Label fourthNightFeelsLikeTemp;

    @FXML
    private Label fourthNightHumidity;

    @FXML
    private ImageView fourthNightImage;

    @FXML
    private Label fourthNightPressure;

    @FXML
    private Label fourthNightTemp;

    @FXML
    private Label fourthNightTempMin;

    @FXML
    private Label fourthNightTiitle;

    @FXML
    private Label cityNameLabel;

    private DailyForecast dailyForecastWIndow = null;
    private LinkedHashMap<String, ArrayList<String> > weatherHashMapData = new LinkedHashMap<>();
    private DailyForecast dailyForecastObject =null;

    public LinkedHashMap<String, ArrayList<String> > getWeatherHashMapData(){
        return weatherHashMapData;
    }

    public String getCityNameLabel(){
        return cityNameTextField.getText();
    }

    @FXML
    public void initialize(){

        scrollPane.setPannable(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        dailyForecast.setDisable(true);

    }

    @FXML
    void findCityButton() throws JSONException {
        errorLabel.setText("");

        if(!cityNameTextField.getText().isEmpty()){
            cityNameLabel.setText("Weather forecast for: "+cityNameTextField.getText().toUpperCase());
            JSONObject jsonObject = getCurrentDayWeatherDataAsJSON();
            displayFirstDayWeatherData(jsonObject);

            JSONObject jsonObjectFourDays = getFourDaysWeatherDataAsJsonObject();
            displayFourDaysWeatherData(jsonObjectFourDays);

        }else{
            errorLabel.setText("Text field must be fill in");
            dailyForecast.setDisable(true);
        }

    }

    private JSONObject getCurrentDayWeatherDataAsJSON(){
        String textField = cityNameTextField.getText();
        String url = "https://api.openweathermap.org/data/2.5/weather?q="+ URLEncoder.encode(textField, StandardCharsets.UTF_8)+"&appid=ac3ab545014509bfe6bd90e10adf9a94&cnt=46&units=metric";
        StringBuffer response = getStringBufer(url);

        JSONObject jsonObject = getJSONObject(response);
        return jsonObject;
    }

    private JSONObject getFourDaysWeatherDataAsJsonObject(){

        String textField = cityNameTextField.getText();

        String url = "https://api.openweathermap.org/data/2.5/forecast?q="+URLEncoder.encode(textField, StandardCharsets.UTF_8)+"&appid=ac3ab545014509bfe6bd90e10adf9a94&cnt=46&units=metric";
        StringBuffer response = getStringBufer(url);
        JSONObject jsonObject = getJSONObject(response);

        return jsonObject;

    }


    public void displayFourDaysWeatherData(JSONObject jsonObject) throws JSONException{

        try{
            JSONArray listJsonArray = null;
            listJsonArray = jsonObject.getJSONArray("list");
            int count=0;
            if (listJsonArray != null){
                for(int i =0; i<listJsonArray.length(); i++){
                    JSONObject listJSONObject = listJsonArray.getJSONObject(i);
                    JSONObject mainJSONObject = listJSONObject.getJSONObject("main");
                    JSONArray weatherJSONArray = listJSONObject.getJSONArray("weather");
                    JSONObject weatherJSONObject = weatherJSONArray.getJSONObject(0);
                    String icon = (String) weatherJSONObject.get("icon");
                    String description = (String) weatherJSONObject.get("description");
                    JSONObject windJSONObject = listJSONObject.getJSONObject("wind");

                    Object date = listJSONObject.get("dt_txt");
                    String[] listdate = getDateAsArray((String)date);

                    Date dateToday = new Date();

                    String todaysDay = returnDateAsString(dateToday,0);
                    String secondDay = returnDateAsString(dateToday,1);
                    String thirdDay = returnDateAsString(dateToday,2);
                    String fourthDay = returnDateAsString(dateToday,3);

                    String godzinaNocna = "21";
                    String godzinaDzienna = "12";

                    if(listdate[0].equals(todaysDay)){

                        ArrayList<String> weatherData = new ArrayList<>();
                        weatherData.add(mainJSONObject.get("temp").toString());
                        weatherData.add(mainJSONObject.get("feels_like").toString());
                        weatherData.add(mainJSONObject.get("pressure").toString());
                        weatherData.add(mainJSONObject.get("humidity").toString());
                        weatherData.add(windJSONObject.get("speed").toString());
                        weatherData.add(weatherJSONObject.get("description").toString());
                        weatherData.add(icon);
                        weatherHashMapData.put(listdate[1], weatherData);
                    }

                    if(listdate[0].equals(todaysDay) && listdate[1].startsWith(godzinaNocna)){
                        firstNightLabel(mainJSONObject, weatherJSONObject);
                        count++;
                    }

                    if(listdate[0].equals(secondDay)){

                        if(listdate[1].startsWith(godzinaDzienna)){

                            secondDayLabel(mainJSONObject, weatherJSONObject);

                        }

                        if(listdate[1].startsWith(godzinaNocna)){
                            secondNightLabel(mainJSONObject, weatherJSONObject);
                        }
                    }

                    if(listdate[0].equals(thirdDay)){
                        if(listdate[1].startsWith(godzinaDzienna)){
                            thirdDayLabel(mainJSONObject, weatherJSONObject);
                        }

                        if(listdate[1].startsWith(godzinaNocna)){
                            thirdNightLabel(mainJSONObject, weatherJSONObject);
                        }
                    }

                    if(listdate[0].equals(fourthDay)){
                        if(listdate[1].startsWith(godzinaDzienna)){
                            fourthDayLabel(mainJSONObject, weatherJSONObject);
                        }

                        if(listdate[1].startsWith(godzinaNocna)){
                            fourthNightLabel(mainJSONObject, weatherJSONObject);
                        }
                    }
                }

                if(count==0){

                    firstNightTemp.setText(firstDayTemp.getText());
                    firstNIghtTempMax.setText(firstDayTempMax.getText());
                    firstNightTempMin.setText(firstDayTempMin.getText());
                    firstNightDesc.setText(firstDayDesc.getText());
                    firstNightImage.setImage(firstDayImage.getImage());
                    firstNightTiitle.setText(firstDayTiitle.getText());
                    firstNightPressure.setText(firstDayPressure.getText());
                    firstNightHumidity.setText(firstDayHumidity.getText());
                    firstNightFeelsLikeTemp.setText(firstDayFeelsLikeTemp.getText());

                }

            }else{
                firstNightTiitle.setText("Sth went wrong");
                dailyForecast.setDisable(false);
            }

        }catch (JSONException e){
            String error = jsonObject.getString("error");
            errorLabel.setText(error);
            dailyForecast.setDisable(true);

        }


    }


    public void displayFirstDayWeatherData(JSONObject jsonObject) throws JSONException{

            try{
                JSONObject mainJSONObject = jsonObject.getJSONObject("main");
                JSONArray weatherJSONArray = jsonObject.getJSONArray("weather");
                JSONObject weatherJSONObject = weatherJSONArray.getJSONObject(0);
                firstDayLabel(mainJSONObject, weatherJSONObject);
                dailyForecast.setDisable(false);
            }catch (JSONException e){
                String error = jsonObject.getString("error");

                errorLabel.setText(error);
                dailyForecast.setDisable(true);
            }


    }


    private void fourthNightLabel(JSONObject mainJSONObject, JSONObject weatherJSONObject) throws JSONException{
        DecimalFormat df = setNumberRunding();
        dailyForecast.setDisable(false);
        fourthNightTemp.setText("Daily Tempture: "+df.format(mainJSONObject.get("temp"))+" ℃");
        fourthNIghtTempMax.setText("Tempture max: "+df.format(mainJSONObject.get("temp_max"))+" ℃");
        fourthNightTempMin.setText("Tempture min: "+df.format(mainJSONObject.get("temp_min"))+" ℃");
        fourthNightPressure.setText("Pressure: "+mainJSONObject.get("pressure")+" hPa");
        fourthNightHumidity.setText("Humidity: "+mainJSONObject.get("humidity")+" %");
        fourthNightFeelsLikeTemp.setText("Feels like: "+df.format(mainJSONObject.get("feels_like"))+" ℃");

        String icon = (String) weatherJSONObject.get("icon");
        Image image = prepareImageView(icon);
        fourthNightImage.setImage(image);
        Date date = new Date();
        String fourthDay = returnDateAsString(date, 3);
        fourthNightTiitle.setText("Day: "+fourthDay);

        fourthNightDesc.setText("Description: "+weatherJSONObject.get("description"));

    }

    private void fourthDayLabel(JSONObject mainJSONObject, JSONObject weatherJSONObject) throws JSONException{
        DecimalFormat df = setNumberRunding();
        fourthDayTemp.setText("Daily Tempture: "+df.format(mainJSONObject.get("temp"))+" ℃");
        fourthDayTempMax.setText("Tempture max: "+df.format(mainJSONObject.get("temp_max"))+" ℃");
        fourthDayTempMin.setText("Tempture min: "+df.format(mainJSONObject.get("temp_min"))+" ℃");
        fourthDayPressure.setText("Pressure: "+mainJSONObject.get("pressure")+" hPa");
        fourthDayHumidity.setText("Humidity: "+mainJSONObject.get("humidity")+" %");
        fourthDayFeelsLikeTemp.setText("Feels like: "+df.format(mainJSONObject.get("feels_like"))+" ℃");

        String icon = (String) weatherJSONObject.get("icon");
        Image image = prepareImageView(icon);
        fourthDayImage.setImage(image);
        Date date = new Date();
        String fourthDay = returnDateAsString(date, 3);
        fourthDayTiitle.setText("Day: "+fourthDay);

        fourthDayDesc.setText("Description: "+weatherJSONObject.get("description"));


    }

    private void thirdNightLabel(JSONObject mainJSONObject, JSONObject weatherJSONObject) throws JSONException{
        DecimalFormat df = setNumberRunding();
        thirdNightTemp.setText("Night Tempture: "+df.format(mainJSONObject.get("temp"))+" ℃");
        thirdNIghtTempMax.setText("Tempture max: "+df.format(mainJSONObject.get("temp_max"))+" ℃");
        thirdNightTempMin.setText("Tempture min: "+df.format(mainJSONObject.get("temp_min"))+" ℃");
        thirdNightPressure.setText("Pressure: "+mainJSONObject.get("pressure")+" hPa");
        thirdNightHumidity.setText("Humidity: "+df.format(mainJSONObject.get("humidity"))+" %");
        thirdNightFeelsLikeTemp.setText("Feels Like: "+df.format(mainJSONObject.get("feels_like"))+" ℃");
        String icon = (String) weatherJSONObject.get("icon");
        Image image = prepareImageView(icon);
        thirdNightImage.setImage(image);
        Date date = new Date();
        String thirdDay = returnDateAsString(date, 2);
        thirdNightTiitle.setText("Day: "+thirdDay);

        thirdNightDesc.setText("Description: "+weatherJSONObject.get("description"));

    }

    private void thirdDayLabel(JSONObject mainJSONObject, JSONObject weatherJSONObject) throws JSONException{
        DecimalFormat df = setNumberRunding();
        thirdDayTemp.setText("Daily Tempture: "+df.format(mainJSONObject.get("temp"))+" ℃");
        thirdDayTempMax.setText("Tempture max: "+df.format(mainJSONObject.get("temp_max"))+" ℃");
        thirdDayTempMin.setText("Tempture min: "+df.format(mainJSONObject.get("temp_min"))+" ℃");
        thirdDayPressure.setText("Pressure: "+mainJSONObject.get("pressure")+" hPa");
        thirdDayHumidity.setText("Humidity: "+mainJSONObject.get("humidity")+" %");
        thirdDayFeelsLikeTemp.setText("Feels like: "+df.format(mainJSONObject.get("feels_like"))+" ℃");
        String icon = (String) weatherJSONObject.get("icon");
        Image image = prepareImageView(icon);
        thirdDayImage.setImage(image);

        Date date = new Date();
        String thirdDay = returnDateAsString(date, 2);
        thirdDayTiitle.setText("Day: "+thirdDay);

        thirdDayDesc.setText("Description: "+weatherJSONObject.get("description"));

    }

    public void secondDayLabel(JSONObject mainJSONObject, JSONObject weatherJSONObject) throws JSONException{
        DecimalFormat df = setNumberRunding();
        SecondDayTemp.setText("Daily Tempture: "+df.format(mainJSONObject.get("temp"))+" ℃");
        SecondDayTempMax.setText("Tempture max: "+df.format(mainJSONObject.get("temp_max"))+" ℃");
        SecondDayTempMin.setText("Tempture min: "+df.format(mainJSONObject.get("temp_min"))+" ℃");
        SecondDayPressure.setText("Pressure "+df.format(mainJSONObject.get("pressure"))+" hPa");
        SecondDayHumidity.setText("Humidity: "+mainJSONObject.get("humidity")+" %");
        SecondDayFeelsLikeTemp.setText("Feels like: "+df.format(mainJSONObject.get("feels_like"))+" ℃");

        String icon = (String) weatherJSONObject.get("icon");
        Image image = prepareImageView(icon);
        SecondDayImage.setImage(image);
        SecondDayDesc.setText("Descritpion: "+weatherJSONObject.get("description"));

        Date date = new Date();
        String todaysDay = returnDateAsString(date, 1);
        SecondDayTiitle.setText("Day: "+todaysDay);



    }

    private void secondNightLabel(JSONObject mainJSONObject, JSONObject weatherJSONObject) throws JSONException{
        DecimalFormat df = setNumberRunding();
        dailyForecast.setDisable(false);
        SecondNightTemp.setText("Night Tempture: "+df.format(mainJSONObject.get("temp"))+" ℃");
        SecondNIghtTempMax.setText("Tempture max: "+df.format(mainJSONObject.get("temp_max"))+" ℃");
        SecondNightTempMin.setText("Tempture min: "+df.format(mainJSONObject.get("temp_min"))+" ℃");
        SecondNightPressure.setText("Prtessure: "+mainJSONObject.get("pressure")+" hPa");
        SecondNightHumidity.setText("Humidity: "+mainJSONObject.get("humidity")+" %");
        SecondNightFeelsLikeTemp.setText("Feels like: "+df.format(mainJSONObject.get("feels_like"))+" ℃");

        String icon = (String) weatherJSONObject.get("icon");
        Image image = prepareImageView(icon);
        SecondNightImage.setImage(image);
        SecondNightDesc.setText("Description: "+weatherJSONObject.get("description"));

        Date date = new Date();
        String todaysDay = returnDateAsString(date, 1);
        SecondNightTiitle.setText("Day: "+todaysDay);

    }

    public void firstNightLabel(JSONObject mainJSONObject, JSONObject weatherJSONObject) throws JSONException{
        DecimalFormat df = setNumberRunding();
        firstNightTemp.setText("NIght Tempture: "+df.format(mainJSONObject.get("temp"))+" ℃");
        firstNIghtTempMax.setText("Tempture max: "+df.format(mainJSONObject.get("temp_max"))+" ℃");
        firstNightTempMin.setText("Temoture min: "+df.format(mainJSONObject.get("temp_min"))+" ℃");
        firstNightPressure.setText("Pressure: "+mainJSONObject.get("pressure")+" hPa");
        firstNightHumidity.setText("Humidity: "+mainJSONObject.get("humidity")+" %");
        firstNightFeelsLikeTemp.setText("Feels like: "+df.format(mainJSONObject.get("feels_like"))+" ℃");
        String icon = (String) weatherJSONObject.get("icon");
        Image image = prepareImageView(icon);
        firstNightImage.setImage(image);
        firstNightDesc.setText("Description: "+weatherJSONObject.get("description"));

        Date date = new Date();
        String todaysDay = returnDateAsString(date, 0);
        firstNightTiitle.setText("Day: "+todaysDay);

    }

    public void firstDayLabel(JSONObject mainJSONObject, JSONObject weatherJSONObject) throws JSONException{

        DecimalFormat df = new DecimalFormat("##");
        firstDayTemp.setText("Daily Tempture: "+df.format(mainJSONObject.get("temp"))+" ℃");
        firstDayTempMax.setText("Tempture max: "+df.format(mainJSONObject.get("temp_max"))+" ℃");
        firstDayTempMin.setText("Tempture min: "+df.format(mainJSONObject.get("temp_min"))+" ℃");
        firstDayPressure.setText("Pressure: "+mainJSONObject.get("pressure")+" hPa");
        firstDayHumidity.setText("Humidity: "+mainJSONObject.get("humidity")+" %");
        firstDayFeelsLikeTemp.setText("Feels like: "+ df.format(mainJSONObject.get("feels_like"))+" ℃");


        String icon = (String) weatherJSONObject.get("icon");
        Image imageView = prepareImageView(icon);
        firstDayImage.setImage(imageView);


        firstDayDesc.setText("Description: "+weatherJSONObject.get("description"));

        Date date = new Date();
        String todaysDay = returnDateAsString(date, 0);
        firstDayTiitle.setText("Day: "+todaysDay);


    }



    public void displayWeatherDataForFourDays(JSONObject jsonObject) throws JSONException {

        JSONArray jsonArray = null;
        jsonArray = jsonObject.getJSONArray("list");
        presentDataListFromJSONArray(jsonArray);

    }

    private String[] getDateAsArray(String text){
        String[] getStringArrayFromDate = text.split(" ");
        return getStringArrayFromDate;
    }

    private void presentDataListFromJSONArray(JSONArray jsonArray) throws JSONException{


        if(jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectListData = jsonArray.getJSONObject(i);
                JSONArray weatherJSONArray = jsonObjectListData.getJSONArray("weather");
                JSONObject mainJSONObject = jsonObjectListData.getJSONObject("main");
                Object dateJSONObject = jsonObjectListData.get("dt_txt");
                setLabelDayText(jsonObjectListData, weatherJSONArray, mainJSONObject, dateJSONObject);

            }

        }else{
            errorLabel.setText("Sth went wrong, list data is empty");
            return;
        }


    }

    private void setLabelDayText( JSONObject jsonObjectListData,JSONArray weatherJSONArray, JSONObject mainJSONObject, Object dateJSONObject) throws JSONException{
        String[] dateArray = getDateAsArray((String) jsonObjectListData.get("dt_txt"));
        Date date = new Date();

        String todaysDay = returnDateAsString(date,0);

        String godzina = "12";
        if(dateArray[0].equals(todaysDay) && dateArray[1].startsWith(godzina)) {

            JSONObject weatherJSONObject = weatherJSONArray.getJSONObject(0);
            firstDayLabel(mainJSONObject, weatherJSONObject);

        }
    }


    @FXML
    void dailyForecastButton() {
        view.showDailyForecastWindow(this);
    }

    @FXML
    void closeButton() {
        Stage stage = (Stage) firstDayDesc.getScene().getWindow();
        stage.close();
    }


    


    public MainWindowController(View view, String fxmlTemplateName) {

        super(view, fxmlTemplateName);

    }


}
