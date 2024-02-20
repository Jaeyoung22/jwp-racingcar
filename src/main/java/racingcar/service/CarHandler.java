package racingcar.service;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CarHandler {
    private final List<Car> carList;
    private int maxPosition = 0;

    public CarHandler() {
        carList = new ArrayList<>();
    }

    public void addCar(String carNameStr) {
        String[] carNameArray = carNameStr.split(",");
        for (String carName: carNameArray) {
            carList.add(new Car(carName.trim()));
        }
    }

    public void moveCar() {
        for (Car car: carList) {
            car.move();
            if (car.getPosition() > maxPosition) {
                maxPosition = car.getPosition();
            }
        }
    }

    public List<String> getFirstCarName() {
        List<String> firstCars = new ArrayList<>();
        for (Car car: carList) {
            if (car.getPosition() == maxPosition) {
                firstCars.add(car.getName());
            }
        }
        return firstCars;
    }
}