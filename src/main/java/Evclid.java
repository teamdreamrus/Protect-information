import java.lang.reflect.Array;
import java.util.ArrayList;

public class Evclid {
    private long a;
    private long b;
    private long x;
    private long y;
    public long Ures;

    public Evclid(long a,long b,long x,long y){
        this.a=a;
        this.b=b;
        this.x=x;
        this.y=y;

        ArrayList<Long> U = new ArrayList<>();
        ArrayList<Long> V = new ArrayList<>();
        ArrayList<Long> T = new ArrayList<>();
        ArrayList<Long> X = new ArrayList<>();
        X.add((long)0);
        T.add((long)0);
        T.add((long)0);
        T.add((long)0);


        U.add(a);
        U.add((long)1);
        U.add((long)0);
        V.add(b);
        V.add((long)0);
        V.add((long)1);
       // int t = (int)(U.get(0) / V.get(0));
//        System.out.println((int)(U.get(0) / V.get(0)));
      //  System.out.println((double)(U.get(0) / V.get(0) - (double)((U.get(0) / V.get(0)) %1)));
//        System.out.println((int) Math.floor(U.get(0) / V.get(0)));
        do{

            long q = (long)(U.get(0) / V.get(0));
//            System.out.println(q);
            //PEREPOLNENIE
            T.set(0,(U.get(0) % V.get(0)));
            T.set(1,(U.get(1) - q * V.get(1)));
            T.set(2,(U.get(2) - q * V.get(2)));
            System.out.println(q * V.get(1));
            System.out.println(q * V.get(2));

//            System.out.println(T.get(1));
//            System.out.println(T.get(2));
            U.set(0,V.get(0));
            U.set(1,V.get(1));
            U.set(2,V.get(2));

            V.set(0,T.get(0));
            V.set(1,T.get(1));
            V.set(2,T.get(2));
//            System.out.println( U.get(0));
//            System.out.println( U.get(1));
//            System.out.println( U.get(2));
            if(V.get(0).equals(X.get(0))) break;
        }while(!(U.get(0).equals(X.get(0))));
//        while(!(U.get(0)==0)){
//            int x1 = (int)(U.get(0) / V.get(0));
//            int q =  x1;
//            T.set(0,(U.get(0) % V.get(0)));
//            T.set(1,(U.get(1) - q * V.get(1)));
//            T.set(2,(U.get(2) - q * V.get(2)));
//
//            U.set(0,V.get(0));
//            U.set(1,V.get(1));
//                U.get(2)

//
//            V.set(0,T.get(0));
//            V.set(1,T.get(1));
//            V.set(2,T.get(2));
//        }
//        System.out.println( U.get(0));
//            System.out.println( U.get(1));
//            System.out.println( U.get(2));
            if(U.get(2)<0)
                Ures=U.get(2)+this.a;
            else Ures=U.get(2);

//        System.out.println(Ures);

    }
    public  long gcd(){
        while (b!=0){
            long r = a %b;
            a=b;
            b=r;
        }
        return a;
    }



}
