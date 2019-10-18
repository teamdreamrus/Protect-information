import sun.awt.windows.WPrinterJob;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

public class Vernam {
    private InputStream inputStream;
    private OutputStream outputStream;
    public long fileSize;

    public Vernam() throws IOException {
        String path = "smile.jpg";
        inputStream = new FileInputStream(path);
        outputStream = new FileOutputStream("NEW" + path);
        File file = new File(path);
        fileSize = file.length();
        byte[] buffer = new byte[(int) fileSize];
        inputStream.read(buffer, 0, buffer.length);
        int[] bytes = new int[buffer.length];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = buffer[i] & 0xFF;
        }

        char[] achKey = new char[bytes.length];
        int[] achResult = new int[bytes.length];
        Random random = new Random();
        for (int i = 0; i < (int) fileSize; i++) {
            achKey[i] = (char) random.nextInt(Byte.MAX_VALUE);
            achResult[i] = (bytes[i] ^ achKey[i]);
            FileWriter writer1 = new FileWriter("Keys.txt", true);
            writer1.write((int)achKey[i]+" ");
            writer1.flush();
            FileWriter writer = new FileWriter("Temp.txt", true);
            writer.write(achResult[i] + " ");
            writer.flush();
        }

        String fileName = "Temp.txt";
        String content = Files.lines(Paths.get(fileName)).reduce("", String::concat);
        String[] koded = content.split(" ");
        ArrayList<Integer> kodedInt = new ArrayList<>();
        for (String el : koded) {
            kodedInt.add(Integer.valueOf(el));
            System.out.println(el);
        }

        byte[] buffer1 = new byte[(int) fileSize];
        char[] achDecrypt = new char[bytes.length];
        for (int i = 0; i < kodedInt.size(); i++) {
            buffer1[i] = (byte) (int) (kodedInt.get(i) ^ achKey[i]);
        }
        outputStream.write(buffer1, 0, buffer1.length);
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
