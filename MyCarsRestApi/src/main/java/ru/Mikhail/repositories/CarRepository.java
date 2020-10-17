package ru.Mikhail.repositories;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.Mikhail.model.Car;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {

    List<Car> findByModel(String model);
}
