package be.brussel.service.implementations;

import be.brussel.service.ConnectionManager;

import java.sql.*;


public class ConnectionManagerImpl implements ConnectionManager {

    private Connection connection;

    @Override
    public Connection createConnection(String url, String user, String pwd) throws SQLException {
        return connection = DriverManager.getConnection(url, user, pwd);
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void closeConnection() throws SQLException {
        connection.close();
        connection = null;
    }



}
