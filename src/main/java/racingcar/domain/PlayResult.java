package racingcar.domain;


import lombok.Getter;

import java.sql.Date;

@Getter
public class PlayResult {
    private int id;
    private String winners;
    private Date createdAt;

    public PlayResult(String winners) {
        this.winners = winners;
    }
    public PlayResult(int id, String winners) {
        this.id = id;
        this.winners = winners;
    }
}
