package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import racingcar.dto.CreateRaceRequest;
import racingcar.dto.RaceResultDto;
import racingcar.service.RaceService;

import java.util.List;

@Controller
public class RaceController {

    private final RaceService raceService;

    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }

    @PostMapping("plays")
    public ResponseEntity<RaceResultDto> create(@RequestBody CreateRaceRequest request) {
        RaceResultDto response = raceService.create(request);
        return ResponseEntity.ok(response);
    }
}
