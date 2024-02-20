package racingcar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class RacingCarApplicationTests {

	@Autowired
	MockMvc mvc;

	@DisplayName("homeController 정상 작동 테스트")
	@Test
	void homeControllerTest() throws Exception {
		mvc.perform(get("/"))
				.andExpect(status().isOk());
	}

	@DisplayName("plays url post 정상 작동 테스트")
	@Test
	void postPlayTest() throws Exception {
		String body = "{\"names\":\"car1, car2, car3, car4\",\"count\":20}";

		mvc.perform(post("/plays")
						.contentType(MediaType.APPLICATION_JSON)
						.content(body))
				.andExpect(status().isOk());
	}

	@DisplayName("자동차 이름 입력 예외 발생 시 400에러 테스트")
	@Test
	void postCarNameExceptionTest() throws Exception {
		String body = "{\"names\":\"car1, car2, car3, car4567\",\"count\":20}";

		mvc.perform(post("/plays")
						.contentType(MediaType.APPLICATION_JSON)
						.content(body))
				.andExpect(status().isBadRequest());
	}

	@DisplayName("시도 횟수 입력 예외 발생 시 400에러 테스트")
	@Test
	void postTryNumExceptionTest() throws Exception {
		String body = "{\"names\":\"car1, car2, car3, car4\",\"count\":\"a\"}";

		mvc.perform(post("/plays")
						.contentType(MediaType.APPLICATION_JSON)
						.content(body))
				.andExpect(status().isBadRequest());
	}

	@DisplayName("plays url get 정상 작동 테스트")
	@Test
	void getPlayTest() throws Exception {
		String body = "{\"names\":\"car1, car2, car3, car4\",\"count\":20}";

		mvc.perform(post("/plays")
						.contentType(MediaType.APPLICATION_JSON)
						.content(body));

		mvc.perform(get("/plays"))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$..winners").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$..racingCars").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$..[0].name").value("car1"))
				.andExpect(MockMvcResultMatchers.jsonPath("$..[0].position").exists());
	}
}
