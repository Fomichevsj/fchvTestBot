package fchvTestBot;

import VK.VKListenThread;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Main {
    public static void main(String[] args) {
        // Initialize Api Context
        ApiContextInitializer.init();

        // Instantiate Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi();

        // Register our bot
        try {
            TestBot myBot = new TestBot();
            botsApi.registerBot(myBot);

            //89976540
            SendMessage message = new SendMessage() // Create a message object object
                    .setChatId("89976540")
                    .setText( "Я стартовал!");
            try {
                myBot.execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            VKListenThread vkListenThread = new VKListenThread(myBot);
            vkListenThread.start();
            System.out.println("Поток обновления ВК запущен");
            ListenThread listenThread = new ListenThread(myBot);
            listenThread.start();
            System.out.println("Поток получения сообщений запущен");
            //create thread to send message
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
