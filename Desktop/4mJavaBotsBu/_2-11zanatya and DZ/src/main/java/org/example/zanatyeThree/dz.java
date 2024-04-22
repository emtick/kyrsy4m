package org.example.zanatyeThree;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class dz extends TelegramLongPollingBot {

        private static final String BOT_USERNAME = "prbtpit_bot";
        private static final String BOT_TOKEN = "6716218546:AAGIh-pKdZalqSnrj2LidCryIB7os2eQrEU";
        private static final List<String> WORDS = new ArrayList<>();

        static {

            WORDS.add("word1");
            WORDS.add("word2");
        }

        public void main(String[] args) {
            try {
                execute(new SetWebhook("https://your-webhook-url/" + "6716218546:AAGIh-pKdZalqSnrj2LidCryIB7os2eQrEU"));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpdateReceived(Update update) {
            if (update.hasMessage() && update.getMessage().hasText()) {
                String messageText = update.getMessage().getText();

                if (messageText.equalsIgnoreCase("/start")) {
                    sendInstructions(update);
                } else {
                    String userWord = messageText;
                    String correctWord = getRandomWord();
                    boolean isCorrect = userWord.equalsIgnoreCase(correctWord);

                    sendResult(update, isCorrect, correctWord);
                }
            }
        }

        private void sendInstructions(Update update) {
            SendMessage message = new SendMessage();
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        private void sendResult(Update update, boolean isCorrect, String correctWord) {
            SendMessage message = new SendMessage();
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        private String getRandomWord() {
            Random random = new Random();
            return WORDS.get(random.nextInt(WORDS.size()));
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
