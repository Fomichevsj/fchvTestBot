package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
    Connection connection;

    public DBHelper() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            //connection = DriverManager.getConnection("jdbc:hsqldb:file:C:\\DataBase/myDataBase", "SA", "");
            connection = DriverManager.getConnection("jdbc:hsqldb:file:C:\\SergDataBase/myDataBase", "SA", "");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
