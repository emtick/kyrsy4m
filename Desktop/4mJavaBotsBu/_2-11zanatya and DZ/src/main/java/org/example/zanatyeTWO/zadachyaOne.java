package org.example.zanatyeTWO;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Random;

public class zadachyaOne extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            if (messageText.equals("/cat")) {
                String catFact = getRandomCatFact();
                sendMessage(chatId, catFact);
            } else if (messageText.equals("/dog")) {
                String dogFact = getRandomDogFact();
                sendMessage(chatId, dogFact);
            }
        }
    }

    private void sendMessage(long chatId, String text) {
        try {
            execute(new SendMessage(String.valueOf(chatId), text));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private String getRandomCatFact() {
        // Здесь можно добавить логику для получения случайного факта о кошках
        String[] catFacts = {
                "Кошки спят в среднем 12-16 часов в день.",
                "У кошек более 32 мышц в каждом ухе, что позволяет им слышать в шесть раз лучше, чем человек.",
                "Самая большая кошка в мире весит более 21 кг."
        };
        return catFacts[new Random().nextInt(catFacts.length)];
    }

    private String getRandomDogFact() {
        // Здесь можно добавить логику для получения случайного факта о собаках
        String[] dogFacts = {
                "Собаки обладают более 300 мимическими выражениями, больше, чем у какого-либо другого животного, и даже больше, чем у человека.",
                "Не все собаки могут плавать.",
                "Самая древняя порода собак в мире - салюки."
        };
        return dogFacts[new Random().nextInt(dogFacts.length)];
    }


    @Override
    public String getBotToken() {
        return "7055523222:AAEiATwOQ1VVwL2HW_gf0UK1rKMNsBERlSo";
    }

    @Override
    public String getBotUsername() {
        return "JavaZanatye_Bot";
    }
}
