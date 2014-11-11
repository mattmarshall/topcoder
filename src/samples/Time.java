package samples;

public class Time {
    
    private static final int SECONDS_IN_HOUR = 3600;
    
    public String whatTime(int seconds) {
        if (seconds <= 0) {
            return "0:0:0";
        }
        int hours = seconds / SECONDS_IN_HOUR;
        int minutes = (seconds % SECONDS_IN_HOUR) / 60;
        int secs = seconds % 60;
        StringBuilder sb = new StringBuilder(String.valueOf(hours))
                .append(":")
                .append(String.valueOf(minutes))
                .append(":")
                .append(String.valueOf(secs));
        return sb.toString();
    }
    
    public static void main(String[] args) {
        Time time = new Time();
        System.out.println(time.whatTime(0));
        System.out.println(time.whatTime(3661));
        System.out.println(time.whatTime(5436));
        System.out.println(time.whatTime(86399));
    }
    
}
