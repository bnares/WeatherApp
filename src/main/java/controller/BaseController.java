package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import org.json.JSONException;
import org.json.JSONObject;
import view.View;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class BaseController {
    public View view;
    private String fxmlTemplateName;


    public BaseController(View view, String fxmlTemplateName) {
        this.view = view;
        this.fxmlTemplateName = fxmlTemplateName;
    }

    public String getFxmlFile(){
        return fxmlTemplateName;
    }

    protected JSONObject getJSONObject(StringBuffer response){
        try {
            JSONObject myresponse = new JSONObject(response.toString());
            return myresponse;
        }catch (JSONException e){
            System.out.println(e.getMessage());

            return null;
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    protected StringBuffer getStringBufer(String url){
        URL adress= null;
        try{

            adress = new URL(url);
            URLConnection con = adress.openConnection();
            HttpURLConnection http = (HttpURLConnection)con;

            int responseCOde  = http.getResponseCode();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(http.getInputStream())
            );

            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null){
                response.append(inputLine);
            }
            in.close();

            return response;

        }catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (FileNotFoundException exce){

            StringBuffer errorStringBuffer = new StringBuffer();
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("error", "Cant find this city, try another one");
                errorStringBuffer.append(jsonObject);
            }catch (JSONException e){
                System.out.println(e.getMessage());
            }

            //errorWindow();
            return errorStringBuffer;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public void errorWindow(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Exception Dialog");
        alert.setHeaderText("Look, an Exception Dialog");
        alert.setContentText("Could not find this city!");

        Exception ex = new FileNotFoundException("Could not find file this city");

// Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

// Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
    }


    protected Image prepareImageView(String icon){
        String imageUrl = "https://openweathermap.org/img/wn/"+icon+"@2x.png";
        Image imageView = new Image(imageUrl);
        return imageView;
    }

    protected DecimalFormat setNumberRunding(){
        DecimalFormat df = new DecimalFormat("##");
        return df;
    }


    protected String returnDateAsString(Date date, int number){
        Date currentDay = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar dayAfter = Calendar.getInstance();

        dayAfter.setTime(currentDay);
        dayAfter.add(Calendar.DATE,number);
        return dateFormat.format(dayAfter.getTime());

    }


}
