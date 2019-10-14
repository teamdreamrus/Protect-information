import java.math.BigInteger;

import static java.lang.Math.pow;
import static jdk.nashorn.internal.objects.NativeMath.random;

public class RSA {
    public long P;
    public long Q;
    public long N;
    public long Fi;
    public long d;
    public long c;

    public RSA() {
        BigInteger BigQ = BigInteger.valueOf(0);
        BigInteger BigP = BigInteger.valueOf(0);
        do {
            Q = random(100000000);
            P = random(100000000);
            BigQ = BigInteger.valueOf(Q);
            BigP = BigInteger.valueOf(P);

        } while (!(isPrime(Q) && BigQ.isProbablePrime(1) && isPrime(P) && BigP.isProbablePrime(1)));
        N = P * Q;
        Fi = (P - 1) * (Q - 1);
        do {
            d = random(Fi);

        } while (gcd(Fi, d) != 1);
        BigInteger M;
        BigInteger fi =  BigInteger.valueOf(Fi);
      //  do {
            Evclid e = new Evclid(Fi, d, 1, 1);
            c = e.Ures;

            System.out.println(c);
//            Mod m = new Mod(c * d, 1, Fi);
//
//            System.out.println("cd :");
//            System.out.println(c*d);
//            System.out.println(Fi);
//            M = m.exp2();

//            System.out.println(M.longValue());
       // } while (!M.equals(BigInteger.ONE));

        int message = 124532;
        System.out.println("origin ");
        System.out.println(message+"");
        Mod xm = new Mod(message, d, N);
        long ee = xm.exp2().longValue();
        System.out.print("Shifr ");
        System.out.println(ee);
        System.out.println(xm.exp2().toString());
        Mod res= new Mod(ee,c,N);
        long newRes = res.exp2().longValue();
        System.out.println(newRes);
        System.out.println(res.exp2().toString());
        System.out.print("New ");
        System.out.println(newRes);



    }

    private boolean isPrime(long P) {
        if (P <= 1) return false;
        long b = (long) pow(P, 0.5);
        for (int i = 2; i <= b; i++) {
            if ((P % i) == 0) return false;
        }
        return true;
    }

    private static long random(long max) {
        return (long) (Math.random() * ++max);
    }

    public long gcd(long a, long b) {
        while (b != 0) {
            long r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}
