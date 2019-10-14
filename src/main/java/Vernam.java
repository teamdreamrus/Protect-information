import sun.awt.windows.WPrinterJob;

import java.util.Random;

public class Vernam {
    public Vernam() {
        String message = "Hello world!";
        char[] achText = message.toCharArray();
        char[] achKey = new char[achText.length];
        char[] achResult = new char[achText.length];
        Random random = new Random();
        for (int i = 0; i < achText.length; i++) {
            achKey[i] = (char) random.nextInt(Character.MAX_VALUE);
            achResult[i] = (char) (achText[i] ^ achKey[i]);
        }
        System.out.print("Text: ");
        for (char a : achText) {
            System.out.print((int) a+" ");
        }
        System.out.println();
        System.out.print("Key: ");
        for (char a : achKey) {
            System.out.print((int) a+" ");
        }
        System.out.println();
        System.out.print("Result: ");
        for (char a : achResult) {
            System.out.print((int) a+" ");
        }
        System.out.println();


      //  System.out.println("Result: " + String.valueOf(achResult));

        char[] achDecrypt = new char[achText.length];
        for (int i = 0; i < achText.length; i++) {
            achDecrypt[i] = (char) (achResult[i] ^ achKey[i]);
        }

        System.out.println("Decrypt: " + String.valueOf(achDecrypt));
    }
}
