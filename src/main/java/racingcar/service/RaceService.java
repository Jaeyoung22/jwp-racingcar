package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.dto.CreateRaceRequest;
import racingcar.dto.CreateRaceResponse;
import racingcar.model.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RaceService {

    public CreateRaceResponse create(CreateRaceRequest request) {
        String names = request.getNames();
        int count = request.getCount();

        List<Car> cars = Arrays.stream(names.split(CarName.DELIMITER))
                .map(CarName::from)
                .map(Car::new)
                .collect(Collectors.toList());

        TrialCount trialCount = TrialCount.from(count);

        while (trialCount.decrement() > 0) {
            playTrial(cars);
        }

        RaceResult result = RaceResult.from(cars);
        return CreateRaceResponse.from(result);
    }

    private void playTrial(List<Car> cars) {
        for (Car car: cars) {
            if (pickRandomDistance() >= RaceConfig.MOVE_THRESHOLD) {
                car.move();
            }
        }
    }

    private int pickRandomDistance() {
        return (int)(Math.random() * 10);
    }
}
