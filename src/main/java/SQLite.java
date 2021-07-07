import test.InsertTest;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLite {
    public static void main(String[] args) {
        try(Connection connection = DriverManager.getConnection("url")) {
            if(connection == null) {
                System.out.println("error");
                return;
            }

            System.out.println("Connection established");

            System.out.println("Insert test time in milliseconds: " + new InsertTest(connection).test());

        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}
