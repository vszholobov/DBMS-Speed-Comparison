package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.concurrent.TimeUnit;

public final class InsertTest implements Test {
    private final Connection connection;
    private final int rowsNumber;

    public InsertTest(Connection connection, int rowsNumber) {
        this.connection = connection;
        this.rowsNumber = rowsNumber;
    }

    @Override
    public long test() throws Exception {
        try(PreparedStatement insertStatement =
                    connection.prepareStatement("INSERT INTO test VALUES(?,?,?)")) {
            char c = 'a';
            double d = 1.0;
            long startTime = System.nanoTime();
            for(int i = 0; i < rowsNumber; i++) {
                insertStatement.setInt(1, i);
                insertStatement.setString(2, String.valueOf(c));
                insertStatement.setDouble(3, d);
                insertStatement.executeUpdate();

                c++;
                d *= 1.3;
            }
            long endTime = System.nanoTime();

            return TimeUnit.MILLISECONDS.convert(endTime - startTime, TimeUnit.NANOSECONDS);
        }
    }
}
