import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

public final class InsertTest implements Test {
    private final Connection connection;
    private final String insertTemplate = "INSERT INTO insert_test VALUES(?,?,?)";

    public InsertTest(Connection connection) {
        this.connection = connection;
    }

    @Override
    public long test() throws Exception {
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE insert_test(x integer, y char, z double precision)");
            PreparedStatement insertStatement = connection.prepareStatement(insertTemplate);

            char c = 'a';
            double d = 1.0;
            long startTime = System.nanoTime();
            for(int i = 0; i < 100; i++) {
                insertStatement.setInt(1, i);
                insertStatement.setString(2, String.valueOf(c));
                insertStatement.setDouble(3, d);
                insertStatement.executeUpdate();

                c++;
                d *= 1.3;
            }
            long endTime = System.nanoTime();

            statement.executeUpdate("DROP TABLE insert_test");
            insertStatement.close();
            return TimeUnit.MILLISECONDS.convert(endTime - startTime, TimeUnit.NANOSECONDS);
        } catch(SQLException e) {
            throw new Exception("Error occured while creating statement", e);
        }
    }
}
