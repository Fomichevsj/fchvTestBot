package fchvTestBot;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.Scanner;

/**
 * объект работает в отдельном потоке - слушает консоль и отправляет введенное сообщение определенным пользователям
 */
public class ListenThread extends Thread {
    private TestBot bot;
    private String primaryChatId = "89976540";
    private String irChatId = "106128975";

    public ListenThread(TestBot b) {
        bot = b;
    }

    @Override
    public void run() {
        System.out.println("Start listen thread");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String message = scanner.nextLine();
            if(message != null) {
                SendMessage sendMessage = new SendMessage(primaryChatId, message);
                SendMessage irSendMessage = new SendMessage(irChatId, message);
                try {
                    bot.execute(sendMessage);
                    System.out.println("Сообщение отпаврлено мне");
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                    System.out.println("Не получилось в отдельном потоке пославть сообщение мне");
                }

                try {
                    bot.execute(irSendMessage);
                    System.out.println("Сообщение отпаврлено Ире");
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                    System.out.println("Не получилось в отдельном потоке пославть сообщение Ире");
                }
            }
        }
    }

}
