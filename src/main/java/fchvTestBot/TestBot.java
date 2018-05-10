package fchvTestBot;


import org.telegram.telegrambots.api.methods.send.SendDocument;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Contact;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class TestBot extends TelegramLongPollingBot {

    private static String botName;
    private static String token;
    static {
        BotInfoManager botInfoManager = new BotInfoManager();
        botName = botInfoManager.getBotName();
        token = botInfoManager.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            System.out.println(message_text);
            System.out.println("chat ID: " + chat_id);

            SendMessage message = null;
            if(new String(chat_id + "").equals("106128975")) {
                message = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText(message_text + "\nИриша! ^_^\nСвязаны!");
            } else {
                message = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText(message_text + "\nСвязаны!");
            }

            // Create ReplyKeyboardMarkup object
            ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
            // Create the keyboard (list of keyboard rows)
            List<KeyboardRow> keyboard = new ArrayList<>();
            // Create a keyboard row
            KeyboardRow row = new KeyboardRow();
            // Set each button, you can also use KeyboardButton objects if you need something else than text
            row.add("Дай фотку");
            // Add the first row to the keyboard
            keyboard.add(row);
            // Set the keyboard to the markup
            keyboardMarkup.setKeyboard(keyboard);
            // Add it to the message
            //message.setReplyMarkup(keyboardMarkup);

            if(message_text.equals("Дай фотку")) {
                //SendDocument sendDocument
                File file = null;
                //file = new File("C:\\Users\\safomichev\\Desktop\\SuperInput.txt");
                file = new File("C:\\Users\\safomichev\\Pictures\\file.png");
                SendDocument sendDocumentRequest = new SendDocument();
                sendDocumentRequest.setChatId(chat_id);
                sendDocumentRequest.setNewDocument(file);
                sendDocumentRequest.setCaption("Мой файл для тебя");
                try {
                    sendDocument(sendDocumentRequest);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    execute(message); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}
