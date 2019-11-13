import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import static java.lang.Math.pow;

public class signatureLgamal {

    public signatureLgamal() throws IOException {
        DiffHellman Diff = new DiffHellman();
        Man Alisa = new Man(Diff.P, Diff.g);
        Man Bob = new Man(Diff.P, Diff.g);
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
        System.out.println(hashCode);
        long k;
        Evclid e;
        do{
            k=random(Diff.P-1);
            e = new Evclid(Diff.P-1,k,1,1);
        }
        while(e.gcdres!=1);
        System.out.println(e.gcdres);
        System.out.println(e.Ures);
        System.out.println(k*e.Ures % (Diff.P-1));
        BigInteger G=BigInteger.valueOf(Diff.g);
        BigInteger K = BigInteger.valueOf(k);
        BigInteger P = BigInteger.valueOf(Diff.P);
        BigInteger R = G.modPow(K, P);
        BigInteger Y = BigInteger.valueOf(Alisa.openKey);
        BigInteger X = BigInteger.valueOf(Alisa.closedKey);
        long u;
        ArrayList<Long> s= new ArrayList<>();
        for(Integer hash : hashCode){
            u = (hash.longValue() - Alisa.closedKey*R.longValue() ) % (Diff.P-1);
            BigInteger U = BigInteger.valueOf(hash.longValue() - Alisa.closedKey*R.longValue());

//            System.out.println(;
            long u1=U.modPow(BigInteger.ONE,P.subtract(BigInteger.ONE)).longValue();

            long x = (e.Ures*u1) % (Diff.P-1);
            s.add(x);
            FileWriter writer = new FileWriter("Temp.txt", true);
            writer.write(x + " ");
            writer.flush();
    }
        //Проверка
        for(int i=0;i<hashCode.size();i++){
             BigInteger left = (Y.modPow(R,P).multiply(R.modPow(BigInteger.valueOf(s.get(i)),P))).modPow(BigInteger.ONE,P);
             BigInteger right = BigInteger.valueOf(Diff.g).modPow(BigInteger.valueOf(hashCode.get(i)),BigInteger.valueOf(Diff.P));
            boolean rez = left.equals(right);
            System.out.println((rez));
        }

    }
    private boolean isPrime(long P){
        if(P<=1) return false;
        long b =(long)pow(P,0.5);
        for(int i=2;i<=b;i++){
            if((P%i)==0) return false;
        }
        return true;
    }
    private static long random(long max)
    {
        return (long) (Math.random() * ++max);
    }
    private long gcd(long a, long b){
        while (b!=0){
            long r = a %b;
            a=b;
            b=r;
        }
        return a;
    }
}
