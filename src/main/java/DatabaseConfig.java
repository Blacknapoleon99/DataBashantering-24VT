import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static final String URL = "jdbc:mysql://awsmobiledb.c9cuyeyqqn8m.eu-north-1.rds.amazonaws.com:3306/aws-mobile_db";
    private static final String USER = "aws_admin";
    private static final String PASSWORD = "c1q6QDf4osfmBnXeZ6pL";

    public static String getUrl() {
        return URL;
    }

    public static String getUser() {
        return USER;
    }

    public static String getPassword() {
        return PASSWORD;
    }
}