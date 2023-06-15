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

    private Object fieldQuery;

    public MySQLStorage(String loadSQL,
                        String saveSQL,
                        String deleteSQL,
                        Object fieldQuery) {
        this.fieldQuery = fieldQuery;
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
    public void save(LocalData data) {
        try {
            // Crear un objeto JSON a partir de los datos del jugador
            JSONObject jsonData = new JSONObject();

            // Guardar los datos del jugador en la tabla playerdata
            saveStatement.setObject(1, fieldQuery);
            saveStatement.setString(2, jsonData.toString());

            saveStatement.executeUpdate();
        } catch (SQLException e) {
            ExceptionsUtil.handleSimpleException(e);
        }
    }

    @Override
    public LocalData load() {
        try {
            loadStatement.setObject(1, fieldQuery);

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
    public void delete() {
        try {
            deleteStatement.setObject(1, fieldQuery);
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
