package com.ria.sofascore.bot;

import com.ria.sofascore.bot.configuration.Config;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import bot.configuration.SettingModel;

public class BodyBot extends TelegramLongPollingBot {

    private Config config = new Config();
    private SettingModel settingModel = config.readSetting();

    @Override
    public String getBotUsername() {
        return settingModel.getBotName();
    }

    @Override
    public void onUpdateReceived(Update e) {
        if(true){
            Message msg = e.getMessage();
            String txt = msg.getText();
            sendMsg(msg, String.valueOf(e.getMessage().getChatId()));
        }
    }

    @Override
    public String getBotToken() {
        return settingModel.getBotTokent();
    }

    @SuppressWarnings("deprecation")
    private void sendMsg(Message msg, String text) {
        SendMessage s = new SendMessage();
        s.setChatId(msg.getChatId());
        s.setText(text);
        try {
            sendMessage(s);
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
}
