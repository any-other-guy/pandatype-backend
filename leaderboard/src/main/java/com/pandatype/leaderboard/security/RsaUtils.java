package com.pandatype.leaderboard.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
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