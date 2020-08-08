package ru.Mikhail;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

public class BaseTemplate {
    protected final String tableName;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public BaseTemplate(String tableName, DataSource dataSource) {
        this.tableName = tableName;
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
}
