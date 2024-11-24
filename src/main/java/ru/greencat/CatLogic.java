package ru.greencat;

import org.springframework.stereotype.Service;

@Service
public class CatLogic {
    public int logic(int count) {
        if (count == 0) {
            count = 1;
        }
        if (count % 2 == 0) {
            return count + 1;
        } else if (count % 3 == 0) {
            return count + 2;
        } else if (count % 5 == 0) {
            return count + 4;
        } else if (count % 7 == 0) {
            return count + 6;
        } else if (count % 11 == 0) {
            return count + 10;
        } else if (count % 13 == 0) {
            return count + 12;
        } else if (count % 17 == 0) {
            return count + 16;
        } else if (count % 19 == 0) {
            return count + 18;
        }
        return count + 12;
    }
}
