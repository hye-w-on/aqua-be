package com.aqua.aquabe.util;

import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import lombok.experimental.UtilityClass;
import org.springframework.util.ObjectUtils;

@UtilityClass
public class SecureUtil {

    private static final String AES256_SALT = "mzp_aes256_salt";
    private static final String AES256_ALGORITHM = "PBKDF2WithHmacSHA256";
    private static final byte[] iv = { 3, 8, 4, 2, 8, 3, 9, 1, 0, 0, 5, 2, 6, 1, 7, 7 }; // 16개

    public static String encryptSha(String email, String password) {
        String salt = email + email.length();
        try {
            KeySpec spec = new PBEKeySpec(
                    password.toCharArray(),
                    salt.getBytes(),
                    10005,
                    512);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            byte[] hash = factory.generateSecret(spec).getEncoded();

            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException("임시");
            // '"Can not generated sha-512 string"
        }
    }

    public static String encryptAes256(String key, String strToEncrypt) {
        if (ObjectUtils.isEmpty(strToEncrypt)) {
            return strToEncrypt;
        }

        try {

            SecretKeyFactory factory = SecretKeyFactory.getInstance(AES256_ALGORITHM);
            KeySpec spec = new PBEKeySpec(key.toCharArray(), AES256_SALT.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);

            IvParameterSpec ivspec = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");

            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);

            byte[] encrypted = cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encrypted);

        } catch (Exception e) {
            throw new RuntimeException("임시");
            // "Can't encrypt aes-256 string"
        }
    }

    public static String decryptAes256(String key, String strToDecrypt) {
        if (ObjectUtils.isEmpty(strToDecrypt)) {
            return strToDecrypt;
        }

        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance(AES256_ALGORITHM);
            KeySpec spec = new PBEKeySpec(key.toCharArray(), AES256_SALT.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);

            IvParameterSpec ivspec = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");

            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);

            byte[] byteStr = Base64.getDecoder().decode(strToDecrypt);
            return new String(cipher.doFinal(byteStr));

        } catch (Exception e) {
            throw new RuntimeException("임시");
            // "Can't decrypt aes-256 string"
        }
    }
}
