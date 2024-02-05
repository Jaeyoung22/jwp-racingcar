package racingcar.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.dto.RaceResultDto;

import javax.sql.DataSource;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JdbcRaceResultRepository implements RaceResultRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcRaceResultRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(RaceResultDto race) {
        // Create race
        String raceSql = "INSERT INTO race_results (id, trial_count, winners) VALUES (?, ?, ?)";
        Object[] args = {
                race.getId(),
                race.getTrialCount(),
                race.getWinners()
        };
        jdbcTemplate.update(raceSql, args);

        // Create cars
        String carSql = "INSERT INTO race_cars (id, name, position, race_result_id) VALUES (?, ?, ?, ?)";
        List<Object[]> batchArgs = race.getRacingCars()
                .stream()
                .map(car -> new Object[]{
                        car.getId(),
                        car.getName(),
                        car.getPosition(),
                        race.getId()
                })
                .collect(Collectors.toList());
        jdbcTemplate.batchUpdate(carSql, batchArgs);
    }

}
