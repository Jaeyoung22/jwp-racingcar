package racingcar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import racingcar.dto.PlayRequest;
import racingcar.dto.PlayResponse;
import racingcar.service.Racing;

import java.sql.SQLException;

@Controller
@RequiredArgsConstructor
public class PlayController {
    private final Racing racing;

    @PostMapping("/plays")
    public ResponseEntity<PlayResponse> playRacingGame(@RequestBody PlayRequest playRequest) {
        try{
            racing.run(playRequest.getNames(), playRequest.getCount());
        } catch (IllegalArgumentException | SQLException e) {
            return ResponseEntity.status(400).build();
        }

        return ResponseEntity.ok().body(new PlayResponse(racing.getWinners(), racing.getCars()));
    }
}
