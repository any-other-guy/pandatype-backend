package com.pandatype.auth.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RsaUtils {
    final static Logger LOGGER = LoggerFactory.getLogger(RsaUtils.class);

    /**
     * # Private key
     * openssl genpkey -algorithm RSA -out private.pem -pkeyopt rsa_keygen_bits:2048
     * <p>
     * # Public key
     * openssl rsa -pubout -in private.pem -out public_key.pem
     * <p>
     * # Private key in pkcs8 format (for Java maybe :D)
     * openssl pkcs8 -topk8 -in private.pem -out private_key.pem
     * <p>
     * ## nocrypt (Private key does have no password)
     * openssl pkcs8 -topk8 -in private.pem -nocrypt -out private_key.pem
     *
     * @return PrivateKey
     */
    public static PrivateKey getPrivateKey() {
        try {
            String privateKeyStr = getKey("private_key.pem");

            privateKeyStr = privateKeyStr.replace("-----BEGIN PRIVATE KEY-----\n", "");
            privateKeyStr = privateKeyStr.replace("-----END PRIVATE KEY-----", "");
            privateKeyStr = privateKeyStr.replace("\n", "");
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyStr));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @return PublicKey
     */
    public static PublicKey getPublicKey() {
        PublicKey publicKey = null;
        try {
            String publicKeyStr = getKey("public_key.pem");
            publicKeyStr = publicKeyStr.replace("-----BEGIN PUBLIC KEY-----\n", "");
            publicKeyStr = publicKeyStr.replace("-----END PUBLIC KEY-----", "");
            publicKeyStr = publicKeyStr.replace("\n", "");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyStr));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | IOException e) {
            e.printStackTrace();
        }
        return publicKey;
    }

    private static String getKey(String filename) throws IOException {
        String data = null;
        try {
            InputStream inputStream = new ClassPathResource(filename).getInputStream();
            byte[] bytearray = FileCopyUtils.copyToByteArray(inputStream);
            data = new String(bytearray, StandardCharsets.UTF_8);

        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }
        return data;
    }
}