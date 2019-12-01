import java.math.BigInteger;

import static java.lang.Math.pow;

public class Voting {
    private long Q;
    private long P;
    private long N;
    private long Fi;
    private long d;
    private long c;

    public Voting() {
        BigInteger BigQ = BigInteger.valueOf(0);
        BigInteger BigP = BigInteger.valueOf(0);
        do {
            Q = random(1000000000);
            P = random(1000000000);
            BigQ = BigInteger.valueOf(Q);
            BigP = BigInteger.valueOf(P);

        } while (!(isPrime(Q) && BigQ.isProbablePrime(1) && isPrime(P) && BigP.isProbablePrime(1)));
        N = P * Q;
        Fi = (P - 1) * (Q - 1);
        do {
            d = random(Fi);

        } while (gcd(Fi, d) != 1);

        Evclid e1 = new Evclid(Fi, d, 1, 1);
        c = e1.Ures;
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
