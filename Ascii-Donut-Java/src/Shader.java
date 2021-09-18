import java.util.HashMap;
import java.util.Map;

public class Shader {
    Map<Integer, Character> brightness;

    Shader() {
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

    public Character getBrightness(float dotP) {
        if (dotP <= -0.833) {
            return brightness.get(12);
        } else if (dotP > -0.833 && dotP <= -0.667) {
            return brightness.get(11);
        } else if (dotP > -0.667 && dotP <= -0.500) {
            return brightness.get(10);
        } else if (dotP > -0.500 && dotP <= -0.334) {
            return brightness.get(9);
        } else if (dotP > -0.334 && dotP <= -0.167) {
            return brightness.get(8);
        } else if (dotP > -0.167 && dotP <= -0.000) {
            return brightness.get(7);
        } else if (dotP > -0.000 && dotP <= 0.166) {
            return brightness.get(6);
        } else if (dotP > 0.166 && dotP <= 0.333) {
            return brightness.get(5);
        } else if (dotP > 0.333 && dotP <= 0.499) {
            return brightness.get(4);
        } else if (dotP > 0.499 && dotP <= 0.666) {
            return brightness.get(3);
        } else if (dotP > 0.666 && dotP <= 0.833) {
            return brightness.get(2);
        } else {
            return brightness.get(1);
        }
    }
}
