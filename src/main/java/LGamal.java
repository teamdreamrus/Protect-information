public class LGamal {
    private long A;
    private long B;
    public LGamal(){
        DiffHellman Diff =new DiffHellman();
//        Diff.P=23;
//        Diff.g=5;


        Man Alisa = new Man(Diff.P,Diff.g);
        Man Bob = new Man(Diff.P,Diff.g);
//        Bob.closedKey = 13;
//        Bob.openKey=21;
//        Alisa.sessionKey=7;
        Mod m1 = new Mod(Diff.g,Alisa.getSessionKey(),Diff.P);
        A=m1.exp2().longValue();
        int message = 1555;
        Mod m2= new Mod(Bob.openKey,Alisa.getSessionKey(),Diff.P);
        B=message*m2.exp2().longValue() % Diff.P;

        Mod m3= new Mod(A,Diff.P-1-Bob.getClosedKey(),Diff.P);
        long messageNew =B*m3.exp2().longValue() % Diff.P;
        System.out.println(messageNew);

    }
}
