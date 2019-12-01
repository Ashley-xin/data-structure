import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {


    private static final String URL ="jdbc:mysql://localhost:3306/java12";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "xinxin0706";



    private static  DataSource DATASOURCE = new MysqlDataSource();
    static {
        ((MysqlDataSource) DATASOURCE).setURL(URL);
        ((MysqlDataSource) DATASOURCE).setUser(USERNAME);
        ((MysqlDataSource) DATASOURCE).setPassword(PASSWORD);
    }


    private static DataSource DATASOURCE2;

    private DBUtil() {

    }



    public static void main(String[] args) {
        System.out.println(getConnection1());
        System.out.println(getConnection2());
        System.out.println(getDataSource());
    }



    public static Connection getConnection1() {
        Connection connection = null;
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager
                        .getConnection(URL, USERNAME, PASSWORD);
            } finally {
                 connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace( );
        } catch (SQLException e) {
            e.printStackTrace( );
        }
        return connection;
    }




    public static Connection getConnection2() {

        try {
            return DATASOURCE.getConnection();
        } catch (SQLException e) {
            e.printStackTrace( );
            throw new RuntimeException("数据库连接失败");
        }
    }



    /**
     *单例的方式
     * @return
     */
    public static DataSource getDataSource() {
        if (DATASOURCE2 == null) {
            DATASOURCE2 = new MysqlDataSource();
            ((MysqlDataSource) DATASOURCE2).setURL(URL);
            ((MysqlDataSource) DATASOURCE2).setUser(USERNAME);
            ((MysqlDataSource) DATASOURCE2).setPassword(PASSWORD);
        }
        return DATASOURCE2;
    }



}
