package org.example.zanatyesix;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.polls.SendPoll;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Arrays;

public class zadatwo extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        // Проверяем, является ли сообщение от пользователя
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Отправляем три вопроса с вариантами ответов
            sendQuestion(update.getMessage().getChatId(), "Какой цвет у неба?", "Синий", "Зеленый", "Красный");
            sendQuestion(update.getMessage().getChatId(), "Сколько планет в Солнечной системе?", "8", "7", "9");
            sendQuestion(update.getMessage().getChatId(), "Какой город является столицей Франции?", "Париж", "Лондон", "Рим");
        }
    }

    private void sendQuestion(Long chatId, String question, String... options) {
        SendPoll sendPoll = new SendPoll();
        sendPoll.setChatId(chatId.toString());
        sendPoll.setQuestion(question);
        sendPoll.setOptions(Arrays.asList(options));
        sendPoll.setType("quiz");
        sendPoll.setCorrectOptionId(0);
        try {
            execute(sendPoll);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotToken() {
        return "6738205758:AAH-EIM54MFtXXlbbsD_LaMAzt5rTsX1w0k";
    }

    @Override
    public String getBotUsername() {
        return "fxseven_bot";
    }
}
