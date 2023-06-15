package me.imsergioh.newgencore.backend;

import lombok.Getter;
import me.imsergioh.newgencore.util.ExceptionsUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

    @Getter
    private Connection connection;

    public MySQLConnection(String hostname, int port,
                           String database, String username, String password) {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + hostname + ":" + port + "/" + database, username, password);
        } catch (SQLException e) {
            ExceptionsUtil.handleSimpleException(e);
        }
    }

}
