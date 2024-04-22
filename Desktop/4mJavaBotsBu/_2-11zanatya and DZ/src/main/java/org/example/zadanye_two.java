package org.example;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.HashMap;

public class zadanye_two extends TelegramLongPollingBot {

    private HashMap<String, String> quotes;

    public void TestBot() {
        quotes = new HashMap<>();
        quotes.put("Альберт Эйнштейн", "Чтобы быть счастливым, надо не иметь большого счастья или много денег, а иметь хоть малость больше, чем другие.");
        quotes.put("Уильям Шекспир", "В мире всё сцена, и люди лишь актеры.");
        quotes.put("Александр Ивченко", "С Артемом спорить бесполезно, лучше поесть нормально");
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            if (quotes.containsKey(messageText)) {
                sendQuote(update.getMessage().getChatId(), quotes.get(messageText));
            } else {
                sendQuote(update.getMessage().getChatId(), "Извините, цитата для данной личности не найдена.");
            }
        }
    }

    private void sendQuote(Long chatId, String quote) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText(quote);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotToken() {
        return "7017300815:AAEUSuJOl_iLpu2-QEVK3aNT4zT7r9rQrpk";
    }

    @Override
    public String getBotUsername() {
        return "Guitarstreet_bot";
    }
}
