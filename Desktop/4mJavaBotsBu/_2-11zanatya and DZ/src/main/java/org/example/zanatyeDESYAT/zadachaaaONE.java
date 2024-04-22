package org.example.zanatyeDESYAT;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Set;

public class zadachaaaONE extends TelegramLongPollingBot  {
    private final String OPENWEATHER_API_KEY = "371140b6fa774180f82d3fe1f576d2f5";

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String city = update.getMessage().getText();
            String weatherInfo = getWeatherInfo(city);
            sendWeatherInfo(update.getMessage().getChatId(), weatherInfo);
        }
    }

    private String getWeatherInfo(String city) {
        try {
            String encodedCity = URLEncoder.encode(city, "UTF-8");
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + encodedCity + "&appid=" + OPENWEATHER_API_KEY + "&lang=ru&units=metric");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JsonObject jsonResponse =  JsonParser.parseString(response.toString()).getAsJsonObject();
            // Извлечение информации о погоде
            String mainWeather = jsonResponse.getAsJsonObject("main").get("temp").getAsString();
            String humidity = jsonResponse.getAsJsonObject("main").get("humidity").getAsString();
            String pressure = jsonResponse.getAsJsonObject("main").get("pressure").getAsString();

            return "Погода " + city + ":\nТемпература: " + mainWeather + "C\nВлажность: " + humidity + "%\nДавление: " + pressure + "hPa";

        } catch (Exception e) {
            e.printStackTrace();
            return "Не удалось получить информацию о погоде для " + city;
        }
    }

    private void sendWeatherInfo(Long chatId, String message) {
        try {
            execute(SendMessage.builder().chatId(String.valueOf(chatId)).text(message).build());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotToken() {
        return "6993414471:AAG7inM38OE-bZeRuO9UHnkrl9u0NsLEqgQ";
    }

    @Override
    public String getBotUsername() {
        return "desatyibot_bot";
    }
}
