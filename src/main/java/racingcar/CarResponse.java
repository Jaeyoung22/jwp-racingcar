package racingcar;

public class CarResponse {

    private final String name;
    private final int position;

    private CarResponse(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public static CarResponse of(String name, int position) {
        return new CarResponse(name, position);
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
