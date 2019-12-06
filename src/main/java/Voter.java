import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;

public class Voter {
    public Long answer;
    public Long N;
    public Long d;
    public Long r;
    public Long h;
    public String hash;
    public BigInteger NN;
    public String ansers;

    public Voter(long N, long d, String name) throws Exception {

        answer = random((long) 2);
        Long rnd = random(10000);
        ansers = rnd.toString()  + answer.toString()+name;
        answer = Long.valueOf(ansers);
        this.N = N;
        this.d = d;
        while (true) {
            r = random(1000000);
            if (gcd(N, r) == 1) break;
        }
        System.out.println(answer);
        hash = getHash(answer.toString());
        System.out.println(hash);
        // h = (getHash(answer.toString());
        BigInteger H = BigInteger.valueOf(Long.parseLong(hash));
        BigInteger R = BigInteger.valueOf(r);
        BigInteger D = BigInteger.valueOf(d);
        NN = BigInteger.valueOf(N);

        H = H.multiply(R.modPow(D, NN));
        h = H.modPow(BigInteger.ONE, NN).longValue();
    }
    public String signature(BigInteger s_) {

        Evclid e = new Evclid(r,N,1,1);
        BigInteger RR = BigInteger.valueOf(e.Ures).modPow(BigInteger.ONE, NN);
        BigInteger SS = s_.modPow(BigInteger.ONE, NN);
        BigInteger res = RR.multiply(SS).modPow(BigInteger.ONE, NN);
        return res.toString();

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

    public String getHash(String str) throws Exception {


        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(str.getBytes());

        byte[] dataBytes = md.digest();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dataBytes.length; i++) {
            sb.append(Integer.toString((dataBytes[i] & 0xff) + 0x100, 10).substring(1));
        }

        return sb.toString();
    }
}
