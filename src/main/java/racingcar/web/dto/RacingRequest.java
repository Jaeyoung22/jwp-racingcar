package racingcar.web.dto;

public class RacingRequest {

    private final String names;
    private final String count;

    public RacingRequest(String names, String count) {
        this.names = names;
        this.count = count;
    }

    public String getNames() {
        return names;
    }

    public String getCount() {
        return count;
    }
}
