package com.ria.sofascore.bot.configuration;


import bot.configuration.SettingModel;
import com.google.gson.*;
import java.io.*;
import java.util.Scanner;

public class Config {

    public static  void saveSetting(String name, String token){
        JsonObject setting = new JsonObject();
        setting.addProperty("name", name);
        setting.addProperty("token", token);

        String path = Config.class.getResource("/com/ria/sofascore/bot/configuration/setting.json").getPath();
        path = path.substring(1,path.length());
        try(Writer writer = new FileWriter(path)){
            Gson gson = new GsonBuilder().create();
            gson.toJson(setting, writer);
            //System.out.println(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static SettingModel readSetting(){
        String path = Config.class.getResource("/com/ria/sofascore/bot/configuration/setting.json").getPath();
        path = path.substring(1,path.length());

        JsonParser jsonParser = new JsonParser();
        StringBuilder builder = new StringBuilder();
        Scanner fileReader = null;
        try {
            fileReader = new Scanner(new FileReader(new File(path)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (fileReader.hasNextLine()){
            builder.append(fileReader.nextLine());
        }
        JsonElement jsonElement = jsonParser.parse(builder.toString());

        SettingModel settingModel = new SettingModel();
        settingModel.setBotName(jsonElement.getAsJsonObject().get("name").toString());
        settingModel.setBotTokent(jsonElement.getAsJsonObject().get("token").toString());
        return settingModel;
    }

//    public static void main(String[] args) {
//        saveSetting("lol", "kek");
//        readSetting("name");
//        readSetting("token");
//    }
}
