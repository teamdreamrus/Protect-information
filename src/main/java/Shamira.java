import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;

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
        //long m = 655654;// 9/2 = 4 5
        System.out.println("m = ");
//        System.out.println(m);
        ArrayList<Integer> arrX = new ArrayList<>();
        String path = "origin.txt";
        inputStream = new FileInputStream(path);
        File file = new File(path);
        fileSize = file.length();
        outputStream = new FileOutputStream("Temp" + path);
        outputStream2 = new FileOutputStream("New" + path);
//        int data ;
        //  char content = (char) data;
//        System.out.println(data);
//        System.out.println(content);
        //& 0xFF
//        arrX.add((int) A.exp(data, A.getC()));
//        arrX.add((int) B.exp(arrX.get(0), B.getC()));
//        System.out.println(arrX.get(1));

        byte[] buffer = new byte[(int)fileSize];
        inputStream.read(buffer, 0, buffer.length);
        int[] bytes = new int[buffer.length];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = buffer[i] & 0xFF;
        }
//шифровка
        ArrayList<Integer> resultWork = new ArrayList<>();
        for (int i = 0; i < bytes.length; i++) {
            arrX.add((int) A.exp(bytes[i], A.getC()));
            arrX.add((int) B.exp(arrX.get(0), B.getC()));
            resultWork.add(arrX.get(1));
            arrX.clear();
            System.out.println(resultWork.get(i));
        }
        arrX.clear();
        //записать шифрованное
        //дешифровка
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < bytes.length; i++) {
            arrX.add((int) A.exp(resultWork.get(i), A.getD()));
            arrX.add((int) B.exp(arrX.get(0), B.getD()));
            result.add(arrX.get(1));
            arrX.clear();
            System.out.println((char)(int)result.get(i));
        }
        //записать дешифрованное
//        byte x;
//        x =(byte)((int)arrX.get(1));
//        System.out.println(x);
//        arrX.add((int) A.exp(arrX.get(1), A.getD()));
//        arrX.add((int) B.exp(arrX.get(2), B.getD()));
//
//        System.out.println((char) (int)arrX.get(3));

//        String koded = "";
//        ArrayList<Integer> buffer = new ArrayList<>();
//        arrX.add((int) A.exp(data & 0xFF, A.getC()));
//        arrX.add((int) B.exp(arrX.get(0), B.getC()));
//
//        koded += arrX.get(1).toString() + " ";
//        arrX.clear();
//        System.out.println("read");

//        while (data != -1) {
//
//            data = inputStream.read();
//            arrX.add((int) A.exp(data & 0xFF, A.getC()));
//            arrX.add((int) B.exp(arrX.get(0), B.getC()));
//
//            koded += arrX.get(1).toString() + " ";
//
//            //сброс arrX
//            arrX.clear();
//            System.out.println("shifring");
//        }
        //запись в шифрованный файл
//        byte[] bf = koded.getBytes();
//        outputStream.write(bf, 0, bf.length);


        //дешифровка
//        ArrayList<Integer> arrDekod = new ArrayList<Integer>();
//        String[] dekods = koded.split(" ");
//        for (String el : dekods) {
//            arrDekod.add(Integer.parseInt(el));
//        }
//        byte[] b = new byte[arrDekod.size()];
//        arrX.clear();
//        for (int i = 0; i < arrDekod.size(); i++) {
//            arrX.add((int) A.exp(arrDekod.get(i) & 0xFF, A.getD()));
//            arrX.add((int) B.exp(arrX.get(0), B.getD()));
//            byte t = arrX.get(1).byteValue();
//
//            b[i] = t;
//
//        }
//
//        outputStream2.write(b, 0, b.length);


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
