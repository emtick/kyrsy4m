package org.example.zanatyesix;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class zadaOne extends TelegramLongPollingBot  {
    private static final String MEMES_DIRECTORY = "C:\\Users\\vyshe\\IdeaProjects\\BotTest\\memes";
    private static final String CATS_MEMES_DIRECTORY = "C:\\Users\\vyshe\\IdeaProjects\\BotTest\\memes\\Мемы про котов";
    private static final String PROGRAMMERS_MEMES_DIRECTORY = "C:\\Users\\vyshe\\IdeaProjects\\BotTest\\memes\\Мемы про программистов";

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();
            if (update.getMessage().getText().equals("/start")) {
                sendCategories(chatId);
            }
        }

        if (update.hasCallbackQuery()) {
            String data = update.getCallbackQuery().getData();
            String callbackQueryId = update.getCallbackQuery().getId();
            long chatId = update.getCallbackQuery().getMessage().getChatId();

            String memesDirectoryPath = "";

            if ("Мемы про котов".equals(data)) {
                memesDirectoryPath = CATS_MEMES_DIRECTORY;
            } else if ("Мемы про программистов".equals(data)) {
                memesDirectoryPath = PROGRAMMERS_MEMES_DIRECTORY;
            }

            File memesFolder = new File(memesDirectoryPath);
            File[] memes = memesFolder.listFiles();

            if (memes != null && memes.length > 0) {
                File randomMeme = memes[new Random().nextInt(memes.length)];
                sendPhoto(chatId, randomMeme);
            }

            AnswerCallbackQuery close = new AnswerCallbackQuery();
            close.setCallbackQueryId(callbackQueryId);

            try {
                execute(close);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }


        }
    }

    private void sendPhoto(long chatId, File photo) {
        SendPhoto sendPhotoRequest = new SendPhoto();
        sendPhotoRequest.setChatId(chatId);
        sendPhotoRequest.setPhoto(new InputFile(photo));

        try {
            execute(sendPhotoRequest);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendCategories(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Выберите категорию мемов:");

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();

        File memesFolder = new File(MEMES_DIRECTORY);
        if (memesFolder.exists() && memesFolder.isDirectory()) {
            for (File category : memesFolder.listFiles()) {
                if (category.isDirectory()) {
                    rowInline.add(new InlineKeyboardButton().builder().text(category.getName()).callbackData(category.getName()).build());
                }
            }
        }

        rowsInline.add(rowInline);
        markup.setKeyboard(rowsInline);
        message.setReplyMarkup(markup);

        try {
            execute(message);
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
