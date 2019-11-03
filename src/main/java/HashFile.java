import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class HashFile {
    public ArrayList<Integer> HashResult;
    public HashFile(String path) throws NoSuchAlgorithmException, IOException {
        //Create checksum for this file
        File file = new File(path);

//Use MD5 algorithm
        MessageDigest md5Digest = null;
        try {
            md5Digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

//Get the checksum
        HashResult = getFileChecksum(md5Digest, file);


//        System.out.println(HashResult);

//see checksum
//        HashResult=BigInteger.valueOf();
//        System.out.println(checksum);

    }
    public static ArrayList<Integer> getFileChecksum(MessageDigest digest, File file) throws IOException
    {
        //Get file input stream for reading the file content
        FileInputStream fis = new FileInputStream(file);

        //Create byte array to read data in chunks
        byte[] byteArray = new byte[(int)file.length()];
        int bytesCount = 0;

        //Read file data and update in message digest
        while ((bytesCount = fis.read(byteArray)) != -1) {
            digest.update(byteArray, 0, bytesCount);
        };

        //close the stream; We don't need it now.
        fis.close();

        //Get the hash's bytes
        ArrayList<Integer> ByteList = new ArrayList<>();

        byte[] bytes = digest.digest();
        for(int i=0; i< bytes.length ;i++)
        {
            ByteList.add(bytes[i]  & 0xFF);
//            System.out.println(ByteList.get(i));
        }
        //This bytes[] has bytes in decimal format;
        //Convert it to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 10).substring(1));
        }

        //return complete hash
        return ByteList;
    }
}
