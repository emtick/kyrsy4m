package org.example.zanatyeELEVEN;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.*;
import java.util.HashMap;

public class zadachaoneee extends TelegramLongPollingBot  {
    private HashMap<Long, StringBuilder> userNotes = new HashMap<>();
    private String notesFilePath = "user_notes.txt";

    public void Bot() {
        loadNotesFromFile();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();

            if ("/start".equals(update.getMessage().getText())) {
                sendTextMessage(chatId, "Я бот который умеет сохранять заметки!\n/addnote - добавить заметку\n/mynotes - посмотреть мои заметки");
            } else if ("/addnote".equals(update.getMessage().getText())) {
                userNotes.putIfAbsent(chatId, new StringBuilder());
                sendTextMessage(chatId, "Пожалуйста, введите заметку:");
            } else if ("/mynotes".equals(update.getMessage().getText())) {
                if (userNotes.containsKey(chatId) && userNotes.get(chatId).length() > 0) {
                    String[] notesArray = userNotes.get(chatId).toString().split(";");
                    StringBuilder notes = new StringBuilder();
                    for (String note : notesArray) {
                        notes.append(note);
                        notes.append("\n");
                    }
                    sendTextMessage(chatId, "Ваши заметки:\n" + notes);
                } else {
                    sendTextMessage(chatId, "У вас пока нет заметок.");
                }
            } else {
                if (userNotes.containsKey(chatId)) {
                    userNotes.get(chatId).append(update.getMessage().getText()).append(";");
                    saveNotesToFile();
                    sendTextMessage(chatId, "Заметка добавлена");
                }
            }
        }
    }

    private void sendTextMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void loadNotesFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(notesFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                userNotes.put(Long.parseLong(parts[0]), new StringBuilder(parts[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveNotesToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(notesFilePath))) {
            for (Long chatId : userNotes.keySet()) {
                writer.write(chatId + ":" + userNotes.get(chatId));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotToken() {
        return "6445414586:AAFxcrmIWVoLcEo0_J2bKWKiR3fM9gaKsNI";
    }

    @Override
    public String getBotUsername() {
        return "botikeleven_bot";
    }
}
