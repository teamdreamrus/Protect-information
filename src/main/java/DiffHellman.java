import java.math.BigInteger;

import static java.lang.Math.pow;

public class DiffHellman {
    public long g;
    public long P;
    public long Q;
    public DiffHellman(){
        BigInteger BigQ =  BigInteger.valueOf(0);
        do{
           Q=random(10000000);
            BigQ = BigInteger.valueOf(Q);
            System.out.println(Q);
            System.out.println(BigQ);
        }while(!(isPrime(Q) && BigQ.isProbablePrime(1)));   //test millera-rabina
        P=2*Q+1;
        g = random(P-1);
        System.out.print("Q: "+ Q+" P: "+P+" g: "+g +" ");
        Man m1 = new Man(P,g);
        Man m2 = new Man(P,g);
      /*  m1.openKey = 4;
        m1.closedKey = 7;
        m2.openKey = 9;
        m2.closedKey = 6 ;*/
        System.out.println(" m1 to m2 ");
        System.out.println(m1.Call(m2,P));
        System.out.println(" m2 to m1 ");
        System.out.println(m2.Call(m1,P));



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


