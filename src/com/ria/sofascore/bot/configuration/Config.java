package com.ria.sofascore.bot.configuration;


import bot.configuration.SettingModel;
import com.google.gson.*;
import javafx.scene.control.Alert;

import java.io.*;
import java.util.Scanner;

public class Config {

    public String getPathSettingFile(){
        String path = Config.class.getResource("").getPath();
        String[] temp = path.split("/");

        final String nameApp = "Sofa.jar!";

        StringBuilder buildPath = new StringBuilder();
        for (int i = 1; i < temp.length; i++) {

            if(!temp[i].equals(nameApp)){
                buildPath.append(temp[i]+"/");
            }
            else {
                buildPath.append("Setting");
                break;
            }
        }
        return buildPath.toString();
    }

    public static String getPath() throws IOException {
        return "C:\\Users\\Prog1\\Desktop\\setting.json";
    }

    public void saveSetting(String name, String token){
        JsonObject setting = new JsonObject();
        setting.addProperty("name", name);
        setting.addProperty("token", token);


        try(Writer writer = new FileWriter(getPath())){
            Gson gson = new GsonBuilder().create();
            gson.toJson(setting, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public SettingModel readSetting(){

        JsonParser jsonParser = new JsonParser();
        StringBuilder builder = new StringBuilder();
        Scanner fileReader = null;
        try {
            fileReader = new Scanner(new FileReader(new File(getPath())));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
}
