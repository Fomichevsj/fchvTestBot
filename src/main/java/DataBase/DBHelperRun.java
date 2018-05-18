package DataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHelperRun {
    public static void main(String[] args) {
        DBHelper dbHelper = new DBHelper();
        try {
            Statement statement = dbHelper.connection.createStatement();
            //statement.executeQuery("create TABLE Contact( TelegramId VARCHAR (32), VK_Id VARCHAR (32));");
            statement.executeQuery("INSERT INTO \"PUBLIC\".\"CONTACT\"\n" +
                    "( \"TELEGRAMID\", \"VK_ID\" )\n" +
                    "VALUES ( 'asdf', '24934')");
            ResultSet resultSet = statement.executeQuery("select * from contact");
            if(resultSet != null) {
                while (resultSet.next())
                    System.out.println(resultSet.getString("TelegramId"));
            } else {
                System.out.println("empty");
            }
            dbHelper.connection.commit();
            dbHelper.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
