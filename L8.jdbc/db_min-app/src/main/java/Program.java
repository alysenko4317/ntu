import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class Program
{
    public static void main(String[] args) throws Exception
    {
        final String userName = "postgres";
        final String userPass = "1234";
        final String connURL = "jdbc:postgresql://localhost:5432/car_portal";

        Class.forName("org.postgresql.Driver");

        Connection conn = DriverManager.getConnection(connURL, userName, userPass);
        Statement statement = conn.createStatement();

        ResultSet rs = statement.executeQuery("SELECT * FROM car_portal_app.account");
        while (rs.next())
        {
            System.out.println(
                rs.getString("first_name") + " "
                   + rs.getString("last_name"));
        }
    }
}
