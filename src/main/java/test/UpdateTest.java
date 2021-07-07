package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.concurrent.TimeUnit;

public final class UpdateTest implements Test {
    private final Connection connection;
    private final int rowsNumber;

    public UpdateTest(Connection connection, int rowsNumber) {
        this.connection = connection;
        this.rowsNumber = rowsNumber;
    }

    @Override
    public long test() throws Exception {
        try(PreparedStatement updateStatement =
                    connection.prepareStatement("UPDATE test SET x=? where x=?")) {
            long startTime = System.nanoTime();
            for(int i = 1; i < rowsNumber; i++) {
                updateStatement.setInt(1, i - 1);
                updateStatement.setInt(2, i);
                updateStatement.executeUpdate();
            }
            long endTime = System.nanoTime();

            return TimeUnit.MILLISECONDS.convert(endTime - startTime, TimeUnit.NANOSECONDS);
        }
    }
}
