import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

import static java.lang.Math.pow;

public class Start{
    public static void main(String[] args) throws IOException {
       /* Mod m = new Mod(17,47,33);
        System.out.println(m.exp2());
*/

//        long a =  rnd(1000000000);
//        long b =  rnd(1000000000);
//        long x =  rnd(1000000000);
//
//
//        long p;
//        long y;
//        do{
//            y = rnd(1000000000);
//            p =  rnd(1000000000);
//        }while(p<y);

//        System.out.println(a+" "+x+" "+p);
//         1
//        Mod m1 = new Mod(a,x,p);
//        System.out.println(m1.exp2());
//
        //2   a,b>0 a>=b
        Evclid e = new Evclid(22, 7,1,1);

//        Evclid e = new Evclid(a,b,1,1);
//        System.out.println(e.gcd());
        //System.out.println(e.gcd());



        //3
//      DiffHellman Diff =new DiffHellman();

        //4
//        System.out.println(a+" "+y+" "+p);
//        LittleBig lit = new LittleBig(2,3,2); //47 17 27
//        Mod d = new Mod(8,11,19);
//        System.out.println(d.exp2());


        Shamira z = new Shamira();

    }
    public static long rnd(long max)
    {
        return (long) (Math.random() * ++max);
    }
}
