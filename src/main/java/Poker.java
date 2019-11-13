import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.pow;

public class Poker {
    private long P;
    private long Q;
    private Integer n;
    private ArrayList<Long> D;

    public Poker(Integer n) {
        this.n = n;
        BigInteger BigQ;
        BigInteger BigP;
        do {
            Q = random(10000000);
            BigQ = BigInteger.valueOf(Q);
            P = 2 * Q + 1;
            BigP = BigInteger.valueOf(P);
        } while (!(isPrime(Q) && BigQ.isProbablePrime(1) && isPrime(P) && BigP.isProbablePrime(1)));   //test millera-rabina
        ArrayList<Long> KStart = new ArrayList<Long>();
        for (int i = 2; i < 54; i++) {
            KStart.add((long) i);
        }
        System.out.println(KStart);
        ArrayList<Long> K = new ArrayList<Long>();
        int num;
        int max = 51;
        int min = 0;
        while (max > 0) {
            num = getRandomNumberInRange(min, max);
            K.add(KStart.get(num));
            KStart.remove(num);
            max--;
        }
        K.add(KStart.get(0));

        ArrayList<Long> C = new ArrayList<Long>();
        D = new ArrayList<Long>();
        for (int i = 0; i < n; i++) {
            D.add((long) 0);
            C.add((long) 0);
        }

        for (int i = 0; i < n; i++) {
            while (true) {
                C.set(i, random(P - 1));
                Evclid e = new Evclid(P - 1, C.get(i), 1, 1);
                D.set(i, e.Ures);
                if ((C.get(i) * D.get(i) % (P - 1)) == 1) {
                    break;
                }
            }
            K = shuffle(K, C.get(i), P);
        }
        System.out.println(K);
        BigInteger res = null;
        for (int i = 0; i < n; i++) {
            System.out.println("hash Cards: \n" + K.get(i));
            System.out.println(K.get(i + n));
            System.out.println("Cards: \n" + unshuffleCard(K.get(i), i));
            System.out.println(unshuffleCard(K.get(i + n), i));
            System.out.println("-----------------------");
        }
        System.out.println("Table: ");
        for (int i = n * 2; i < (n * 2 + 5); i++) {
            System.out.println("hash Cards: \n" + K.get(i));
            System.out.println("Card: \n" + unshuffleCard(K.get(i), 0));
        }

    }

    private Long unshuffleCard(long hashCard, int number) {
        Long card = (long) 0;
        BigInteger res = null;
        for (int j = 0; j < n; j++) {
            if (j != number) {
                BigInteger big1 = BigInteger.valueOf(hashCard);
                res = big1.modPow(BigInteger.valueOf(D.get(j)), BigInteger.valueOf(P));
                hashCard = res.longValue();
            }
        }
        assert res != null;
        res = res.modPow(BigInteger.valueOf(D.get(number)), BigInteger.valueOf(P));
        card = res.longValue();

        return card;
    }

    private ArrayList<Long> shuffle(ArrayList<Long> lastK, long C, long P) {
        ArrayList<Long> K = new ArrayList<Long>();
        int num;
        int max = 51;
        int min = 0;
        BigInteger var;
        while (max > 0) {
            num = getRandomNumberInRange(min, max);
            var = BigInteger.valueOf(lastK.get(num)).modPow(BigInteger.valueOf(C), BigInteger.valueOf(P));
            K.add(var.longValue());
            lastK.remove(num);
            max--;
        }
        var = BigInteger.valueOf(lastK.get(0));
        K.add(var.longValue());
        return K;
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

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
