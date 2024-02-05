package racingcar.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public HashMap<String, Integer> moveCar() {
        HashMap<String, Integer> position = new HashMap<String, Integer>();
        for (Car car: carList) {
            car.move();
            position.put(car.getName(), car.getPosition());
            if (car.getPosition() > maxPosition) {
                maxPosition = car.getPosition();
            }
        }
        return position;
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