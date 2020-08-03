package ru.Mikhail.Database;

import ru.Mikhail.utils.*;
import java.sql.*;
import java.util.Map;

public class CacheMapDbSerializer<T> {
    private static String SELECT_SQL = "select * from %s";
    private static String INSERT_SQL = "insert into %s(%s) values (%s)";
    private static String DELETE_SQL = "delete from %s";

    private final String jdbcUrl;
    private final String user;
    private final String password;
    private final String driver;
    private final Mapper<T> mapper;
    private final String table;

    public CacheMapDbSerializer(String jdbcUrl, String user, String password, String driver, Mapper<T> mapper, String table) {
        this.jdbcUrl = jdbcUrl;
        this.user = user;
        this.password = password;
        this.driver = driver;
        this.mapper = mapper;
        this.table = table;
    }

    private void registerDriver() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(driver + " driver not present");
        }
    }

    public CacheMap<T> deserialize() {
        //registerDriver();
        try (
                Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
                Statement statement = connection.createStatement();
        ) {
            ResultSet resultSet = statement.executeQuery(String.format(SELECT_SQL, table));
            return mapper.map(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void serialize(CacheMap<T> map) {
        //registerDriver();
        String[] colNames = mapper.getColumns();
        String insertSql = String.format(INSERT_SQL,
                table, "\"" + String.join("\", \"", colNames) + "\"", getInsertParamsTemplate(colNames.length));

        try (
                Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
                Statement statement = connection.createStatement();
                PreparedStatement prepared = connection.prepareStatement(insertSql);
        ) {
            statement.executeUpdate(String.format(DELETE_SQL, table));

            for (Map.Entry<CacheKey, T> entry : map.entrySet()) {
                Object[] values = mapper.getValues(entry.getKey(), entry.getValue());
                for (int i = 0; i < values.length; i++) {
                    fillPreparedStatementParam(prepared, i, values[i]);
                }
                prepared.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fillPreparedStatementParam(PreparedStatement statement, int index, Object value) throws SQLException {
        index++; // параметры нумеруются c 1 (единицы)
        if (value instanceof String) {
            statement.setString(index, (String) value);
        } else if (value instanceof Integer) {
            statement.setInt(index, (Integer)value);
        } else if (value instanceof Double) {
            statement.setDouble(index, (Double)value);
        } else {
            throw new RuntimeException("Entity type " + value.getClass().getSimpleName() + " not support");
        }
    }

    private String getInsertParamsTemplate(int length) {
        String[] arr = new String[length];
        for (int i = 0; i < length; i++) {
            arr[i] = "?";
        }
        return String.join(", ", arr);
    }

}
