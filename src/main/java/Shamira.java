import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Math.pow;

public class Shamira {
    private InputStream inputStream;
    private OutputStream outputStream;
    private OutputStream outputStream2;
    public long fileSize;
    private long P;

    public Shamira() throws IOException {
        BigInteger BigP = BigInteger.valueOf(0);
        do {
            P = rnd(100000000);
            BigP = BigInteger.valueOf(P);
            //   System.out.println("p");
        } while (!(isPrime(P) && BigP.isProbablePrime(1)));
        Companion A = new Companion(P);
        Companion B = new Companion(P);
        ArrayList<Integer> arrX = new ArrayList<>();
        String path = "origin.txt";
        inputStream = new FileInputStream(path);
        File file = new File(path);
        fileSize = file.length();

        byte[] buffer = new byte[(int)fileSize];
        inputStream.read(buffer, 0, buffer.length);
        int[] bytes = new int[buffer.length];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = buffer[i] & 0xFF;

        //vse okay
        }

//шифровка

        for (int i = 0; i < bytes.length; i++) {
            arrX.add((int) A.exp(bytes[i], A.getC()));
            arrX.add((int) B.exp(arrX.get(0), B.getC()));
        

            FileWriter writer = new FileWriter("Temp.txt", true);
            writer.write(arrX.get(1).toString()+" ");
            writer.flush();
            System.out.println(arrX.get(1));
            arrX.clear();
        }

        //записать шифрованное
        //считать шифрованное
        String fileName = "Temp.txt";
        String content = Files.lines(Paths.get(fileName)).reduce("", String::concat);
        String[] koded = content.split(" ");
        ArrayList<Integer> kodedInt= new ArrayList<>();
        for(String el : koded){
            kodedInt.add(Integer.valueOf(el));
            System.out.println(el);
        }

        //дешифровка
        ArrayList<Integer> result = new ArrayList<>();
        String t="";
        byte[] buffer1 = new byte[(int)fileSize];
        arrX.clear();
        for (int i = 0; i < bytes.length; i++) {
            arrX.add((int) A.exp(kodedInt.get(i), A.getD()));
            arrX.add((int) B.exp(arrX.get(0), B.getD()));
            result.add(arrX.get(1));

            arrX.clear();
           System.out.println((char)result.get(i).byteValue());
            FileWriter writer = new FileWriter("New.txt", true);
            writer.write((char)(byte)(int)result.get(i));
            writer.flush();

        }



    }

    private static long rnd(long max) {
        return (long) (Math.random() * ++max);
    }

    private boolean isPrime(long P) {
        if (P <= 1) return false;
        long b = (long) pow(P, 0.5);
        for (int i = 2; i <= b; i++) {
            if ((P % i) == 0) return false;
        }
        return true;
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
