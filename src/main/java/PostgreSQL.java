import test.InsertTest;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgreSQL {
    public static void main(String[] args) {
        if(args.length < 5) {
            System.out.println("Insufficient arguments passed. You need to pass: ");
            System.out.println("1) host");
            System.out.println("2) port");
            System.out.println("3) database");
            System.out.println("4) username");
            System.out.println("5) password");
            return;
        }

        String host = args[0];
        String port = args[1];
        String database = args[2];
        String username = args[3];
        String password = args[4];

        try(Connection connection = DriverManager.getConnection(
                            "jdbc:postgresql://" + host + ":" + port + "/" + database,
                            username,
                            password)) {
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
