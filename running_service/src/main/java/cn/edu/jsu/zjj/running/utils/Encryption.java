package cn.edu.jsu.zjj.running.utils;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密工具包
 */
public class Encryption {

    //字符串转sha256
    public static String getSah256(String str){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] digest = messageDigest.digest(str.getBytes());
            return byte2Hex(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    private static String byte2Hex(byte[] bytes){
        StringBuilder stringBuffer = new StringBuilder();
        String temp;
        for (byte aByte : bytes) {
            temp = Integer.toHexString(aByte & 0xFF);
            if (temp.length() == 1) {
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }

    //生成token
    public static String getToken(String acc, int hashCode){
        StringBuilder token=new StringBuilder();
        String tokenHead="Bearer ";
        String tokenBody = Encryption.getSah256(acc) + "." +
                Encryption.getSah256(Integer.toHexString(hashCode)) +
                Encryption.getSah256(token.toString()) + "." +
                Encryption.getSah256(token.toString());
        token.append(tokenHead).append(tokenBody);
        return token.toString();
    }

}
