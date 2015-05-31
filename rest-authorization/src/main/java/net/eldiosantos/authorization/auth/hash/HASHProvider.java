package net.eldiosantos.authorization.auth.hash;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Eldius on 16/05/2015.
 */
public class HASHProvider {

    public byte[] hash(final String phrase) throws Exception {
        final MessageDigest hasher = getMessageDigest();
        return hasher.digest(phrase.getBytes("UTF-8"));
    }

    public byte[] hash(final String phrase, final String salt) throws Exception {
        final MessageDigest hasher = getMessageDigest();
        hasher.reset();
        hasher.update(salt.getBytes());
        return hasher.digest(phrase.getBytes("UTF-8"));
    }

    public String stringHash(final String phrase) throws Exception {
        return Hex.encodeHexString(hash(phrase));
    }

    public String stringHash(final String phrase, final String salt) throws Exception {
        return Hex.encodeHexString(hash(phrase, salt));
    }

    private MessageDigest getMessageDigest() throws NoSuchAlgorithmException {
        return MessageDigest.getInstance("SHA-256");
    }


}
