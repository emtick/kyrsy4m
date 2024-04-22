package org.example.zanatyeDEVYAT;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;

public class zadachyaTHREE {
    public static void main(String[] args) {
        String token = "Ваш токен";
        String baseUrl = "https://api.telegram.org/bot" + token + "/";
        int offset = 0;

        while (true) {
            try {
                URL url = new URL(baseUrl + "getUpdates?offset=" + offset);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                JsonObject updates = JsonParser.parseString(response.toString()).getAsJsonObject();
                JsonArray result = updates.getAsJsonArray("result");

                for (JsonElement updateElement : result) {
                    JsonObject update = updateElement.getAsJsonObject();
                    offset = update.get("update_id").getAsInt() + 1;
                    String messageText = update.getAsJsonObject("message").get("text").getAsString();
                    String reversedMessage = new StringBuilder(messageText).reverse().toString();

                    String chatId = update.getAsJsonObject("message").getAsJsonObject("chat").get("id").getAsString();
                    sendMessage(baseUrl, chatId, reversedMessage);
                }

                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void sendMessage(String baseUrl, String chatId, String text) {
        try {
            URL url = new URL(baseUrl + "sendMessage");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            JsonObject message = new JsonObject();
            message.addProperty("chat_id", chatId);
            message.addProperty("text", text);

            byte[] outputInBytes = message.toString().getBytes(StandardCharsets.UTF_8);
            connection.getOutputStream().write(outputInBytes);
            connection.getInputStream();
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
