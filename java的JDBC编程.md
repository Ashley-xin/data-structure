## java的JDBC编程

### JDBC的介绍

JDBC，即Java Database Connectivity，java数据库连接。是一种用于执行SQL语句的Java API，它是
Java中的数据库连接规范。这个API由 java.sql.*,javax.sql.* 包中的一些类和接口组成，它为Java
开发人员操作数据库提供了一个标准的API，可以为多种关系数据库提供统一访问。

### java数据编程的必备条件

编程语言 ： java.

数据库：Oracle，MySQL，SQL Server，这里我使用的是mysql

数据库驱动包：不同的数据库，对应不同的编程语言提供了不同的数据库驱动包，如：MySQL提
供了Java的驱动包mysql-connector-java，需要基于Java操作MySQL即需要该驱动包。

### JDBC的工作原理



### JDBC的使用

idea + mysql

1.准备数据库驱动包，并添加到项目的依赖中：
在项目中创建文件夹lib，并将依赖包mysql-connector-java-5.1.47.jar复制到lib中。再配置该jar
包到本项目的依赖中：右键点击项目Open Module Settings，在Modules中，点击项目，配置
Dependencies，点击+，JARS or Directories，选择这个项目的lib文件夹，将该lib文件夹配置进依赖中，表示该文件夹下的jar包都引入作为依赖。

2.数据库连接Connection

有两种方式

一种是通过DriverManager（驱动管理类）的静态方法获取：

```java 
 Connection connection = null;
 /* 加载JDBC驱动程序：反射，这样调用初始化com.mysql.jdbc.Driver类，即将该类加载到JVM方法区，并执行该类的静态方法块、静态属性。*/
 Class.forName("com.mysql.jdbc.Driver");
  // 创建数据库连接
 connection = DriverManager.getConnection ( "jdbc:mysql://localhost:3306/test?
user=root&password=root&useUnicode=true&characterEncoding=UTF-8");
           
```

一种是通过DataSource（数据源）对象获取。实际应用中会使用DataSource对象。

```java 
DataSource ds = new MysqlDataSource();
((MysqlDataSource) ds).setUrl("jdbc:mysql://localhost:3306/test");
((MysqlDataSource) ds).setUser("root");
((MysqlDataSource) ds).setPassword("root");
Connection connection = ds.getConnection();
```

区别;

1. DriverManager类来获取的Connection连接，是无法重复利用的，每次使用完以后释放资源
    时，通过connection.close()都是关闭物理连接。

2. DataSource提供连接池的支持。连接池在初始化时将创建一定数量的数据库连接，这些连接
    是可以复用的，每次使用完数据库连接，释放资源调用connection.close()都是将
    Conncetion连接对象回收。

3. 创建操作命令（Statement）

  ```java
  Statement statement = connection.createStatement();
  ```

4.执行SQL语句

```java
ResultSet resultSet= statement.executeQuery(
        "select id, sn, name, qq_mail, classes_id from student");
```

5.处理结果集

```java
while (resultSet.next()) {
      int id = resultSet.getInt("id");
      String sn = resultSet.getString("sn");
      String name = resultSet.getString("name");
      int classesId = resultSet.getInt("classes_id");
      System.out.println(String.format("Student: id=%d, sn=%s, name=%s,
classesId=%s", id, sn, name, classesId));
}
```

6.释放资源（关闭结果集，命令，连接）

```java
//关闭结果集
if (resultSet != null) {
  try {
    resultSet.close();
 } catch (SQLException e) {
    e.printStackTrace();
 }
    }
//关闭命令
if (statement != null) {
  try {
    statement.close();
 } catch (SQLException e) {
    e.printStackTrace();
 }
}
//关闭连接命令
if (connection != null) {
  try {
    connection.close();
 } catch (SQLException e) {
    e.printStackTrace();
 }
}
    
```

