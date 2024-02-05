package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import racingcar.dto.CreateRaceRequest;
import racingcar.dto.CreateRaceResponse;
import racingcar.service.RaceService;

@Controller
public class RaceController {

    private final RaceService raceService;

    @Autowired
    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }

    @PostMapping("plays")
    public ResponseEntity<CreateRaceResponse> create(@RequestBody CreateRaceRequest request) {
        CreateRaceResponse response = raceService.create(request);
        return ResponseEntity.ok(response);
    }
}
