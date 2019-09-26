public class Man {
    private long closedKey;
    public long openKey;

    public  Man(long P,long g){
        closedKey = random(P);
        Mod m = new Mod(g,closedKey,P);
        openKey = m.exp2().intValue();
    }
    public long Call(Man m,long P){
        Mod m1 = new Mod(m.openKey,closedKey,P);
        return m1.exp2().intValue();
    }
    private static long random(long max)
    {
        return (long) (Math.random() * ++max);
    }
}
