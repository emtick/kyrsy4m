package org.example.zanatyeSeven;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class zadachyathreee extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            String text = message.getText();

            if (text.startsWith("/start")) {
                sendTextMessage(message.getChatId(), "Привет! Пришли мне ссылку на страницу для парсинга ссылок.");
            } else {
                ArrayList<String> links = extractLinks(message.getText());
                String strLinks = String.join("\n", links);
                String subStr = strLinks.length() > 4078 ? strLinks.substring(0, 4078) : strLinks;
                sendTextMessage(message.getChatId(), "Найденные ссылки:\n" + subStr);
            }
        }
    }
    private ArrayList<String> extractLinks(String urlString) {

        ArrayList<String> links = new ArrayList<>();

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();

            String htmlContent = stringBuilder.toString();
            Pattern pattern = Pattern.compile("http[s]?:\\/\\/[^\\s\"\'<]+");
            Matcher matcher = pattern.matcher(htmlContent);
            while (matcher.find()) {
                links.add(matcher.group());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return links;
    }

    private void sendTextMessage(Long chatId, String text) {

        SendMessage message = SendMessage.builder()
                .chatId(chatId)
                .text(text)
                .build();

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String getBotToken() {
        return "7148262167:AAF46vXzN9NJCqt-4dSV6RLUi3QOxpyNsiE";
    }

    @Override
    public String getBotUsername() {
        return "sevenprstat_bot";
    }
}
