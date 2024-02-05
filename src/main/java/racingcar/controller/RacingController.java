package racingcar.controller;

import racingcar.model.CarHandler;
import racingcar.view.RacingView;

public class RacingController {
    private final CarHandler carHandler = new CarHandler();

    public void run() {
        startRacing();
        processRacing();
        RacingView.outputWinner(carHandler.getFirstCarName());
    }

    private void startRacing() {
        boolean ready = false;
        while (!ready) {
            try{
                carHandler.addCar(RacingView.inputCarName());
                ready = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void processRacing() {
        boolean ready = false;
        while (!ready) {
            try {
                moveRacingCar(validate(RacingView.inputTryNum()));
                ready = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void moveRacingCar(int tryNum) {
        RacingView.outputProcessStart();
        for (int i = 0; i<tryNum; i++) {
            RacingView.outputProcess(carHandler.moveCar());
        }
    }

    private int validate(String tryNumStr) {
        int tryNum;
        try {
            tryNum = Integer.parseInt(tryNumStr);
        } catch (Exception e) {
            throw new IllegalArgumentException("[ERROR] 시도 횟수가 숫자가 아닙니다.");
        }

        if (tryNum <= 0) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 시도 횟수입니다.");
        }
        return tryNum;
    }
}
