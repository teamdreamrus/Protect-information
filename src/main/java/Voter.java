import java.security.MessageDigest;

public class Voter {
    public Long answer;
    public Long N;
    public Long d;
    public Long r;
    public Long h;
    public Voter(long N, long d) throws Exception {

        answer = random((long) 2);
        Long rnd = random(10000);
        answer = Long.valueOf(rnd.toString() + answer.toString());
        this.N = N;
        this.d = d;
        while(true){
            r = random(1000000);
            if(gcd(N,r) == 1) break;
        }
        System.out.println(answer);
        System.out.println(getHash(answer.toString()));
        // h = (getHash(answer.toString());

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
    public String  getHash(String str)throws Exception     {



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
