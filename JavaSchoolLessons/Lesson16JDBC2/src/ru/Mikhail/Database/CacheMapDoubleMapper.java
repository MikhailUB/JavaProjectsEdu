package ru.Mikhail.Database;

import ru.Mikhail.utils.*;

import java.sql.*;

public class CacheMapDoubleMapper implements Mapper<Double> {
    @Override
    public CacheMap<Double> map(ResultSet resultSet) throws SQLException {
        CacheMap<Double> cacheMap = new CacheMap<>();
        while (resultSet.next()) {
            cacheMap.put(
                    new CacheKey(resultSet.getString("Method"), resultSet.getString("StrArg"), resultSet.getInt("IntArg")),
                    resultSet.getDouble("Value"));
        }
        return cacheMap;
    }

    @Override
    public String[] getColumns() {
        return new String[] { "Method", "StrArg", "IntArg", "Value" };
    }

    @Override
    public Object[] getValues(CacheKey key, Double value) {
        Object[] keyValues = key.getFieldsValues();
        return new Object[] { keyValues[0], keyValues[1], keyValues[2], value };
    }
}
