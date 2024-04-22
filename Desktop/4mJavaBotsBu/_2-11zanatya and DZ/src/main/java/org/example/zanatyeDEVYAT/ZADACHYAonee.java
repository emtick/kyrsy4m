package org.example.zanatyeDEVYAT;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ZADACHYAonee {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/posts");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Создание JSON объекта для нового поста
            JsonObject postObject = new JsonObject();
            postObject.addProperty("title", "New post");
            postObject.addProperty("body", "This is the text of the new post");

            // Отправка JSON объекта в теле запроса
            OutputStream os = connection.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            osw.write(postObject.toString());
            osw.flush();
            osw.close();
            os.close();

            // Получение ответа от сервера
            InputStream responseStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(responseStream));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            // Парсинг JSON ответа
            JsonObject jsonResponse = JsonParser.parseString(response.toString()).getAsJsonObject();

            // Вывод информации о созданном посте
            int postId = jsonResponse.get("id").getAsInt();
            String postTitle = jsonResponse.get("title").getAsString();
            String postBody = jsonResponse.get("body").getAsString();

            System.out.println("Пост создан:");
            System.out.println("ID: " + postId);
            System.out.println("Заголовок: " + postTitle);
            System.out.println("Содержимое: " + postBody);

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
