package ru.Mikhail;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        DishTemplate dishTemplate = context.getBean(DishTemplate.class);

        boolean initDatabase = false;
        if (initDatabase) {
            initDatabase(context);
        }

        List<Dish> dishes = dishTemplate.findDishes("ясная");
        for (Dish dish : dishes) {
            System.out.println(dish);
        }

        context.close();

        System.out.println("Executed");
    }

    private static void initDatabase(AnnotationConfigApplicationContext context) {
        // добавляем одно блюдо
        Dish dish = new Dish(1, "Солянка сборная мясная");
        List<Ingredient> ingredients = new ArrayList<Ingredient>();
        ingredients.add(new Ingredient(dish.getId(), "Оливки", 100));
        ingredients.add(new Ingredient(dish.getId(), "Огурцы маринованные", 150));
        ingredients.add(new Ingredient(dish.getId(), "Говядина", 200));
        addDish(context, dish, ingredients);

        // добавляем второе блюдо
        dish = new Dish(2, "Мясная тарелка");
        ingredients = new ArrayList<Ingredient>();
        ingredients.add(new Ingredient(dish.getId(), "Свинина отварная", 250));
        ingredients.add(new Ingredient(dish.getId(), "Говядина копченая", 250));
        addDish(context, dish, ingredients);
    }


    private static void addDish(AnnotationConfigApplicationContext context, Dish dish, List<Ingredient> ingredients)
    {
        DishTemplate dishTemplate = context.getBean(DishTemplate.class);
        IngredientTemplate ingrTemplate = context.getBean(IngredientTemplate.class);

        dishTemplate.addDish(dish);

        for (Ingredient ingr : ingredients) {
            ingrTemplate.addIngredient(ingr);
        }
    }
}
