import java.util.HashMap;
import java.util.Map;

public class Illuminate {
    Map<Integer, Character> brightness;

    Illuminate() {
        brightness = new HashMap<>();
        brightness.put(1,  '.');
        brightness.put(2,  ',');
        brightness.put(3,  '-');
        brightness.put(4,  '~');
        brightness.put(5,  ':');
        brightness.put(6,  ';');
        brightness.put(7,  '=');
        brightness.put(8,  '!');
        brightness.put(9,  '*');
        brightness.put(10, '#');
        brightness.put(11, '$');
        brightness.put(12, '@');
    }

    public Character getBrightness(float angle) {
        if (angle <= -0.833) {
            return brightness.get(12);
        } else if (angle > -0.833 && angle <= -0.667) {
            return brightness.get(11);
        } else if (angle > -0.667 && angle <= -0.500) {
            return brightness.get(10);
        } else if (angle > -0.500 && angle <= -0.334) {
            return brightness.get(9);
        } else if (angle > -0.334 && angle <= -0.167) {
            return brightness.get(8);
        } else if (angle > -0.167 && angle <= -0.000) {
            return brightness.get(7);
        } else if (angle > -0.000 && angle <= 0.166) {
            return brightness.get(6);
        } else if (angle > 0.166 && angle <= 0.333) {
            return brightness.get(5);
        } else if (angle > 0.333 && angle <= 0.499) {
            return brightness.get(4);
        } else if (angle > 0.499 && angle <= 0.666) {
            return brightness.get(3);
        } else if (angle > 0.666 && angle <= 0.833) {
            return brightness.get(2);
        } else {
            return brightness.get(1);
        }
    }
}
