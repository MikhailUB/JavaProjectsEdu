package ru.Mikhail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;
import ru.Mikhail.model.Car;
import ru.Mikhail.exception.NotFoundException;
import ru.Mikhail.repositories.CarRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("cars")
public class CarsController {

    private final CarRepository carRepository;

    @Autowired
    public CarsController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping
    public List<Car> list() {
        Iterable<Car> all = carRepository.findAll();
        List<Car> cars = new ArrayList<>();
        all.forEach(cars::add);

        return cars;
    }

    @GetMapping("{id}")
    public Car getOne(@PathVariable Integer id) {
        return getCar(id);
    }

    private Car getCar(Integer id) {
        return carRepository
                .findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public Car create(@RequestBody Car car) {

        carRepository.save(car);
        return car;
    }

    @PutMapping("{id}")
    public Car update(@PathVariable Integer id, @RequestBody Car car) {
        Car carFromDb = getCar(id);
        carFromDb.setModel(car.getModel());
        carFromDb.setMaxSpeed(car.getMaxSpeed());
        carFromDb.setMileage(car.getMileage());
        carRepository.save(carFromDb);

        return carFromDb;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        try {
            carRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException(ex.getMessage());
        }
    }
}
