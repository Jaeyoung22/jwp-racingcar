package racingcar.domain.game;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class PlayResultJdbcRepository implements PlayResultRepository {

    private final NamedParameterJdbcTemplate template;

    public PlayResultJdbcRepository(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Override
    public PlayResult save(PlayResult playResult) {
        String sql = "insert into PLAY_RESULT (trial_count, winners, played_at) values (:trialCount, :winners, :playedAt)";

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("trialCount", playResult.getTrialCount())
                .addValue("winners", String.join(", ", playResult.getWinners()))
                .addValue("playedAt", playResult.getPlayedAt());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(sql, parameters, keyHolder);

        return template.queryForObject("select * from PLAY_RESULT where id = :ID", keyHolder.getKeys(), new PlayResultMapper());
    }
}
