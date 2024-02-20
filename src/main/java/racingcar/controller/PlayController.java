package racingcar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import racingcar.dto.PlayRequest;
import racingcar.dto.PlayResponse;
import racingcar.service.PlaysService;
import racingcar.service.Racing;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PlayController {
    private final Racing racing;
    private final PlaysService playsService;

    @PostMapping("/plays")
    public ResponseEntity<PlayResponse> playRacingGame(@RequestBody PlayRequest playRequest) {
        try{
            racing.run(playRequest.getNames(), playRequest.getCount());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).build();
        }

        return ResponseEntity.ok().body(new PlayResponse(racing.getWinners(), racing.getCars()));
    }

    @GetMapping("/plays")
    public ResponseEntity<List<PlayResponse>> getPlays() {
        return ResponseEntity.ok().body(playsService.getPlays());
    }
}
