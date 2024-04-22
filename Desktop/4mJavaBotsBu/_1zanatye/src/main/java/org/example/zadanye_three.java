package org.example;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class zadanye_three extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {

        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            String text = message.getText();
            if (text.startsWith("/start")) {
                sendReply(message, "Привет! Для расчета калорий введите необходимую информацию (пол возраст вес рост (отсутствие|легкая|умеренная|высокая) (похудение|набор|поддержание))");
            } else {
                String[] userData = update.getMessage().getText().split(" ");
                if (userData.length != 6) {
                    sendReply(message, "Неверный формат! Для расчета калорий введите необходимую информацию (пол возраст вес рост (отсутствие|легкая|умеренная|высокая) (похудение|набор|поддержание))");
                } else {
                    String gender = userData[0];
                    int age = Integer.parseInt(userData[1]);
                    double weight = Double.parseDouble(userData[2]);
                    double height = Double.parseDouble(userData[3]);
                    String activityLevel = userData[4];
                    String goal = userData[5];
                    int dailyCalories = calculateDailyCalories(gender, age, weight, height, activityLevel, goal);
                    String result = "Ваша норма ккал = " + dailyCalories;
                    sendReply(message, result);
                }

            }
        }



    }

    private int calculateDailyCalories(String gender, int age, double weight, double height, String activityLevel, String goal) {
        int bmr;
        if (gender.equalsIgnoreCase("мужской")) {
            bmr = (int) (66 + (6.23 * weight) + (15.5 * height) - (6.8 * age));
        } else {
            bmr = (int) (655 + (4.35 * weight) + (4.7 * height) - (4.7 * age));
        }

        double activityMultiplier;
        switch (activityLevel) {
            case "отсутствие":
                activityMultiplier = 1.2;
                break;
            case "легкая":
                activityMultiplier = 1.375;
                break;
            case "умеренная":
                activityMultiplier = 1.55;
                break;
            case "высокая":
                activityMultiplier = 1.725;
                break;
            default:
                activityMultiplier = 1.2;
        }

        int tdee = (int) (bmr * activityMultiplier);

        int dailyCalories;
        switch (goal) {
            case "похудение":
                dailyCalories = tdee - 500;
                break;
            case "набор":
                dailyCalories = tdee + 500;
                break;
            case "поддержание":
                dailyCalories = tdee;
                break;
            default:
                dailyCalories = tdee;
        }

        return dailyCalories;
    }

    private void sendReply(Message message, String quote) {
        SendMessage reply = new SendMessage();
        reply.setChatId(message.getChatId());
        reply.setText(quote);
        try {
            execute(reply);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotToken() {
        return  "7017300815:AAEUSuJOl_iLpu2-QEVK3aNT4zT7r9rQrpk";
    }

    @Override
    public String getBotUsername() {
        return "Guitarstreet_bot";
    }
}
