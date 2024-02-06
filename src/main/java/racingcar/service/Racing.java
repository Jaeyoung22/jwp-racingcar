package racingcar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import racingcar.domain.PlayResult;
import racingcar.domain.PlayerResult;
import racingcar.repository.PlayRepository;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Racing {
    private CarHandler carHandler;
    private final PlayRepository playRepository;

    public void run(String carNames, int tryNum) throws SQLException {
        carHandler = new CarHandler();
        startRacing(carNames);
        validate(tryNum);
        processRacing(tryNum);
        int id = playRepository.savePlay(new PlayResult(getWinners()));
        for (Car car: carHandler.getCarList()) {
            playRepository.savePlayer(id, new PlayerResult(car.getName(), car.getPosition()));
        }
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
