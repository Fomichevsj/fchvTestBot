package fchvTestBot;

import VK.BaseConnection;
import org.junit.Test;

public class TestVKConnection {
    @Test
    public void testConnection() {
        BaseConnection baseConnection = new BaseConnection("fchvjvamch");
        for(int i = 0; i < 1000; i++) {
            try {
                Thread.sleep(5*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.println(baseConnection.getPost());
            baseConnection.getPost();
            boolean up = baseConnection.isUpdated();
            if(up) {
                System.out.println("Новый пост");
                System.out.println(baseConnection.getPost());
            }
            else System.out.println("old");

        }
    }
}
