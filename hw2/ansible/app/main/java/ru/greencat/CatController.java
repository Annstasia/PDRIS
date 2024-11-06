package ru.greencat;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatController {
    @GetMapping("/get")
    public ResponseEntity<String> getCat() {
        return ResponseEntity.ok("Green cat");
    }
}
