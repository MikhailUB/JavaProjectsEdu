package ru.Mikhail.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.Mikhail.model.Car;

public interface CarRepository extends CrudRepository<Car, Integer> {
}
