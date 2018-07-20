package com.ria.sofascore.bot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class InitializationBot {

    private static InitializationBot instance;

    private InitializationBot() {
    }

    public static InitializationBot getInstance(){
        if(instance == null){
            instance = new InitializationBot();

            ApiContextInitializer.init();
            TelegramBotsApi botapi = new TelegramBotsApi();
            try {
                botapi.registerBot(new BodyBot());
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }
        return instance;
    }
}
