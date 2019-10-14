import java.math.BigInteger;
import java.util.ArrayList;

public class Mod {
    private long a;
    private   long x;
    private long P;

    public Mod(long a1, long x1, long P1){
        a= a1;
        x=x1;
        P=P1;
    }

    public BigInteger exp2(){
        long aNew = x;

        String bin = Long.toBinaryString(x);

        String[] binStrArr = Long.toBinaryString(x).split("");
        ArrayList<Byte> arrInt = new ArrayList<>();

        for(int i= binStrArr.length-1; i>=0;i--){
            arrInt.add(Byte.valueOf(binStrArr[i]));

        }
        long count=arrInt.size();
//        long count=0;
//        while(aNew>0){
//            count++;
//            aNew = aNew >> 1;
//
//        }
        ArrayList<Long> arrLong = new ArrayList<>();
        arrLong.add(modul(a));
        for(int i=1; i<count;i++){

            arrLong.add(modul(arrLong.get(i-1)*arrLong.get(i-1)));
            if(arrLong.get(i)<0) {
//                System.out.print("TUT");System.out.println(arrLong.get(i));
            }

        }

        BigInteger end = BigInteger.valueOf(1);
        for(int i=0; i<arrLong.size();i++){
            if(arrInt.get(i)!=0){
                end=end.multiply(BigInteger.valueOf((arrLong.get(i))));

            }
        }

        return end.mod(BigInteger.valueOf(P));

    }
    public long modul(long del){
        long count = del % P;

    return count;
    }

}
