package ru.Mikhail.controller;

import org.springframework.web.bind.annotation.*;
import ru.Mikhail.dao.Car;
import ru.Mikhail.exception.NotFoundException;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("cars")
public class CarsController {
    private static List<Car> list = Arrays.asList(
            new Car(1, "Mercedes", 220, 242000),
            new Car(2, "BMW", 240, 22000),
            new Car(3, "Ford", 190, 78000)
    );

    @GetMapping
    public List<Car> list() {

        return list;
    }

    @GetMapping("{id}")
    public Car getOne(@PathVariable Long id) {
        return list.stream()
            .filter(c -> c.getId().equals(id))
            .findFirst()
            .orElseThrow(NotFoundException::new);
    }
}
