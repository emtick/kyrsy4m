package org.example.zanatyeVOSEM;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class dztwo extends TelegramLongPollingBot{
    private String botToken;
    private String botUsername;

    public void CityGuideBot(String botToken, String botUsername) {
        this.botToken = botToken;
        this.botUsername = botUsername;
    }

    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();

            if (messageText.equals("/start")) {
                sendWelcomeMessage(update);
            } else if (messageText.equals("/help")) {
                sendHelpMessage(update);
            } else {
                // TODO: обработка других команд и сообщений
            }
        }
    }

    private void sendWelcomeMessage(Update update) {
        // TODO: отправка приветственного сообщения с инструкциями по использованию бота
    }

    private void sendHelpMessage(Update update) {
        // TODO: отправка сообщения с помощью по использованию бота
    }

    public String getBotUsername() {
        return "sevenprstat_bot";
    }

    public String getBotToken() {
        return "7148262167:AAF46vXzN9NJCqt-4dSV6RLUi3QOxpyNsiE";
    }
}

