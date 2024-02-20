package racingcar.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Controller
public class HomeController {
    @GetMapping("/")
    public ResponseEntity<String> home() throws IOException {
        ClassPathResource resource = new ClassPathResource("/static/index.html");

        byte[] bytes = Files.readAllBytes(Path.of(resource.getURI()));
        String content = new String(bytes, StandardCharsets.UTF_8);

        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(content);
    }
}
