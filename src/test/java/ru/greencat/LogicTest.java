package ru.greencat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LogicTest {
    private final CatLogic logic = new CatLogic();

    @Test
    public void testLogic() {
        Assertions.assertEquals(13, logic.logic(0));
        Assertions.assertEquals(13, logic.logic(1));
        Assertions.assertEquals(3, logic.logic(2));
        Assertions.assertEquals(5, logic.logic(3));
        Assertions.assertEquals(9, logic.logic(5));
        Assertions.assertEquals(13, logic.logic(7));
        Assertions.assertEquals(21, logic.logic(11));
        Assertions.assertEquals(25, logic.logic(13));
        Assertions.assertEquals(33, logic.logic(17));
        Assertions.assertEquals(37, logic.logic(19));
        Assertions.assertEquals(35, logic.logic(23));
    }
}
