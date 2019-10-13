import java.math.BigInteger;

import static java.lang.Math.pow;

public class DiffHellman {
    public long g;
    public long P;
    public long Q;
    public DiffHellman(){
        BigInteger BigQ =  BigInteger.valueOf(0);
        BigInteger BigP =  BigInteger.valueOf(0);
        do{
           Q=random(10000000);
            BigQ = BigInteger.valueOf(Q);


            P=2*Q+1;
            BigP = BigInteger.valueOf(P);
//            System.out.println(isPrime(Q));
//            System.out.println(BigQ.isProbablePrime(1));
        }while(!(isPrime(Q) && BigQ.isProbablePrime(1) && isPrime(P) && BigP.isProbablePrime(1)));   //test millera-rabina



        long xl = 0;
        BigInteger BigG=  BigInteger.valueOf(0);
        do{
            g = random(P-1);
            BigG = BigInteger.valueOf(g);
            Mod d = new Mod(g,Q,P);
            BigInteger x= d.exp2();
            xl = x.longValue();
            System.out.println((xl));
            System.out.println( (isPrime(g) && BigG.isProbablePrime(1)));
        }while(xl==1);
        System.out.println("Q: "+ Q+" P: "+P+" g: "+g +" ");
//        Man A = new Man(P,g);
//        Man B = new Man(P,g);
    /*
        System.out.println(" m1 to m2 ");
        System.out.println(m1.Call(m2,P));
        System.out.println(" m2 to m1 ");
        System.out.println(m2.Call(m1,P));
*/


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
 }


