package racingcar.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import racingcar.dto.CarDto;
import racingcar.dto.RaceResultDto;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
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

    @Override
    public List<RaceResultDto> findAll() {
        String carSql = "SELECT id, name, position, race_result_id FROM race_cars";
        List<CarDto> cars = jdbcTemplate.query(carSql, carDtoRowMapper());

        String raceSql = "SELECT id, trial_count, winners FROM race_results";
        List<RaceResultDto> results = jdbcTemplate.query(raceSql, raceResultDtoRowMapper());

        joinResult(cars, results);

        return results;
    }

    private void joinResult(List<CarDto> cars, List<RaceResultDto> results) {
        HashMap<String, RaceResultDto> resultMap = new HashMap<>();
        for (RaceResultDto result: results) {
            resultMap.put(result.getId(), result);
        }

        for (CarDto car: cars) {
            RaceResultDto result = resultMap.get(car.getRaceId());
            if (result == null) {
                throw new RuntimeException("데이터베이스 무결성 오류");
            }
            result.getRacingCars().add(car);
        }
    }

    private RowMapper<RaceResultDto> raceResultDtoRowMapper() {
        return (rs, rowNum) -> new RaceResultDto(
                rs.getString("id"),
                rs.getInt("trial_count"),
                rs.getString("winners"),
                new ArrayList<>()
        );
    }

    private RowMapper<CarDto> carDtoRowMapper() {
        return (rs, rowNum) -> new CarDto(
                rs.getString("id"),
                rs.getString("name"),
                rs.getInt("position"),
                rs.getString("race_result_id")
        );
    }
}
