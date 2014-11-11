package samples;

public class CCipher {

    public String decode(String cipherText, int shift) {
        StringBuilder sb = new StringBuilder();
        int offset = -shift % 26 + 26;        
        for (char c : cipherText.toCharArray()) {
            char s = (char) ('A' + (c - 'A' + offset) % 26 );
            sb.append(s);
        }
        return sb.toString();
    }
    
    public static void main(String[] args) {
        
        CCipher c =  new CCipher();
        System.out.println(c.decode("VQREQFGT", 2));
        System.out.println(c.decode("ABCDEFGHIJKLMNOPQRSTUVWXYZ", 10));
        System.out.println(c.decode("TOPCODER", 0));
        System.out.println(c.decode("ZWBGLZ", 25));
        System.out.println(c.decode("DBNPCBQ", 1));
        System.out.println(c.decode("LIPPSASVPH", 4));
    }
    
}
