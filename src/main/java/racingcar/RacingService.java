package racingcar;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class RacingService {

    public RacingResult play(String names, String count) {
        Cars cars = CarsMaker.make(names);
        int number = Integer.parseInt(count);

        Game game = new Game(cars, number);
        game.play();
        game.end();

        System.out.println(game);
        return RacingResult.from(cars);
    }
}
