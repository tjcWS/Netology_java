package org.example;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, TelegramApiException {
        new NasaBot("7345192749:AAGlzs1dDi3FtbCT47fAetM0E-ljsDXpnrY", "Nasa_Java");
    }
}
