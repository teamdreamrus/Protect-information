import java.math.BigInteger;
import java.util.Random;
import java.io.*;

import static java.lang.Math.pow;

public class Companion {
    private long C;
    private long D;
    private long P;

    public long getC() {
        return C;
    }

    public long getD() {
        return D;
    }
    public Companion(long P) throws IOException {
        this.P = P;
      BigInteger BigC;
      BigInteger M;
        do{
            do {
                C = generateRandomInt(10000);
                BigC = BigInteger.valueOf(C);
            }while(!(isPrime(C) && BigC.isProbablePrime(1)));

                Evclid e = new Evclid(P-1,C,1,1);
                 D=e.Ures;
//        System.out.println("D");
//                 System.out.println(D);
                 Mod m = new Mod(C*D,1,P-1);
                 M=m.exp2();
            } while (!M.equals(BigInteger.ONE));


    }
    public long exp(long mes,long power){
        Mod x = new Mod(mes,power,P);
        BigInteger res = x.exp2();
        return res.longValue();
    }


    private static long rnd(long max)
    {
        return (long) (Math.random() * ++max);
    }

    public static long generateRandomInt(int upperRange){
        Random random = new Random();

        return random.nextInt(upperRange);
    }

    private boolean isPrime(long P){
        if(P<=1) return false;
        long b =(long)pow(P,0.5);
        for(int i=2;i<=b;i++){
            if((P%i)==0) return false;
        }
        return true;
    }


}
