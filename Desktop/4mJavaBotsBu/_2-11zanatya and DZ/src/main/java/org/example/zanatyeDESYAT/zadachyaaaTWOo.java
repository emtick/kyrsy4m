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
import java.nio.charset.StandardCharsets;
public class zadachyaaaTWOo extends TelegramLongPollingBot  {
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String currency = update.getMessage().getText().toUpperCase();
            String exchangeRate = getExchangeRate(currency);
            sendExchangeRate(update.getMessage().getChatId(), exchangeRate);
        }
    }

    private String getExchangeRate(String currency) {
        try {
            URL url = new URL("https://www.cbr-xml-daily.ru/daily_json.js");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            System.out.println("Response" + response);

            JsonObject jsonResponse = JsonParser.parseString(response.toString()).getAsJsonObject();
            System.out.println("jsonResponse" + jsonResponse);
            JsonObject valute = jsonResponse.getAsJsonObject("Valute").getAsJsonObject(currency);
            System.out.println("valute " + valute );
            String name = valute.get("Name").getAsString();
            double value = valute.get("Value").getAsDouble();
            double nominal = valute.get("Nominal").getAsDouble();

            return "Обменный курс для " + name + ":\n1 " + name + " = " + value / nominal + " RUB";

        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to get exchange rate for " + currency;
        }
    }

    private void sendExchangeRate(Long chatId, String message) {
        try {
            execute(SendMessage.builder().chatId(String.valueOf(chatId)).text(message).build());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String getBotUsername() {
        return "desatyibot_bot";
    }

    @Override
    public String getBotToken() {
        return "6993414471:AAG7inM38OE-bZeRuO9UHnkrl9u0NsLEqgQ";
    }
}
