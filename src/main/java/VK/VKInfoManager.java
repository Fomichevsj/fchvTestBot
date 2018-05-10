package VK;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class VKInfoManager {
    private String pathToFile = "C:\\Users\\User\\IdeaProjects\\fchvTestBot\\resources\\VKInfo";
    private static String token;

    public String getToken() {
        return token;
    }

    /**
     *
     * @param s path to file where stores bot's info
     */
    public VKInfoManager(String s) {
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
            token = scanner.nextLine().split("=")[1];
        }
    }

    public VKInfoManager() {
        this("C:\\Users\\User\\IdeaProjects\\fchvTestBot\\resources\\VKInfo");
    }
}
