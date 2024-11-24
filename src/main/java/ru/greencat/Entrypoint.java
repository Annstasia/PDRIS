package ru.greencat;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class Entrypoint {
    private final CatRepo catRepo;

    public static void main(String[] args) {
        SpringApplication.run(Entrypoint.class, args);
    }

    @PostConstruct
    public void initCat() {
        catRepo.save(new CatCounter());
    }
}
