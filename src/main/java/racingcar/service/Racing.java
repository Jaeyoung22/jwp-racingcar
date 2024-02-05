package racingcar.service;

import java.util.List;

public class Racing {
    private final CarHandler carHandler = new CarHandler();

    public void run(String carNames, int tryNum) {
        startRacing(carNames);
        validate(tryNum);
        processRacing(tryNum);
    }

    private void startRacing(String carNames) {
        carHandler.addCar(carNames);
    }

    private void processRacing(int tryNum) {
        for (int i = 0; i<tryNum; i++) {
            carHandler.moveCar();
        }
    }

    private void validate(int tryNum) {
        if (tryNum <= 0) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 시도 횟수입니다.");
        }
    }

    public String getWinners() {
        return String.join(", ", carHandler.getFirstCarName());
    }

    public List<Car> getCars() {
        return carHandler.getCarList();
    }
}
