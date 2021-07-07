import test.Tests;

import java.util.Properties;

public class PostgreSQL {
    public static void main(String[] args) throws Exception {
        if(args.length < 6) {
            System.out.println("Insufficient arguments passed. You need to pass: ");
            System.out.println("1) host");
            System.out.println("2) port");
            System.out.println("3) database");
            System.out.println("4) username");
            System.out.println("5) password");
            System.out.println("6) rows number");
            return;
        }

        String host = args[0];
        String port = args[1];
        String database = args[2];
        String username = args[3];
        String password = args[4];
        int rowsNumber = Integer.parseInt(args[5]);

        Properties properties = new Properties();
        properties.setProperty("user", username);
        properties.setProperty("password", password);

        Tests tests = new Tests("jdbc:postgresql://" + host + ":" + port + "/" + database,
                properties,
                rowsNumber);
        tests.run();
    }
}
