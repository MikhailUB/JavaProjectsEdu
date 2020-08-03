package ru.Mikhail.Database;

import ru.Mikhail.utils.CacheKey;
import ru.Mikhail.utils.CacheMap;

import java.sql.*;

public interface Mapper<T> {
    CacheMap<T> map(ResultSet resultSet) throws SQLException;
    String[] getColumns();
    Object[] getValues(CacheKey key, T value);
}