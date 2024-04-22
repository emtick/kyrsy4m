package org.example.zanatyeThree;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;


public class zadachyaTWo extends TelegramLongPollingBot {
    private String operation;
    private double num1;
    private double num2;

    @Override
    public void onUpdateReceived(Update update) {
        // Обработка входящего сообщения от пользователя
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            if (messageText.startsWith("/start")) {
                sendMessage(chatId, "Привет! Я бот-калькулятор. Выберите операцию с помощью инлайн кнопок.");
                sendOperationsKeyboard(chatId);
            } else if (messageText.matches("\\d+\\s+\\d+")) {
                String[] nums = messageText.split(" ");
                num1 = Double.parseDouble(nums[0]);
                num2 = Double.parseDouble(nums[1]);
                double result = calculateResult();
                sendMessage(chatId, num1 + operation + num2  + "=" + result);
            } else {
                sendMessage(chatId, "Неправильный ввод. Пожалуйста, выберите операцию или введите число.");
            }

        }

        // Обработка входящего запроса от пользователя с выбранной операцией
        if (update.hasCallbackQuery()) {
            operation = update.getCallbackQuery().getData();
            long chatId = update.getCallbackQuery().getMessage().getChatId();

            Message message = (Message) update.getCallbackQuery().getMessage();
            int messageId = message.getMessageId();
            String callbackQueryId = update.getCallbackQuery().getId();

            AnswerCallbackQuery close = AnswerCallbackQuery.builder()
                    .callbackQueryId(callbackQueryId)
                    .build();

            EditMessageReplyMarkup newKb = EditMessageReplyMarkup.builder()
                    .chatId(chatId)
                    .messageId(messageId)
                    .build();

            EditMessageText newTxt = EditMessageText.builder()
                    .chatId(chatId)
                    .messageId(messageId)
                    .text("Введите два числа через пробел")
                    .build();

            try {
                execute(close);
                execute(newKb);
                execute(newTxt);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendOperationsKeyboard(long chatId) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        InlineKeyboardButton addButton = InlineKeyboardButton.builder()
                .text("+")
                .callbackData("+").build();

        InlineKeyboardButton subtractButton = InlineKeyboardButton.builder()
                .text("-")
                .callbackData("-").build();

        InlineKeyboardButton multiplyButton = InlineKeyboardButton.builder()
                .text("*")
                .callbackData("*").build();


        InlineKeyboardButton divideButton = InlineKeyboardButton.builder()
                .text("/")
                .callbackData("/").build();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(addButton);
        row1.add(subtractButton);

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(multiplyButton);
        row2.add(divideButton);

        rows.add(row1);
        rows.add(row2);

        markup.setKeyboard(rows);

        SendMessage message = SendMessage.builder()
                .chatId(chatId)
                .text("Выберите операцию:")
                .replyMarkup(markup).build();

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private double calculateResult() {

        double result = 0;

        switch (operation) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
        }
        return result;
    }

    public void sendMessage(Long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);

        try {
            execute(message);
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
