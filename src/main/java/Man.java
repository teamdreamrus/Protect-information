public class Man {
    public long closedKey;
    public long openKey;
    public long sessionKey;

    public  Man(long P,long g){
        closedKey = random(P);
        Mod m = new Mod(g,closedKey,P);
        openKey = m.exp2().intValue();
        sessionKey = random(P-1);
    }
    public long Call(Man m,long P){
        Mod m1 = new Mod(m.openKey,closedKey,P);
        return m1.exp2().intValue();
    }
    private static long random(long max)
    {
        return (long) (Math.random() * ++max);
    }


    public long getSessionKey() {
        return sessionKey;
    }
    public long getClosedKey() {
        return closedKey;
    }
}
