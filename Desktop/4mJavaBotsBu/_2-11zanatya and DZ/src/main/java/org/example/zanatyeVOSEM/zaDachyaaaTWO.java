package org.example.zanatyeVOSEM;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class zaDachyaaaTWO {
    public static void main(String[] args) throws UnknownHostException {

        String botToken = "7148262167:AAF46vXzN9NJCqt-4dSV6RLUi3QOxpyNsiE";

        try {
            URL url = new URL("https://api.telegram.org/bot" + botToken + "/getUpdates");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String responseBody = response.toString();

            // Извлечение текста сообщения с помощью регулярного выражения
            Pattern pattern = Pattern.compile("\"text\":\"(.*?)\"");
            Matcher matcher = pattern.matcher(responseBody);
            while (matcher.find()) {
                String messageText = matcher.group(1);
                System.out.println("Получено сообщение от пользователя: " + messageText);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
