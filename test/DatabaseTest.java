import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseTest {
    @Test

    public void connectionTest() throws SQLException{
        Assertions.assertTrue(true); // Tester om true er true, bare for at teste om vores test klasse virker
        Connection connection = null;

        String JdbcUrl = "jdbc:mysql://127.0.0.1:3306/sp3+?" + "autoReconnect=true&useSSL=false";
        String username = "root";
        String password = "Lampen04aug";

        try {
            connection = DriverManager.getConnection(JdbcUrl, username, password);
            Assertions.assertEquals(true, connection.isValid(1));
            connection.close();
            Assertions.assertEquals(false, connection.isValid(1));

        } catch (SQLException e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
    }
}

