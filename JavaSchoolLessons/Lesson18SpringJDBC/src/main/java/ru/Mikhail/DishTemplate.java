package ru.Mikhail;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class DishTemplate {
    private final String tableName = "Dish";
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public DishTemplate(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public void addDish(Dish dish) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", dish.getId());
        map.addValue("name", dish.getName());

        jdbcTemplate.update("insert into " + tableName + "(Id, Name) VALUES(:id, :name)", map);
    }

    public List<Dish> findDishes(String partName) {
        List<Dish> dishes = jdbcTemplate.query("select Id, Name from Dish where lower(Name) like :part",
            new MapSqlParameterSource("part", "%" + partName + "%"),
                new RowMapper<Dish>() {
                public Dish mapRow(ResultSet rs, int i) throws SQLException {
                    return new Dish(rs.getInt("Id"), rs.getString("Name"));
                }
            });
        return dishes;
    }
}
