package racingcar;

public class Application {

    public static void main(String[] args) {
        Game game = Game.getInstance();
        game.play();
        game.end();
    }
}
