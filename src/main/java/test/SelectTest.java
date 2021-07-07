package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.concurrent.TimeUnit;

public final class SelectTest implements Test {
    private final Connection connection;
    private final int rowsNumber;

    public SelectTest(Connection connection, int rowsNumber) {
        this.connection = connection;
        this.rowsNumber = rowsNumber;
    }

    @Override
    public long test() throws Exception {
        try(PreparedStatement selectStatement
                    = connection.prepareStatement("SELECT * FROM test WHERE x = ?")) {
            long startTime = System.nanoTime();
            for(int i = 0; i < rowsNumber; i++) {
                selectStatement.setInt(1, i);
                selectStatement.executeQuery();
            }
            long endTime = System.nanoTime();

            return TimeUnit.MILLISECONDS.convert(endTime - startTime, TimeUnit.NANOSECONDS);
        }
    }
}
