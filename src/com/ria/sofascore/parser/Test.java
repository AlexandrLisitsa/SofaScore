package com.ria.sofascore.parser;

import com.ria.sofascore.models.Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("C:\\Users\\WyPik\\Desktop\\singleGame.txt"));
            StringBuilder stringBuilder = new StringBuilder();
            while(scanner.hasNext()){
                stringBuilder.append(scanner.nextLine());
            }
            SingleGameParser singleGameParser = new SingleGameParser();
            singleGameParser.parse(stringBuilder.toString(),new Game());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
