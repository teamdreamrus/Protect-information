import static java.lang.Math.pow;

public class DiffHellman {
    public int g;
    public int P;
    public int Q;
    public DiffHellman(){

        do{
           Q=random(100);
        }while(!isPrime(Q));
        P=2*Q+1;
        g = random(P-1);
        System.out.print("Q: "+ Q+" P: "+P+" g: "+g +" ");
        Man m1 = new Man(P,g);
        Man m2 = new Man(P,g);
      /*  m1.openKey = 4;
        m1.closedKey = 7;
        m2.openKey = 9;
        m2.closedKey = 6 ;*/
        System.out.println("m1 to m2");
        System.out.println(m1.Call(m2,P));
        System.out.println("m2 to m1");
        System.out.println(m2.Call(m1,P));



    }

    private boolean isPrime(int P){
        if(P<=1) return false;
        int b =(int)pow(P,0.5);
        for(int i=2;i<=b;i++){
            if((P%i)==0) return false;
        }
        return true;
       }
    private static int random(int max)
    {
        return (int) (Math.random() * ++max);
    }
 }


