package samples;

import java.util.HashSet;
import java.util.Set;

public class ImageDithering {
    
    public int count(String dithered, String[] screen) {
        final Set<Character> colors = new HashSet<>();
        for (Character c : dithered.toCharArray()) {
            colors.add(c);
        }
        int count = 0;
        for (String line : screen) {
            for (Character c : line.toCharArray()) {
                if (colors.contains(c)) {
                    count++;
                }
            }
        }
        return count;
    }
    
}
