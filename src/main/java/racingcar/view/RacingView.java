package racingcar.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RacingView {
    public static String inputCarName() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        return Console.readLine();
    }

    public static String inputTryNum() {
        System.out.println("시도할 회수는 몇회인가요?");
        return Console.readLine();
    }

    public static void outputProcessStart() {
        System.out.println("실행 결과");
    }

    public static void outputProcess(HashMap<String, Integer> result) {
        for (Map.Entry<String, Integer> entry : result.entrySet()) {
            String name = entry.getKey();
            String value = "";
            for (int i = 0; i<entry.getValue(); i++) {
                value += "-";
            }
            System.out.println(name + " : " + value);
        }
        System.out.println();
    }

    public static void outputWinner(List<String> winners) {
        System.out.println("최종 우승자 : " + String.join(", ", winners));
    }
}
