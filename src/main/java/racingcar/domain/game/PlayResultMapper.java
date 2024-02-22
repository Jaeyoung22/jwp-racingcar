package racingcar.domain.game;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PlayResultMapper implements RowMapper<PlayResult> {

    @Override
    public PlayResult mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt("id");
        int trialCount = rs.getInt("trial_count");
        List<String> winners = Arrays.stream(rs.getString("winners").split(","))
                .map(String::strip)
                .collect(Collectors.toList());
        LocalDateTime playedAt = rs.getTimestamp("played_at").toLocalDateTime();

        return new PlayResult(id, trialCount, winners, playedAt);
    }
}
