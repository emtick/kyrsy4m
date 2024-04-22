

package org.example.zanatyeThree;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class firstZadachya extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();
            if (update.getMessage().getText().equals("/start")) {
                sendStartMessage(chatId);
            }
        } else if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            long chatId = update.getCallbackQuery().getMessage().getChatId();
            String callbackQueryId = update.getCallbackQuery().getId();


            if (callbackData.equals("show_time")) {
                sendTime(chatId, callbackQueryId);
            }
        }
    }

    private void sendTime(long chatId, String callbackQueryId) {
        String currentTime = LocalTime.now().toString().substring(0, 8); // Получаем текущее время в формате ЧЧ:ММ:СС
        SendMessage response = new SendMessage();
        response.setChatId(chatId);
        response.setText("Текущее время: " + currentTime);

        AnswerCallbackQuery close = new AnswerCallbackQuery();
        close.setCallbackQueryId(callbackQueryId);

        try {
            execute(response);
            execute(close);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendStartMessage(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Привет! Я - Времябот. Нажми кнопку ниже, чтобы узнать текущее время.");

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton showTimeButton = new InlineKeyboardButton();
        showTimeButton.setText("Показать время");
        showTimeButton.setCallbackData("show_time");
        row.add(showTimeButton);
        keyboard.add(row);
        keyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(keyboardMarkup);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "prbtpit_bot";
    }

    @Override
    public String getBotToken() {
        return "6716218546:AAGIh-pKdZalqSnrj2LidCryIB7os2eQrEU";
    }
}
