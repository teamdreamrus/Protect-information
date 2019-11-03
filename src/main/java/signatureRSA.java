import java.io.IOException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import static java.lang.Math.pow;

public class signatureRSA {
    public long P;
    public long Q;
    public long N;
    public long Fi;
    public long d; //open
    public long c;

    public signatureRSA() {
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
        ArrayList<Integer> hashCode = new ArrayList<>();
        try {
            HashFile hashFile = new HashFile("origin.txt");
            for (Integer h : hashFile.HashResult) {
                hashCode.add(h);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<BigInteger> SArray = new ArrayList<>();
        for (int i = 0; i < hashCode.size(); i++) {
            BigInteger S = BigInteger.valueOf(hashCode.get(i)).modPow(BigInteger.valueOf(c), BigInteger.valueOf(N));
            SArray.add(S);
        }
        for(int i = 0; i < SArray.size(); i++){
            BigInteger E = SArray.get(i).modPow(BigInteger.valueOf(d),BigInteger.valueOf(N));
            boolean res = hashCode.get(i).equals(E.intValue());
            System.out.println(res);
        }
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
