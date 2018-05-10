package fchvTestBot;

import VK.VKInfoManager;
import org.junit.Assert;
import org.junit.Test;

public class VKInfoManagerTest {
    private static final String pathToFile = "C:\\Users\\User\\IdeaProjects\\fchvTestBot\\resources\\ResourcesTest\\VKInfoTest";
    @Test
    public void testConstructor() {
        VKInfoManager vkInfoManagerTest = new VKInfoManager(pathToFile);
        Assert.assertEquals("mySuperToken", vkInfoManagerTest.getToken());
    }
}
