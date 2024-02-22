package racingcar.web.dto;

public class PlayerResultResponse {

    private final String name;
    private final int position;

    private PlayerResultResponse(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public static PlayerResultResponse of(String name, int position) {
        return new PlayerResultResponse(name, position);
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
