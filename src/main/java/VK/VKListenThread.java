package VK;

import fchvTestBot.TestBot;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class VKListenThread extends Thread {
    private String primaryChatId = "89976540";
    private String irChatId = "106128975";
    private String kira = "137727498";
    TestBot bot;

    public VKListenThread(TestBot b) {
        bot = b;
    }

    @Override
    public void run() {
        System.out.println("Start listen VK");
        BaseConnection baseConnection = new BaseConnection("fchvjvamch");
        for(int i = 0; i < 10_000; i++) {
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
                String message = baseConnection.getPost();
                System.out.println(message);
                SendMessage sendMessage = new SendMessage(primaryChatId, message);
                SendMessage irSendMessage = new SendMessage(irChatId, message);
                SendMessage kirSendMessage = new SendMessage(kira, message);

                try {
                    bot.execute(sendMessage);
                    System.out.println("Сообщение отпаврлено мне");
                } catch (TelegramApiException e) {
                    System.out.println("Не получилось в отдельном потоке пославть новый пост мне");
                    e.printStackTrace();
                }

                try {
                    bot.execute(irSendMessage);
                    System.out.println("Сообщение отпаврлено Ире");
                } catch (TelegramApiException e) {
                    System.out.println("Не получилось в отдельном потоке пославть новый пост Ире");
                    e.printStackTrace();
                }

                try {
                    bot.execute(kirSendMessage);
                    System.out.println("Сообщение отпаврлено мне");
                } catch (TelegramApiException e) {
                    System.out.println("Не получилось в отдельном потоке пославть новый пост кириллу");
                    e.printStackTrace();
                }
            }
            else System.out.println("old");

        }
    }
}
