package org.ordermanagement.dataAccesLayer;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/orderdb";
    private static final String USER = "root";
    private static final String PASS = "root";

    private static ConnectionFactory singleInstance = new ConnectionFactory();
    private ConnectionFactory(){
        try {
            Class.forName(DRIVER);
        } catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    private Connection createConnection() {
    Connection conexiune = null;
    try{
        conexiune = DriverManager.getConnection(DBURL,USER,PASS);
    }catch(SQLException e){
        LOGGER.log(Level.SEVERE,"Error while connecting to the database",e);
    }
    return conexiune;
    }
    public static Connection getConnection(){
        return singleInstance.createConnection();
    }
    public static void close(Connection connection){
        if(connection!=null){
            try{
                connection.close();
            }catch (SQLException e){
                LOGGER.log(Level.SEVERE,"Error while trying to close the connection",e);
            }
        }
    }
    public static void close(Statement statement){
    if(statement != null){
        try {
            statement.close();
        }catch (SQLException e){
          LOGGER.log(Level.SEVERE,"Error while trying to close the statement",e);
        }
    }
    }
    public static void close(ResultSet resultSet){
        if(resultSet!=null){
            try {
                resultSet.close();
            }catch (SQLException e){
                LOGGER.log(Level.SEVERE,"Error while trying to close result set",e);
            }
        }
    }
}
