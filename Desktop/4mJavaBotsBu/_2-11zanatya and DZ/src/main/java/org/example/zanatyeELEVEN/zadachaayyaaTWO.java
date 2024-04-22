package org.example.zanatyeELEVEN;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.*;
import java.util.ArrayList;

public class zadachaayyaaTWO extends TelegramLongPollingBot  {
    private static final String FILE_NAME = "tasks.txt";
    private ArrayList<String> tasks = new ArrayList<>();

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message.hasText()) {
            String text = message.getText();
            if (text.startsWith("/start")) {
                loadTasksFromFile();
            } else if (text.startsWith("/add")) {
                String taskDescription = text.substring(5);
                tasks.add(taskDescription);
                saveTasksToFile();
            } else if (text.startsWith("/delete")) {
                int taskNumber = Integer.parseInt(text.substring(8));
                if (taskNumber >= 0 && taskNumber < tasks.size()) {
                    tasks.remove(taskNumber);
                    saveTasksToFile();
                }
            } else if (text.equals("/list")) {
                sendTasksList(message.getChatId());
            } else if (text.startsWith("/done")) {
                int taskNumber = Integer.parseInt(text.substring(6));
                if (taskNumber >= 0 && taskNumber < tasks.size()) {
                    tasks.set(taskNumber, tasks.get(taskNumber) + " - Done");
                    saveTasksToFile();
                }
            }
        }
    }

    public void loadTasksFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveTasksToFile() {
        try {
            PrintWriter writer = new PrintWriter(FILE_NAME);
            for (String task : tasks) {
                writer.println(task);
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void sendTasksList(Long chatId) {
        StringBuilder taskList = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            taskList.append(i).append(". ").append(tasks.get(i)).append("\n");
        }
        try {
            execute(SendMessage.builder().chatId(String.valueOf(chatId)).text(taskList.toString()).build());
        } catch (TelegramApiException e) {
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
