package racingcar.service;

import camp.nextstep.edu.missionutils.Randoms;
import lombok.Getter;

@Getter
public class Car {
    private final String name;
    private int position = 0;

    public Car(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (name.length() > 5) {
            throw new IllegalArgumentException("[ERROR] 이름은 5자 이하이어야 합니다.");
        }

        if (name.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 이름은 1자 이상이어야 합니다.");
        }
    }

    public void move() {
        if (Randoms.pickNumberInRange(0, 9) >= 4) {
            position++;
        }
    }
}
