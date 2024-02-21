package racingcar.domain.game;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerResultMapper implements RowMapper<PlayerResult> {

    @Override
    public PlayerResult mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        int position = rs.getInt("position");
        int playResult = rs.getInt("play_result");

        return new PlayerResult(id, name, position, playResult);
    }
}
