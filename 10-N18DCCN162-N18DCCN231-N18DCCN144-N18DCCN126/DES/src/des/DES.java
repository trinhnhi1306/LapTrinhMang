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
        //Tạo key (tạo khóa mã hóa/giải mã)
        SecretKeySpec skeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "DES");
        /*Cipher (đối tượng này dùng để mã hóa, giải mã) và chỉ rõ các thông tin
        *   Tên thuật toán
            Mode (tùy chọn)
            Padding scheme (tùy chọn)
        */
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING");
        // chế độ mã hóa và key mã hóa
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        //Khi thực hiện mã hóa hay giải mã nó sẽ thực hiện trên byte[] - ở đây mã hóa text nên chuyển text sang byte
        byte[] byteEncrypted = cipher.doFinal(original.getBytes());

        //Ở đây mình chuyển byte sang dạng base64 để hiển thị.
        String encrypted =  Base64.getEncoder().encodeToString(byteEncrypted);
        
        
        return byteEncrypted;
    }
    
    
    public static String giaiMaDES(String SECRET_KEY, byte[] byteEncrypted) throws NoSuchAlgorithmException, InvalidKeyException,
            NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
        
        SecretKeySpec skeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "DES");   

        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING"); 
        // chế độ giải mã và key giải mã
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] byteDecrypted = cipher.doFinal(byteEncrypted);
        
        String decrypted = new String(byteDecrypted);
        
        return decrypted.toUpperCase();
    }
    
}
