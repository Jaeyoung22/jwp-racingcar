package racingcar.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import racingcar.dto.RacingInitInfo;
import racingcar.dto.RacingResult;
import racingcar.service.RacingService;
import racingcar.web.dto.RacingRequest;
import racingcar.web.dto.RacingResultResponse;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/plays")
@RestController
public class RacingController {

    private final RacingService racingService;

    public RacingController(RacingService racingService) {
        this.racingService = racingService;
    }

    @PostMapping
    public ResponseEntity<RacingResultResponse> create(@RequestBody RacingRequest racingRequest) {
        String names = racingRequest.getNames();
        String trialCount = racingRequest.getCount();
        RacingInitInfo racingInitInfo = new RacingInitInfo(names, trialCount);

        RacingResult result = racingService.race(racingInitInfo);

        RacingResultResponse response = RacingResultResponse.from(result);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<RacingResultResponse>> readAll() {
        List<RacingResult> racingResults = racingService.readAll();

        return ResponseEntity.ok(
                racingResults.stream()
                        .map(RacingResultResponse::from)
                        .collect(Collectors.toList())
        );
    }
}
