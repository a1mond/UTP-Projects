package assignment10;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectorDB {
    public static Connection connect() {
        try {
            System.out.println("CONNECTING TO localhost:5432/utp-10");
            Connection connect = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/utp-10",
                    "postgres",
                    "lagara84"
            );
            System.out.println("CONNECTED");
            return connect;
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(3);
            return null;
        }
    }
}
