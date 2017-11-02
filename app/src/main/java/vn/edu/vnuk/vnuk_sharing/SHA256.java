package vn.edu.vnuk.vnuk_sharing;

import java.security.MessageDigest;

/**
 * Created by ngocb on 11/1/2017.
 */

public class SHA256 {

    public static String getSHA256Hash(String data) {

        String result = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(data.getBytes());

            byte byteData[] = messageDigest.digest();

            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < byteData.length; i++){
                stringBuffer.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }

            result = stringBuffer.toString();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

}
