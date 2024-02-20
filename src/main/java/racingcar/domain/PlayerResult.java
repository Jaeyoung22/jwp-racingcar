package racingcar.domain;

import lombok.Getter;

@Getter
public class PlayerResult {
    private int id;
    private String name;
    private int position;

    public PlayerResult(String name, int position) {
        this.name = name;
        this.position = position;
    }
}
