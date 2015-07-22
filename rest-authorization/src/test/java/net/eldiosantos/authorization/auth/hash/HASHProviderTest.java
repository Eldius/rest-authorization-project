package net.eldiosantos.authorization.auth.hash;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by esjunior on 22/07/2015.
 */
public class HASHProviderTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testHash() throws Exception {
        final HASHProvider hashProvider = new HASHProvider();
        final String phrase = "my-test-phrase";
        final byte[] hash = hashProvider.hash(phrase);

        assertEquals("Same input generates same output size", hash.length, hashProvider.hash(phrase).length);

        for(int i = 0; i < hash.length; i++) {
            assertEquals("Same input generates same output", hash[i], hashProvider.hash(phrase)[i]);
        }
    }

    @Test(expected = NullPointerException.class)
    public void testHashNull() throws Exception {
        final HASHProvider hashProvider = new HASHProvider();
        final String phrase = "my-test-phrase";
        final byte[] hash = hashProvider.hash(null);
    }

    @Test
    public void testHash1() throws Exception {
        final HASHProvider hashProvider = new HASHProvider();
        final String phrase = "my-test-phrase";
        final String salt = "a simple salt";
        final byte[] hash = hashProvider.hash(phrase, salt);

        assertEquals("Same input generates same output size", hash.length, hashProvider.hash(phrase, salt).length);

        for(int i = 0; i < hash.length; i++) {
            assertEquals("Same input generates same output", hash[i], hashProvider.hash(phrase, salt)[i]);
        }
    }

    @Test(expected = NullPointerException.class)
    public void testHash1Null() throws Exception {
        final HASHProvider hashProvider = new HASHProvider();
        final String salt = "a simple salt";
        hashProvider.hash(null, salt);
    }


    @Test(expected = NullPointerException.class)
    public void testHash1Null1() throws Exception {
        final HASHProvider hashProvider = new HASHProvider();
        final String phrase = "my-test-phrase";
        hashProvider.hash(phrase, null);
    }

    @Test
    public void testStringHash() throws Exception {
        final HASHProvider hashProvider = new HASHProvider();
        final String phrase = "my-test-phrase";
        final String salt = "a simple salt";
        final String hash = hashProvider.stringHash(phrase, salt);

        assertTrue("Same input generates same output", hash.equals(hashProvider.stringHash(phrase, salt)));
    }

    @Test(expected = NullPointerException.class)
    public void testStringHashNull() throws Exception {
        final HASHProvider hashProvider = new HASHProvider();
        final String salt = "a simple salt";
        hashProvider.stringHash(null, salt);
    }

    @Test
    public void testStringHash1() throws Exception {
        final HASHProvider hashProvider = new HASHProvider();
        final String phrase = "my-test-phrase";
        final String hash = hashProvider.stringHash(phrase);

        assertTrue("Same input generates same output", hash.equals(hashProvider.stringHash(phrase)));
    }

    @Test(expected = NullPointerException.class)
    public void testStringHash1Null() throws Exception {
        final HASHProvider hashProvider = new HASHProvider();
        final String phrase = "my-test-phrase";
        hashProvider.stringHash(phrase, null);
    }

}
