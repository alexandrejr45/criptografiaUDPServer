package principal;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Criptografia {

    private Cipher cipher;
    private SecretKeySpec key;
    private byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    

    public byte[] encrypt(byte[] valor) throws IllegalBlockSizeException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, NoSuchProviderException, InvalidAlgorithmParameterException, BadPaddingException, NoSuchPaddingException {

        this.cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
        this.key = new SecretKeySpec("0123456789abcdef".getBytes("UTF-8"), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
        byte[] encrypted = cipher.doFinal(valor);
        return encrypted;

    }
    
    public byte[] decrypt(byte[] valor) throws IllegalBlockSizeException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, NoSuchProviderException, InvalidAlgorithmParameterException, BadPaddingException, NoSuchPaddingException {

        this.cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
        this.key = new SecretKeySpec("0123456789abcdef".getBytes("UTF-8"), "AES");
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
        byte[] decrypted = cipher.doFinal(valor);
        return decrypted;

    }
}
