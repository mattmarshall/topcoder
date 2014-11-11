package samples;

public class BinaryCode {

    private static class Result {
        
        int[] zeros;
        int[] ones;
        
        public Result(int length) {
            zeros = new int[length];
            ones = new int[length];

            // Set defaults
            zeros[0] = 0;
            ones[0] = 1;
        }
    }

    private int calcResult(int index, int[] qs, int[] ps) {
        int qm1 = (qs != null && qs.length > index && index > 0) ? qs[index - 1] : 0;
        int pm1 = (ps != null && ps.length > index && index > 0) ? ps[index - 1] : 0;
        int pm2 = (ps != null && ps.length > index && index > 1) ? ps[index - 2] : 0;
        return qm1 - pm1 - pm2;
    }

    private void calcResult(int[] qs, int index, Result result) {

        // Finished calculating
        if (index >= qs.length) {
            return;
        }

        // Calculate for 0 and 1 together
        if (result.ones != null) {
            int one = calcResult(index, qs, result.ones);
            if (one > 1 || one < 0) {
                result.ones = null;
            } else {
                result.ones[index] = one;
            }
        }
        
        if (result.zeros != null) {
            int zer = calcResult(index, qs, result.zeros);
            if (zer > 1 || zer < 0) {
                result.zeros = null;
            } else {
                result.zeros[index] = zer;
            }
        }

        // Tail recurse
        calcResult(qs, index + 1, result);
    }

    private Result getResult(String message) {
        Result result = new Result(message.length());
        final int[] qs = new int[message.length()];
        // Turn string into int array
        for (int i = 0; i < message.length(); i++) {
            qs[i] = Integer.parseInt(message.substring(i, i + 1));
        }
        calcResult(qs, 1, result);
        return result;
    }

    public String[] decode(String message) {
        
        final String[] answer = new String[2];        
        if (message == null || message.length() == 0) {
            return answer;
        }        
        
        final Result result = getResult(message);

        // Zeros
        if (result.zeros != null) {
            StringBuilder sb = new StringBuilder();
            for (int i : result.zeros) {
                sb.append(i);
            }
            answer[0] = sb.toString();
        } else {
            answer[0] = "NONE";
        }
        
        // Ones
        if (result.ones != null) {
            StringBuilder sb = new StringBuilder();
            for (int i : result.ones) {
                sb.append(i);
            }
            answer[1] = sb.toString();
        } else {
            answer[1] = "NONE";
        }
        
        return answer;
    }
    
    public static void main(String[] args) {
        final String code = "123210122";
        System.out.println("Code: " + code);
        BinaryCode bc = new BinaryCode();
        String[] answer = bc.decode("123210122");
        System.out.println("{ " + answer[0] + ", " + answer[1] + " }");
    }
}
