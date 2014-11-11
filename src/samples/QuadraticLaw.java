package samples;

public class QuadraticLaw {
    
    public long getTime(long d) {
        if (d <= 0) {
            return 0;
        }
        
        return (long) ((Math.sqrt(1 + 4 * d) - 1) / 2);
    }
    
    public static void main(String[] args) {
        QuadraticLaw q = new QuadraticLaw();
        System.out.println(q.getTime(1));
        System.out.println(q.getTime(2));
        System.out.println(q.getTime(5));
        System.out.println(q.getTime(6));
        System.out.println(q.getTime(7));
        System.out.println(q.getTime(1482));
        System.out.println(q.getTime(1000000000000000000L));
        System.out.println(q.getTime(31958809614643170L));
    }
    
}
