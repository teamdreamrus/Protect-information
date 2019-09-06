import java.math.BigInteger;
import java.util.Random;

public class Start{
    public static void main(String[] args) {
        Mod m = new Mod(11,7,9);
        System.out.println(m.exp2());
        Random random = new Random();

        long a =  rnd(1000000000);
        long x =  rnd(1000000000);
        long p =  rnd(1000000000);
        System.out.println(a+" "+x+" "+p);
        Mod m1 = new Mod(a,x,p);
        System.out.println(m1.exp2());



    }
    public static long rnd(long max)
    {
        return (long) (Math.random() * ++max);
    }
}
