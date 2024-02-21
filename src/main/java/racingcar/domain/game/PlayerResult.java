package racingcar.domain.game;

public class PlayerResult {

    private int id;
    private final String name;
    private final int position;
    private final int playResult;

    public PlayerResult(int id, String name, int position, int playResult) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.playResult = playResult;
    }

    public PlayerResult(String name, int position, int playResult) {
        this.name = name;
        this.position = position;
        this.playResult = playResult;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public int getPlayResult() {
        return playResult;
    }
}
