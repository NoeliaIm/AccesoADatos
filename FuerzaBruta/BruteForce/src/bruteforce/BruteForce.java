/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bruteforce;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author ALUMNOM
 */
public class BruteForce {
    
    private static final String FIRST_CANDIDATE = "a";
    private static final char START_OF_SEARCH_AREA = 'a';
    private static final char END_OF_SEARCH_AREA = 'z';
    
    public static String getMd5Digest(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            return number.toString(16);
        }
        catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
    public static String first() {
        return FIRST_CANDIDATE;
    }
    public static String next(String c) {
        String next = "";
        int N = c.length();
        char[] a = c.toCharArray();
        for (int j = N - 1; j >= 0; j--) {
            a[j] = nextChar(a[j]);
            if (!(a[j] == START_OF_SEARCH_AREA)) {
                next = new String(a);
                break;
            } else {
                if (j == 0) {
                    next = new String(a) + START_OF_SEARCH_AREA;
                }
            }
        }
        return next;
    }
 
    private static char nextChar(char ch) {
         if (ch == END_OF_SEARCH_AREA) {
            return START_OF_SEARCH_AREA;
         } else {
            return ++ch;
        }
    }
    
    
}
