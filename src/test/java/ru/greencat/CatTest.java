package ru.greencat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class CatTest {
    private final CatRepo repo = Mockito.mock(CatRepo.class);
    private final CatController controller = new CatController(repo, new CatLogic());

    @Test
    public void someTest() {
        CatCounter counter = new CatCounter();
        counter.setId(1L);
        counter.setCounter(0);
        Mockito.when(repo.findAll()).thenReturn(List.of(counter));
        Assertions.assertEquals(ResponseEntity.ok("Green cat: " + 13), controller.getCat());
    }
}
