package ru.Mikhail;

import org.springframework.jdbc.core.namedparam.*;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class IngredientTemplate {
    private final String tableName = "Ingredient";
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public IngredientTemplate(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public void addIngredient(Ingredient ingredient) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("dishId", ingredient.getDishId());
        map.addValue("name", ingredient.getName());
        map.addValue("quantity", ingredient.getQuantity());

        jdbcTemplate.update("insert into " + tableName + "(DishId, Name, Quantity) VALUES(:dishId, :name, :quantity)", map);
    }
}
