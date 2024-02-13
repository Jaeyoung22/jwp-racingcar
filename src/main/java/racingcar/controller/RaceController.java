package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import racingcar.dto.CreateRaceRequest;
import racingcar.dto.RaceResultDto;
import racingcar.service.RaceService;

import java.util.List;


@RestController
@RequestMapping("/plays")
public class RaceController {

    private final RaceService raceService;

    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }

    @GetMapping
    public ResponseEntity<List<RaceResultDto>> findAll() {
        return ResponseEntity.ok(raceService.findAll());
    }

    @PostMapping
    public ResponseEntity<RaceResultDto> create(@RequestBody CreateRaceRequest request) {
        RaceResultDto response = raceService.create(request);
        return ResponseEntity.ok(response);
    }
}
