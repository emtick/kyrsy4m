package org.example.zanatyeThree;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class zadachyaThree extends TelegramLongPollingBot  {
    private Map<Long, Integer> userProgress = new HashMap<>();


    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();

            if (update.getMessage().getText().equals("/start")) {
                // Начало квеста - отправляем приветствие и первый вопрос
                userProgress.put(chatId, 1);
                sendTextMessageWithInlineButtons(chatId, "Привет! Ты оказался в загадочном замке. Куда пойдем?", "Вперед", "Назад");
            }
        } else if (update.hasCallbackQuery()) {
            long chatId = update.getCallbackQuery().getMessage().getChatId();
            String optionChosen = update.getCallbackQuery().getData();
            int currentProgress = userProgress.get(chatId);

            switch (currentProgress) {
                case 1:
                    if (optionChosen.equals("Вперед")) {
                        sendTextMessageWithInlineButtons(chatId, "Ты видишь две двери. Какую выберешь?", "Левая", "Правая");
                        userProgress.put(chatId, 2);
                    } else {
                        sendTextMessage(chatId, "Ты вернулся обратно и встретил стража. Игра окончена.");
                        userProgress.remove(chatId);
                    }
                    break;
                case 2:
                    if (optionChosen.equals("Левая")) {
                        sendTextMessage(chatId, "Ты нашел сокровища и победил! Игра окончена.");
                        userProgress.remove(chatId);
                    } else {
                        sendTextMessage(chatId, "Ты выпал в яму. Игра окончена.");
                        userProgress.remove(chatId);
                    }
                    break;
                // добавьте варианты для других шагов квеста и их завершение
            }
            // подтверждаем выбор пользователя
            sendCallbackQueryAnswer(update.getCallbackQuery().getId());
        }
    }

    private void sendTextMessageWithInlineButtons(long chatId, String text, String... options) {
        SendMessage message = SendMessage.builder()
                .chatId(chatId)
                .text(text)
                .build();

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        for (String option : options) {
            List<InlineKeyboardButton> row = new ArrayList<>();
            InlineKeyboardButton button = InlineKeyboardButton.builder()
                    .text(option)
                    .callbackData(option)
                    .build();
            row.add(button);
            rows.add(row);
        }

        keyboardMarkup.setKeyboard(rows);
        message.setReplyMarkup(keyboardMarkup);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendTextMessage(long chatId, String text) {
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

    private void sendCallbackQueryAnswer(String callbackQueryId) {
        AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery().builder().callbackQueryId(callbackQueryId).build();
        try {
            execute(answerCallbackQuery);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotToken() {
        return "6716218546:AAGIh-pKdZalqSnrj2LidCryIB7os2eQrEU";
    }

    @Override
    public String getBotUsername() {
        return "prbtpit_bot";
    }
}
