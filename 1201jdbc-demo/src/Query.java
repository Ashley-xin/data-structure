import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Query {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            try {
                //创建数据库连接
                connection = DBUtil.getConnection2();
                //根据数据库连接创建操作命令对象Statement
                statement = connection.createStatement();
                String sql = "select id,name,chinese,math,english from exam_result";
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getNString("name");
                    BigDecimal chinese = resultSet.getBigDecimal("chinese");
                    BigDecimal math = resultSet.getBigDecimal("math");
                    BigDecimal english = resultSet.getBigDecimal("english");
                    System.out.printf("id = %s, name = %s, chinese = %s, math = %s,english = %s"
                            ,id,name,chinese,math,english);
                    System.out.println( );
                }
            } finally {
                //释放
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
