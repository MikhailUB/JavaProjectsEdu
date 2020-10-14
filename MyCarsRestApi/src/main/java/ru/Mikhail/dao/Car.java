package ru.Mikhail.dao;

public class Car {
    private Long id;
    private String model;
    private Integer maxSpeed;
    private Integer mileage;

    public Car() {
    }

    public Car(long id, String model, int maxSpeed, int mileage) {
        this.id = id;
        this.model = model;
        this.maxSpeed = maxSpeed;
        this.mileage = mileage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Integer maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }
}
