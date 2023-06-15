package me.imsergioh.newgencore.instance.data;

import me.imsergioh.newgencore.NewGenCore;
import me.imsergioh.newgencore.util.ExceptionsUtil;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLStorage implements DataStorage {

    private PreparedStatement loadStatement;
    private PreparedStatement saveStatement;
    private PreparedStatement deleteStatement;


    public MySQLStorage(String loadSQL,
                        String saveSQL,
                        String deleteSQL) {
        try {
            Connection connection = NewGenCore.getMySQLConnection().getConnection();
            loadStatement = connection.prepareStatement(loadSQL);
            saveStatement = connection.prepareStatement(saveSQL);
            deleteStatement = connection.prepareStatement(deleteSQL);
        } catch (SQLException e) {
            ExceptionsUtil.handleSimpleException(e);
        }
    }

    @Override
    public void save(LocalData data, Object queryObject) {
        try {
            saveStatement.setObject(1, queryObject);
            saveStatement.setString(2, data.toString());

            saveStatement.executeUpdate();
        } catch (SQLException e) {
            ExceptionsUtil.handleSimpleException(e);
        }
    }

    @Override
    public LocalData load(Object queryObject) {
        try {
            loadStatement.setObject(1, queryObject);

            ResultSet resultSet = loadStatement.executeQuery();
            if (resultSet.next()) {
                String jsonData = resultSet.getString("data");
                JSONObject jsonObject = new JSONObject(jsonData);
                return new LocalData(jsonObject);
            }
        } catch (SQLException e) {
            ExceptionsUtil.handleSimpleException(e);
        }
        return null;
    }

    @Override
    public void delete(Object queryObject) {
        try {
            deleteStatement.setObject(1, queryObject);
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
