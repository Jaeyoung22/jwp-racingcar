package racingcar;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RacingController {

    private final RacingService racingService;

    public RacingController(RacingService racingService) {
        this.racingService = racingService;
    }

    @PostMapping(path = "/plays")
    public ResponseEntity<RacingResultResponse> play(@RequestBody RacingRequest racingRequest) {
        String names = racingRequest.getNames();
        String count = racingRequest.getCount();

        RacingResult result = racingService.play(names, count);

        RacingResultResponse response = RacingResultResponse.from(result);

        return ResponseEntity.ok(response);
    }
}
