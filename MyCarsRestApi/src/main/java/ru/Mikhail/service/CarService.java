package ru.Mikhail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import ru.Mikhail.exception.NotFoundException;
import ru.Mikhail.model.Car;
import ru.Mikhail.repositories.CarRepository;

import java.util.List;

@Service
public class CarService {
    private static final String SORT_FIELD = "id";

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Page<Car> findAll(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = size > 0 ? PageRequest.of(page, size, sort) : Pageable.unpaged();
        Page<Car> resultPage = carRepository.findAll(pageable);
        if (page < resultPage.getTotalPages()) {
            return resultPage;
        }
        throw new NotFoundException("Индекс страницы должен быть меньше количества страниц");
    }

    public Page<Car> findByModel(String model) {
        List<Car> cars = carRepository.findByModel(model);
        return new PageImpl<>(cars);
    }

    public Car getOne(Integer id) {
        return getCar(id);
    }

    private Car getCar(Integer id) {
        return carRepository
                .findById(id)
                .orElseThrow(NotFoundException::new);
    }

    public Car create(Car car) {
        carRepository.save(car);
        return car;
    }

    public Car update(Integer id, Car car) {
        Car carFromDb = getCar(id);
        carFromDb.setModel(car.getModel());
        carFromDb.setMaxSpeed(car.getMaxSpeed());
        carFromDb.setMileage(car.getMileage());
        carRepository.save(carFromDb);

        return carFromDb;
    }

    public void delete(Integer id) {
        try {
            carRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException(ex.getMessage());
        }
    }
}
