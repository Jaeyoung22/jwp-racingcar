package racingcar.domain.game;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PlayerResultJdbcRepository implements PlayerResultRepository {

    private final NamedParameterJdbcTemplate template;

    public PlayerResultJdbcRepository(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<PlayerResult> saveAll(List<PlayerResult> playerResults) {
        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(playerResults.toArray());
        template.batchUpdate("insert into PLAYER_RESULT (name, position, play_result) values (:name, :position, :playResult)", batch);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("names", playerResults.stream().map(PlayerResult::getName).collect(Collectors.toList()));
        return template.query("select * from player_result where name in (:names)",
                parameters,
                new PlayerResultMapper()
        );
    }

}
