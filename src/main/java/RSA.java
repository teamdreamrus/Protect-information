import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static java.lang.Math.pow;
import static jdk.nashorn.internal.objects.NativeMath.random;

public class RSA {
    public long P;
    public long Q;
    public long N;
    public long Fi;
    public long d;
    public long c;
    public long fileSize;
    private InputStream inputStream;
    private OutputStream outputStream;
    public RSA() throws IOException {
        String path = "smile.jpg";
        inputStream = new FileInputStream(path);
        outputStream = new FileOutputStream("NEW"+path);
        File file = new File(path);
        fileSize = file.length();

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



        BigInteger M;
        BigInteger fi =  BigInteger.valueOf(Fi);
      //  do {
            Evclid e = new Evclid(Fi, d, 1, 1);
            c = e.Ures;

            System.out.println(c);
//            Mod m = new Mod(c * d, 1, Fi);
//
//            System.out.println("cd :");
//            System.out.println(c*d);
//            System.out.println(Fi);
//            M = m.exp2();

//            System.out.println(M.longValue());
       // } while (!M.equals(BigInteger.ONE));
        byte[] buffer = new byte[(int)fileSize];
        inputStream.read(buffer, 0, buffer.length);
        int[] bytes = new int[buffer.length];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = buffer[i] & 0xFF;

            //vse okay
        }

//        int message = 124532;
        System.out.println("origin ");
//        System.out.println(message+"");
        for (int aByte : bytes) {
            BigInteger BRes = BigInteger.valueOf(aByte).modPow(BigInteger.valueOf(d), BigInteger.valueOf(N));
            FileWriter writer = new FileWriter("Temp.txt", true);
            writer.write(BRes.toString()+" ");
            writer.flush();

            //vse okay
        }


//        System.out.println(BRes.toString());
//        Mod xm = new Mod(message, d, N);
//        long ee = xm.exp2().longValue();
//        System.out.print("Shifr ");
//        System.out.println(ee);
//        System.out.println(xm.exp2().toString());
//        Mod res= new Mod(ee,c,N);
//        long newRes = res.exp2().longValue();
//        System.out.println(newRes);
//        System.out.println(res.exp2().toString());
        String fileName = "Temp.txt";
        String content = Files.lines(Paths.get(fileName)).reduce("", String::concat);
        String[] koded = content.split(" ");
        byte[] buffer1 = new byte[(int)fileSize];
        ArrayList<BigInteger> kodedBig= new ArrayList<>();
        for(int i = 0; i < buffer1.length; i++){
            kodedBig.add(BigInteger.valueOf(Long.parseLong(koded[i])));
            BigInteger OutRes = kodedBig.get(i).modPow(BigInteger.valueOf(c),BigInteger.valueOf(N));
            buffer1[i]=(byte)OutRes.byteValue();
           System.out.println(OutRes.byteValue());
        }

//for(int i = 0; i < kodedBig.size; i++){
//
//}
        outputStream.write(buffer1, 0, buffer1.length);






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
    public class InputOutputStreamExam {
        private InputStream inputStream;
        private OutputStream outputStream;
        private String path;


        public InputOutputStreamExam(String path) {
            this.path = path;

        }

    }
}
