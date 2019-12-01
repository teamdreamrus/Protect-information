import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Start {
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
        //   Evclid e = new Evclid(22, 7,1,1);

//        Evclid e = new Evclid(a,b,1,1);
//        System.out.println(e.gcd());
        //System.out.println(e.gcd());


        //3
//      DiffHellman Diff =new DiffHellman();

        //4
//        System.out.println(a+" "+y+" "+p);
//        LittleBig lit = new LittleBig(18,13,11); //47 17 27
//        Mod d = new Mod(8,11,19);
//        System.out.println(d.exp2());

//LAB 2
        File file = new File("Keys.txt");
        file.delete();
        File file1 = new File("NEWsmile.jpg");
        file1.delete();
        File file2 = new File("Temp.txt");
        file2.delete();
//        try {
//            Shamira z = new Shamira();
//            LGamal el = new LGamal();
//            Vernam ver = new Vernam();
//            RSA rsa = new RSA();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        //LAB 3#
//            signatureLgamal sigL = new signatureLgamal();

//        signatureRSA sigRSA = new signatureRSA();

//    signatureGHOST sigGhost = new signatureGHOST();

        //Lab 4
//Poker p = new Poker(3);
        try{

            Voter v = new Voter(1232223,123123);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static long rnd(long max) {
        return (long) (Math.random() * ++max);
    }
}
