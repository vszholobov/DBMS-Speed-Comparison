import test.Tests;

import java.util.Properties;

public class SQLite {
    public static void main(String[] args) throws Exception {
        if(args.length < 2) {
            System.out.println("Insufficient arguments passed. You need to pass: ");
            System.out.println("1) path to database");
            System.out.println("2) rows number");
            return;
        }

        String database = args[0];
        int rowsNumber = Integer.parseInt(args[1]);

        Properties properties = new Properties();

        Class.forName("org.sqlite.JDBC");
        Tests tests = new Tests("jdbc:sqlite:" + database,
                properties,
                rowsNumber);
        tests.run();

    }
}
