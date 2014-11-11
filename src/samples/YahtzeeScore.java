package samples;

public class YahtzeeScore {

    public int maxPoints(int[] toss) {
        int[] counts = new int[6];
        for (int val : toss) {
            counts[val - 1] += val;
        }
        int max = 0;
        for (int val : counts) {
            if (val > max) {
                max = val;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        YahtzeeScore score = new YahtzeeScore();
        System.out.println(score.maxPoints(new int[]{2, 2, 3, 5, 4}));
        System.out.println(score.maxPoints(new int[]{6, 4, 1, 1, 3}));
        System.out.println(score.maxPoints(new int[]{5, 3, 5, 3, 3}));
    }

}
