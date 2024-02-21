package racingcar.console;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import racingcar.console.controller.RacingConsoleController;
import racingcar.domain.game.PlayResultJdbcRepository;
import racingcar.domain.game.PlayResultRepository;
import racingcar.domain.game.PlayerResultJdbcRepository;
import racingcar.domain.game.PlayerResultRepository;
import racingcar.service.RacingService;

import javax.sql.DataSource;

public class ConsoleApplication {

    public static void main(String[] args) {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("");
        config.setJdbcUrl("");
        DataSource dataSource = new HikariDataSource(config);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        PlayResultRepository playResultRepository = new PlayResultJdbcRepository(namedParameterJdbcTemplate);
        PlayerResultRepository playerResultRepository = new PlayerResultJdbcRepository(namedParameterJdbcTemplate);
        RacingService racingService = new RacingService(playResultRepository, playerResultRepository);
        RacingConsoleController racingConsoleController = new RacingConsoleController(racingService);

        racingConsoleController.race();
    }
}
