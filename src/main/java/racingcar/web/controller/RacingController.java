package racingcar.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.RacingInitInfo;
import racingcar.dto.RacingResult;
import racingcar.service.RacingService;
import racingcar.web.dto.RacingRequest;
import racingcar.web.dto.RacingResultResponse;

@RestController
public class RacingController {

    private final RacingService racingService;

    public RacingController(RacingService racingService) {
        this.racingService = racingService;
    }

    @PostMapping(path = "/plays")
    public ResponseEntity<RacingResultResponse> play(@RequestBody RacingRequest racingRequest) {
        String names = racingRequest.getNames();
        String trialCount = racingRequest.getCount();
        RacingInitInfo racingInitInfo = new RacingInitInfo(names, trialCount);

        RacingResult result = racingService.race(racingInitInfo);

        RacingResultResponse response = RacingResultResponse.from(result);
        return ResponseEntity.ok(response);
    }
}
