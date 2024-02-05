package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import racingcar.dto.PlayRequest;
import racingcar.dto.PlayResponse;
import racingcar.service.Racing;

@Controller
public class PlayController {
    @PostMapping("/plays")
    public ResponseEntity<PlayResponse> playRacingGame(@RequestBody PlayRequest playRequest) {
        Racing racing = new Racing();
        try{
            racing.run(playRequest.getNames(), playRequest.getCount());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).build();
        }

        return ResponseEntity.ok().body(new PlayResponse(racing.getWinners(), racing.getCars()));
    }
}
