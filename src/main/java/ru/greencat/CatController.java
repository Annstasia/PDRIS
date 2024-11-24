package ru.greencat;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CatController {
    private final CatRepo catRepo;
    private final CatLogic logic;

    @GetMapping("/get")
    public ResponseEntity<String> getCat() {
        CatCounter catCounter = catRepo.findAll().get(0);
        catCounter.setCounter(logic.logic(catCounter.getCounter()));
        catRepo.save(catCounter);
        return ResponseEntity.ok("Green cat: " + catCounter.counter);
    }
}
