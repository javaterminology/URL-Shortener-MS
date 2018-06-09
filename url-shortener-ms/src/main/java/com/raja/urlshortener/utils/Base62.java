package com.raja.urlshortener.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.raja.urlshortener.generator.Base62TimestampUrlIdentifierGenerator;

public class Base62 {

    private static final Logger LOG = LoggerFactory.getLogger(Base62.class);

    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";//62

    public static final int BASE = ALPHABET.length();

    private Base62() {}

    public static String fromBase10(long i) {
        StringBuilder sb = new StringBuilder("");
        while (i > 0) {
            LOG.info("fromBase10 i value before------>"+i);
            i = fromBase10(i, sb);
            LOG.info("fromBase10 i value after------>"+i);

        }
        return sb.reverse().toString();
    }

    private static long fromBase10(long i, final StringBuilder sb) {
        int rem = (int) (i % BASE);
        sb.append(ALPHABET.charAt(rem));
        return i / BASE;
    }

    public static long toBase10(String str) {
        return toBase10(new StringBuilder(str).reverse().toString().toCharArray());
    }

    private static long toBase10(char[] chars) {
        long n = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            n += toBase10(ALPHABET.indexOf(chars[i]), i);
        }
        return n;
    }

    private static long toBase10(long n, long pow) {
        return n * (long) Math.pow(BASE, pow);
    }

}
