package com.einstein.experiment.utils;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class AES {

    public static final String CHARSET = "UTF-8";
    public static final String KEY_ALGIROTHM = "AES";
    public static final String CIPHER_ALGIROTHM = "AES/CBC/PKCS5Padding";
    private static final Logger LOG = LoggerFactory.getLogger(AES.class);

    /**
     * 加密(UTF8)
     *
     * @param key     密码
     * @param content 待加密内容
     * @return base64处理后的密文
     */
    public static String encrypt(String key, String content) {
        try {
            Key keySpec = new SecretKeySpec(key.getBytes(CHARSET), KEY_ALGIROTHM);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGIROTHM);
            byte[] iv = new byte[cipher.getBlockSize()];
            IvParameterSpec ivParams = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParams);
            byte[] encryptByte = cipher.doFinal(content.getBytes(CHARSET));
            return Base64.encodeBase64String(encryptByte);
        } catch (Exception e) {
            LOG.error("加密失败.", e);
        }
        return null;
    }

    /**
     * 解密(UTF8)
     *
     * @param key       密码
     * @param encrypted 密文
     * @return 原文
     */
    public static String decrypt(String key, String encrypted) {
        byte[] encryptBytes = Base64.decodeBase64(encrypted);
        try {
            Key keySpec = new SecretKeySpec(key.getBytes(CHARSET), KEY_ALGIROTHM);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGIROTHM);
            byte[] ivByte = new byte[cipher.getBlockSize()];
            IvParameterSpec ivParamsSpec = new IvParameterSpec(ivByte);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamsSpec);
            byte[] content = cipher.doFinal(encryptBytes);
            return new String(content, CHARSET);
        } catch (Exception e) {
            LOG.error("解密失败.", e);
        }
        return null;
    }

    public static void main(String[] args) {
        String key = "12345678901dbc7a";
        String content = "12djajakdkdjdjej";
        String encrypted = encrypt(key, content);

        System.out.println(content);
        System.out.println(content.length());
        System.out.println(encrypted);
        System.out.println(encrypted.length());
        System.out.println(decrypt(key, encrypted));

    }
}
