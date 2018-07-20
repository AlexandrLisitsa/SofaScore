package com.ria.sofascore.bot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class InitializationBot {

    private InitializationBot instance;

    private InitializationBot() {
    }

    public InitializationBot getInstance(){
        if(instance == null){
            instance = new InitializationBot();
        }
        return instance;
    }

    public void Initialization(){
        ApiContextInitializer.init();
        TelegramBotsApi botapi = new TelegramBotsApi();
        try {
            botapi.registerBot(new BodyBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
