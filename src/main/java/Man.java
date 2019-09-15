public class Man {
    private int closedKey;
    public int openKey;

    public  Man(int P,int g){
        closedKey = random(P);
        Mod m = new Mod(g,closedKey,P);
        openKey = m.exp2().intValue();
    }
    public int Call(Man m,int P){
        Mod m1 = new Mod(m.openKey,closedKey,P);
        return m1.exp2().intValue();
    }
    private static int random(int max)
    {
        return (int) (Math.random() * ++max);
    }
}
