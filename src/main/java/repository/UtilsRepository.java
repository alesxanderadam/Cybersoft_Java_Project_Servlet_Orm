package repository;

import config.MysqlConfig;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public abstract class UtilsRepository {
    public <T> List<T> findAllModels(String tableName, String[] columnNames, Class<T> modelClass) {
        Connection connection = null;
        List<T> listModels = new ArrayList<>();
        try {
            String sql = "SELECT * FROM " + tableName;
            PreparedStatement statement = MysqlConfig.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                T model = modelClass.getDeclaredConstructor().newInstance();

                for (String columnName : columnNames) {
                    Field field = modelClass.getDeclaredField(columnName);
                    field.setAccessible(true);

                    // Lấy giá trị từ ResultSet dựa trên tên cột
                    Object value = resultSet.getObject(columnName);

                    // Thiết lập giá trị cho thuộc tính trong entity
                    field.set(model, value);
                }

                listModels.add(model);
            }
        } catch (Exception e) {
            System.out.println("Error findAllModels: " + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                System.out.println("Error findAllModels finally " + e.getMessage());
            }
        }
        return listModels;
    }

    public <T> T findModelsByIds(String tablename, String[] columnNames, String idColumnName, Object idValue, Class<T> modelClass){
        T model = null;
        try(Connection connection = MysqlConfig.getConnection()) {
            String sql = "SELECT * FROM " + tablename +  " WHERE " + idColumnName + " = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1, idValue);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                model = modelClass.getDeclaredConstructor().newInstance();
                for (String columnName : columnNames) {
                    Field field = modelClass.getDeclaredField(columnName);
                    field.setAccessible(true);

                    Object value = resultSet.getObject(columnName);
                    field.set(model, value);
                }
            }
        }
        catch (Exception e) {
            System.out.println("Error findModelById: " + e.getMessage());
        }
        return model;
    }
    public <T> boolean updateModelsById(String tableName, String[] columnNames, String idColumnName, Object idValue, T model) {
        boolean isSuccess = false;

        try (Connection connection = MysqlConfig.getConnection()) {
            StringBuilder sqlBuilder = new StringBuilder();
            sqlBuilder.append("UPDATE ").append(tableName).append(" SET ");

            for (int i = 0; i < columnNames.length; i++) {
                sqlBuilder.append(columnNames[i]).append(" = ?");
                if (i < columnNames.length - 1) {
                    sqlBuilder.append(", ");
                }
            }

            sqlBuilder.append(" WHERE ").append(idColumnName).append(" = ?");
            String sql = sqlBuilder.toString();

            PreparedStatement statement = connection.prepareStatement(sql);

            int parameterIndex = 1;
            for (String columnName : columnNames) {
                Field field = model.getClass().getDeclaredField(columnName);
                field.setAccessible(true);

                Object value = field.get(model);
                statement.setObject(parameterIndex++, value);
            }

            statement.setObject(parameterIndex, idValue);

            int rowsAffected = statement.executeUpdate();
            isSuccess = rowsAffected > 0;
        } catch (Exception e) {
            System.out.println("Error updateModelById: " + e.getMessage());
        }

        return isSuccess;
    }
    public boolean deleteById(int id, String tableName, String sql){
        boolean isSuccess = false;
        try(Connection connection = MysqlConfig.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            isSuccess = statement.executeUpdate() > 0;
        }
        catch (Exception Error){
            System.out.println("Until repo delete error " + Error);
        }
        return isSuccess;
    }
}
