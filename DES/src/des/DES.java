/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package des;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Quan Dau
 */
public class DES {
    
    public static byte[] maHoaDES(String SECRET_KEY, String original) throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
        
        SecretKeySpec skeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "DES");
        
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING");
        
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        
        byte[] byteEncrypted = cipher.doFinal(original.getBytes());

        String encrypted =  Base64.getEncoder().encodeToString(byteEncrypted);
        
        
        return byteEncrypted;
    }
    
    
    public static String giaiMaDES(String SECRET_KEY, byte[] byteEncrypted) throws NoSuchAlgorithmException, InvalidKeyException,
            NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
        
        SecretKeySpec skeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "DES");   

        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING"); 
        
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        
        byte[] byteDecrypted = cipher.doFinal(byteEncrypted);
        
        String decrypted = new String(byteDecrypted);
        
        return decrypted.toUpperCase();
    }
    
}
