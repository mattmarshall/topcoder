package samples;

public class IdentifyingWood {

    boolean doCheck(String s, String t) {
        if (s == null || t == null || t.length() > s.length()) {
            return false;
        }

        // Iterate through searchable characters
        int lastIdx = 0;
        boolean found = false;
        for (char tc : t.toCharArray()) {
            
            // Iterate through string in which to search
            for (int i = lastIdx; i < s.length(); i++) {
                lastIdx++;
                char sc = s.charAt(i);
                if (sc == tc) {
                    found = true;
                    break;
                }
            }
            
            // Signal that it's found
            if (!found) {
                return false;
            }
            
            // Reset found
            found = false;
        }

        return true;
    }

    public String check(String s, String t) {
        return doCheck(s, t) ? "Yep, it's wood." : "Nope.";
    }
    
    public static void main(String[] args) {
        IdentifyingWood wood = new IdentifyingWood();
        System.out.println(wood.check("absdefgh", "asdf"));
        System.out.println(wood.check("oxoxoxox", "ooxxoo"));
        System.out.println(wood.check("oxoxoxox", "xxx"));
        System.out.println(wood.check("qwerty", "qwerty"));
        System.out.println(wood.check("string", "longstring"));
    }
}
