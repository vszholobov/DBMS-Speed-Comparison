package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.concurrent.TimeUnit;

public final class DeleteTest implements Test {
    private final Connection connection;
    private final int rowsNumber;

    public DeleteTest(Connection connection, int rowsNumber) {
        this.connection = connection;
        this.rowsNumber = rowsNumber;
    }

    @Override
    public long test() throws Exception {
        try(PreparedStatement deleteStatement =
                    connection.prepareStatement("DELETE FROM test WHERE x=?")) {
            long startTime = System.nanoTime();
            for(int i = 0; i < rowsNumber; i++) {
                deleteStatement.setInt(1, i);
                deleteStatement.executeUpdate();
            }
            long endTime = System.nanoTime();

            return TimeUnit.MILLISECONDS.convert(endTime - startTime, TimeUnit.NANOSECONDS);
        }
    }
}
