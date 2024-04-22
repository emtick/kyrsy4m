
package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MyBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update){
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            String text = message.getText();

            int wordCount = countWords(text);
            int charCount = text.length();

            String response = "Word count: " + wordCount + "\nChar count: " + charCount;

            sendResponse(message.getChatId(), response);
        }

    }

    private int countWords(String text) {
        String[] words = text.split("\\s+");
        return words.length;
    }
    private void sendResponse(Long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText(text);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String getBotUsername(){
        return "Guitarstreet_bot";
    }
    @Override
    public String getBotToken(){
        return "7017300815:AAEUSuJOl_iLpu2-QEVK3aNT4zT7r9rQrpk";
    }
}
