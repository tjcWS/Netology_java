package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;
import java.util.SplittableRandom;

public class NasaBot extends TelegramLongPollingBot {
    private String url
            = "https://api.nasa.gov/planetary/apod" + "?api_key=oMIeFWhC6eDfABqAQcDOSZx280mkf28ezPLIjgNZ";
    //"&date=2024-08-06";
    private final String BOT_NAME;
    private final String BOT_TOKEN;

    public NasaBot(String BOT_TOKEN,
                   String BOT_NAME
    )throws TelegramApiException {
        this.BOT_TOKEN = BOT_TOKEN;
        this.BOT_NAME = BOT_NAME;
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(this);
    }

    @Override
    public void onUpdateReceived(Update update) {
        // start  help  image  date 2024-10-10
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatID = update.getMessage().getChatId();
            String text = update.getMessage().getText();

            String[] splittedAns = text.split(" ");
            String action = splittedAns[0];

            switch (action){
                case "/start":
                    sendMessage(chatID, "Я бот, высылающий картинки с сервера Nasa");
                    break;
                case "/help":
                    sendMessage(chatID, "Для получения картинки введи /image или /date YYYY-MM-DD");
                    break;
                case "/image":
                    String image = null;
                    try {
                        image = Utils.getUrl(url);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    sendMessage(chatID, image);
                    break;
                case "/date":
                    String date = splittedAns[1];
                    String image1 = null;
                    try {
                        image1 = Utils.getUrl(url + "&date=" + date);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    sendMessage(chatID, image1);
                    break;
                default:
                    sendMessage(chatID, "Я хз че ты пишешь");
            }
        }
    }

    //вспомогательный метод
public void sendMessage(long chatID, String text){
    SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
    message.setChatId(chatID);
    message.setText(text);

    try {
        execute(message); // Call method to send the message
    } catch (TelegramApiException e) { //указываем тип ошибки
        e.printStackTrace();
    }
}

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }
}
