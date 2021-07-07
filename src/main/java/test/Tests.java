package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class Tests {
    private final String url;
    private final Properties properties;
    private final int rowsNumber;

    public Tests(String url, Properties properties, int rowsNumber) {
        this.url = url;
        this.properties = properties;
        this.rowsNumber = rowsNumber;
    }

    public void run() throws Exception {
        try(Connection connection = DriverManager.getConnection(url, properties);
            Statement statement = connection.createStatement()) {
            System.out.println("Connection successfully established");

            statement.executeUpdate("CREATE TABLE test(x integer, y char, z double precision)");

            System.out.println("Insert test time: " + new InsertTest(connection, rowsNumber).test() + "ms");
            System.out.println("Select test time: " + new SelectTest(connection, rowsNumber).test() + "ms");
            System.out.println("Update test time: " + new UpdateTest(connection, rowsNumber).test() + "ms");
            System.out.println("Delete test time: " + new DeleteTest(connection, rowsNumber).test() + "ms");

            statement.executeUpdate("DROP TABLE test;");
        }
    }
}
