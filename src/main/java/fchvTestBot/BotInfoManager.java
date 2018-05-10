package fchvTestBot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class BotInfoManager {
    private String pathToFile = "C:\\Users\\User\\IdeaProjects\\fchvTestBot\\resources\\BotInfo";
    private static String botName;
    private static String token;

    public String getBotName() {
        return botName;
    }

    public String getToken() {
        return token;
    }

    /**
     *
     * @param s path to file where stores bot's info
     */
    public BotInfoManager(String s) {
        pathToFile = s;
        File file = new File(pathToFile);
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Не получилось прочитать файл");
        }

        if(fileReader != null) {
            Scanner scanner = new Scanner(fileReader);
            botName = scanner.nextLine().split("=")[1];
            token = scanner.nextLine().split("=")[1];
        }
    }

    public BotInfoManager() {
        this("C:\\Users\\User\\IdeaProjects\\fchvTestBot\\resources\\BotInfo");
    }
}
