package org.example.zanatyeDEVYAT;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class zadachyaTWOO {
    public static void main(String[] args) {
        try {
            // Получение данных о первом user
            URL url = new URL("https://jsonplaceholder.typicode.com/users/1");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();

            // Разбор JSON
            JsonElement jsonElement = new JsonParser().parse(sb.toString());
            JsonObject user = jsonElement.getAsJsonObject();


            JsonObject address = new JsonObject();
            address.addProperty("street", "New Street");
            address.addProperty("suite", "New Suite");
            address.addProperty("city", "New City");
            address.addProperty("zipcode", "New Zipcode");
            // Добавляем новое место работы
            JsonObject company = new JsonObject();
            company.addProperty("name", "New Company");
            user.add("address", address);
            user.add("company", company);


            // Загрузка нового user на сервер под id = 11
            URL updateUrl = new URL("https://jsonplaceholder.typicode.com/users");
            connection = (HttpURLConnection) updateUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            connection.connect();

            byte[] outputInBytes = user.toString().getBytes();
            connection.getOutputStream().write(outputInBytes);

            // Обработка ответа от сервера
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                BufferedReader updateReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder updateResponse = new StringBuilder();
                String updateLine;
                while ((updateLine = updateReader.readLine()) != null) {
                    updateResponse.append(updateLine);
                }
                updateReader.close();
                connection.disconnect();
                System.out.println("User successfully created: " + updateResponse);
            } else {
                System.out.println("Failed to create user. Response code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
