package samples;

public class ExerciseMachine {

    private static class Tick {

        final int seconds;
        final double doubleSeconds;
        int current;
        int total;
        int last;

        public Tick(int seconds) {
            this.seconds = seconds;
            this.doubleSeconds = (double) seconds;
        }
    }

    int getPercentages(int seconds) {

        Tick tick = new Tick(seconds);
        do {            
            double pct = tick.current / (tick.doubleSeconds) * 100;
            if (pct == ((int) pct)) {
                tick.total++;
            }
            tick.current++;
        } while (tick.current < tick.seconds);

        return tick.total;
    }

    int getPercentages(int hours, int minutes, int seconds) {
        int totSecs = (3600 * hours) + (60 * minutes) + seconds;
        return getPercentages(totSecs);
    }

    public int getPercentages(String time) {
        int hours = Integer.parseInt(time.substring(0, 2));
        int mints = Integer.parseInt(time.substring(3, 5));
        int secds = Integer.parseInt(time.substring(6, 8));
        return getPercentages(hours, mints, secds);
    }

    public static void main(String[] args) {
        ExerciseMachine ec = new ExerciseMachine();
        System.out.println(ec.getPercentages("00:30:00"));
        System.out.println(ec.getPercentages("00:28:00"));
        System.out.println(ec.getPercentages("23:59:59"));
        System.out.println(ec.getPercentages("00:14:10"));
        System.out.println(ec.getPercentages("00:19:16"));
    }

}
