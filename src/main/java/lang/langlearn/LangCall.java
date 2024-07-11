package lang.langlearn;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class LangCall {

    public static String getTranslation(String untranslatedData, String requestedLang) {

        //ensure safe for url query. replace special chars with url =
        untranslatedData = URLEncoder.encode(untranslatedData, StandardCharsets.UTF_8);

        String url = "https://api.mymemory.translated.net/get?q=" + untranslatedData + "&langpair=en|" + requestedLang;


        try {
            //call api
            HttpURLConnection conn = fetchApiResponse(url);
            //check response status
            if (conn.getResponseCode() != 200) {
                System.out.print("Error: could not make API connection");
                return null;

            }
            //store Json result data
            StringBuilder resultJson = new StringBuilder();
            Scanner scanner = new Scanner(conn.getInputStream());
            while (scanner.hasNext()) {
                //read and store into string builder
                resultJson.append(scanner.nextLine());
            }
            scanner.close();
            conn.disconnect();

            //parse data
            JSONParser parser = new JSONParser();
            JSONObject resultJsonObj = (JSONObject) parser.parse(resultJson.toString());
            JSONObject responseData = (JSONObject) resultJsonObj.get("responseData");

            return (String)responseData.get("translatedText");



        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }



    private static HttpURLConnection fetchApiResponse(String urlString) {
        try {
            //try to connect
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //set request to get->we are attempting to GET location data
            conn.setRequestMethod("GET");
            //connect to API
            conn.connect();
            return conn;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;//if no connection

    }
}
