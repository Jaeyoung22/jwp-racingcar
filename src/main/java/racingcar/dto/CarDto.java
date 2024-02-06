package racingcar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CarDto {
    private String name;
    private int position;
}
