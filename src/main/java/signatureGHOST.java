import java.io.IOException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.pow;

public class signatureGHOST {
    public long p;
    public long q;
    public long a;
    public long b;
    public long g;
    public long x;
    public long y;
    public signatureGHOST(){
        Random random = new Random();
//        System.out.println(pow(2,1024));
        do{
            q = (random.nextInt(32768) + 32768);
        }while(!isPrime(q));
        for(int i = 16384; i < 32768; i += 2) {
            p = q * (long)i + 1L;
            if (isPrime(p)) {
                b = i;
                //System.out.println(p + " " + i + " " + q);
                break;
            }
        }
        do {
            g = (random.nextInt((int)p - 1));
            a = BigInteger.valueOf(g).modPow(BigInteger.valueOf(b), BigInteger.valueOf(p)).longValue();
        } while(a <= 1L);
        x = (random.nextInt((int)q - 1)) + 1;
        y = BigInteger.valueOf(a).modPow(BigInteger.valueOf(x), BigInteger.valueOf(p)).longValue();
        ArrayList<Integer> hashCode= new ArrayList<>();
        try {
            HashFile hashFile = new HashFile("origin.txt");
            for( Integer h : hashFile.HashResult){
                hashCode.add(h);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long r;
        long k;
        long s=0L;
        ArrayList<Long> arrS = new ArrayList<>();
        ArrayList<Long> arrR = new ArrayList<>();
        for(int i=0;i<hashCode.size();i++) {
            do {
                k = (random.nextInt((int) q - 1)) + 1;
                r = (BigInteger.valueOf(a).modPow(BigInteger.valueOf(k), BigInteger.valueOf(p)).modPow(BigInteger.ONE, BigInteger.valueOf(q)).longValue());

                s = BigInteger.valueOf(k * hashCode.get(i) + x * r).modPow(BigInteger.ONE, BigInteger.valueOf(p)).longValue();

            } while (r == 0L || s == 0L);
            arrS.add(s);
            arrR.add(r);
        }
        System.out.println(arrS);
//        System.out.println(arrR);
        System.out.println(q);
//        System.out.println(isPrime(q));
        long u1,u2,v;
        for(int i=0;i<hashCode.size();i++){
            if(arrS.get(i)>=q){
                Evclid hh = new Evclid(q,hashCode.get(i),1,1);
                u1 = (arrS.get(i)*hh.Ures) % q;
                u2 = (-1*arrR.get(i)*hh.Ures) % q;
                BigInteger resM= BigInteger.valueOf(a).modPow(BigInteger.valueOf(u1), BigInteger.valueOf(p)).multiply(BigInteger.valueOf(y).modPow(BigInteger.valueOf(u2), BigInteger.valueOf(p)));

                v = resM.modPow( BigInteger.ONE, BigInteger.valueOf(p)).modPow(BigInteger.ONE,BigInteger.valueOf(q)).longValue();
                if(v==arrR.get(i)) System.out.println("true");
                else System.out.println("false");
            }else System.out.println("error");
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
