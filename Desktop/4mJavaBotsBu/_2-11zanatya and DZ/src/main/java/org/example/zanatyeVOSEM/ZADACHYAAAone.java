package org.example.zanatyeVOSEM;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;

public class ZADACHYAAAone {
    public static void main(String[] args) throws UnknownHostException {

        String token = "7148262167:AAF46vXzN9NJCqt-4dSV6RLUi3QOxpyNsiE";
        String urlString = "https://api.telegram.org/bot" + token + "/getMe";

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();

            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
