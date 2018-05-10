package fchvTestBot;

import org.junit.Assert;
import org.junit.Test;

public class BotInfoManagerTest {
    private static final String pathToFile = "C:\\Users\\User\\IdeaProjects\\fchvTestBot\\resources\\BotInfoTest";
    @Test
    public void testConstructor() {
        BotInfoManager botInfoManager = new BotInfoManager(pathToFile);
        Assert.assertEquals("myTestBot", botInfoManager.getBotName());
        Assert.assertEquals("tokenTest", botInfoManager.getToken());
        BotInfoManager botInfoManagerReal = new BotInfoManager();
        System.out.println(botInfoManagerReal.getBotName());
        System.out.println(botInfoManagerReal.getToken());
    }
}
