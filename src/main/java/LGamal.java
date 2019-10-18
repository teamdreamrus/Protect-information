import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class LGamal {
    private long A;
    private long B;
    private InputStream inputStream;
    private OutputStream outputStream;
    public long fileSize;

    public LGamal() throws IOException {

        DiffHellman Diff = new DiffHellman();
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

        Man Alisa = new Man(Diff.P, Diff.g);
        Man Bob = new Man(Diff.P, Diff.g);
        Mod m1 = new Mod(Diff.g, Alisa.getSessionKey(), Diff.P);

        A = m1.exp2().longValue();
        FileWriter writer1 = new FileWriter("Temp.txt", true);
        writer1.write(A + " ");
        writer1.flush();
        FileWriter writer2 = new FileWriter("Keys.txt", true);
        writer2.write(Alisa.getSessionKey()+" ");
        writer2.flush();
        for (int message : bytes) {
            Mod m2 = new Mod(Bob.openKey, Alisa.getSessionKey(), Diff.P);
            B = message * m2.exp2().longValue() % Diff.P;
            FileWriter writer = new FileWriter("Temp.txt", true);
            writer.write(B + " ");
            writer.flush();
        }

        String fileName = "Temp.txt";
        String content = Files.lines(Paths.get(fileName)).reduce("", String::concat);
        String[] koded = content.split(" ");
        long newA = Long.parseLong(koded[0]);
        ArrayList<Integer> kodedInt = new ArrayList<>();
        boolean x = false;
        for (String el : koded) {
            if (x)
                kodedInt.add(Integer.valueOf(el));
            x = true;
        }

        byte[] buffer1 = new byte[(int) fileSize];
        for (int i = 0; i < bytes.length; i++) {
            Mod m3 = new Mod(newA, Diff.P - 1 - Bob.getClosedKey(), Diff.P);
            long messageNew = kodedInt.get(i) * m3.exp2().longValue() % Diff.P;
            buffer1[i] = (byte) (int) messageNew;
            System.out.println(messageNew);
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
