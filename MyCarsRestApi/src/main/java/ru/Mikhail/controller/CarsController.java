package ru.Mikhail.controller;

import org.springframework.web.bind.annotation.*;
import ru.Mikhail.dao.Car;
import ru.Mikhail.exception.NotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("cars")
public class CarsController {
    private static List<Car> cars = new ArrayList<>(
            Arrays.asList(
                    new Car(1, "Mercedes", 220, 242000),
                    new Car(2, "BMW", 240, 22000),
                    new Car(3, "Ford", 190, 78000)
            )
    );

    @GetMapping
    public List<Car> list() {
        return cars;
    }

    @GetMapping("{id}")
    public Car getOne(@PathVariable Long id) {
        return getCar(id);
    }

    private Car getCar(@PathVariable Long id) {
        return cars.stream()
            .filter(c -> c.getId().equals(id))
            .findFirst()
            .orElseThrow(NotFoundException::new);
    }

    private Long getMaxId() {
        return cars.stream()
                .mapToLong(Car::getId)
                .max()
                .orElse(0);
    }

    @PostMapping
    public Car create(@RequestBody Car car) {
        car.setId(getMaxId() + 1);
        cars.add(car);

        return car;
    }

    @PutMapping("{id}")
    public Car update(@PathVariable Long id, @RequestBody Car car) {
        Car carFromDb = getCar(id);
        carFromDb.setModel(car.getModel());
        carFromDb.setMaxSpeed(car.getMaxSpeed());
        carFromDb.setMileage(car.getMileage());

        return carFromDb;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        Car car = getCar(id);

        cars.remove(car);
    }
}
