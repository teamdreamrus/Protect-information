import java.math.BigInteger;
import java.util.*;

public class LittleBig {
    private long a;
    private   long y;
    private long P;
    private long m;
    private  long k;

    public LittleBig(long a1, long P1,long y1){
        a= a1;
        y=y1;
        P=P1;
        m= (long)Math.sqrt(P*1.0) +1;
        k= (long)Math.sqrt(P*1.0) +1;


        HashMap<Long, BigInteger> first = new HashMap<Long, BigInteger>();
        HashMap<Long, BigInteger> second = new HashMap<Long, BigInteger>();



        for(long j=0; j<m;j++){
            long z = (long)Math.pow(a,j);

            Mod m1 = new Mod(y*z,1,P);

            first.put(j, m1.exp2());

        }
        System.out.println("--------------------------");
        for(long i=1;i<=k;i++){

            Mod m1 = new Mod(a,i*m,P);
            second.put(i, m1.exp2());

        }

        System.out.println("--------------------------");
        Map<Long, BigInteger>  first1 = sortByValue(first);
        Map<Long, BigInteger>  second1 = sortByValue(second);


        long iEnd=0;
        long jEnd=0;
        boolean cheTam =false;
        for(Map.Entry<Long, BigInteger> entry1 : first1.entrySet()) {
            Long key1 = entry1.getKey();
            BigInteger value1 = entry1.getValue();
                for(Map.Entry<Long, BigInteger> entry2 : second1.entrySet()) {
                    Long key2 = entry2.getKey();
                    BigInteger value2 = entry2.getValue();
                    if (value1.equals(value2)) {

                        System.out.println(value2);
                        iEnd = key2;
                        jEnd = key1;

                        cheTam = true;
                        break;
                    }
                    if(value1.equals(value1.max(value2))) break;
                    if(cheTam) break;

                    System.out.println(value1);
                    System.out.println(value2);

                }
                if(cheTam) break;

            }
            if(!cheTam){
                System.out.println("ERROR!!! net otveta");
                return;
            }

        System.out.println("i "+iEnd+" j "+jEnd);
        System.out.print("x = ");
            long x = iEnd*m-jEnd;
        System.out.println(x);
        System.out.println("PROVERKA ");
        Mod check = new Mod(a,x,P);
        System.out.print("y = ");
        System.out.println(y);
        System.out.print("PODCHET ");
        System.out.println(check.exp2());


    }
    private static int random(int max)
    {
        return (int) (Math.random() * ++max);
    }

    public static HashMap<Long, BigInteger> sortByValue(HashMap<Long, BigInteger> hm)
    {

        List<Map.Entry<Long, BigInteger> > list =
                new LinkedList<Map.Entry<Long, BigInteger> >(hm.entrySet());


        Collections.sort(list, new Comparator<Map.Entry<Long, BigInteger> >() {
            public int compare(Map.Entry<Long, BigInteger> o1,
                               Map.Entry<Long, BigInteger> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        HashMap<Long, BigInteger> temp = new LinkedHashMap<Long, BigInteger>();
        for (Map.Entry<Long, BigInteger> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
}
