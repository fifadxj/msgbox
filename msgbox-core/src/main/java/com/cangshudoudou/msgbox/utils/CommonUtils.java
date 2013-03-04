package com.cangshudoudou.msgbox.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.springframework.util.StringUtils;

import com.cangshudoudou.msgbox.SystemException;

public class CommonUtils {
    public static String sha1Base64(String clearTxt) {
        if (StringUtils.hasLength(clearTxt)) {
            try {
                MessageDigest msgDigest = MessageDigest.getInstance("SHA-1");
                byte[] hashedBytes = msgDigest.digest(clearTxt.getBytes("UTF-8"));
                return new String(Base64.encodeBase64(hashedBytes), "UTF-8");
            } catch (NoSuchAlgorithmException e) {
                throw new SystemException(e);//should never go here
            } catch (UnsupportedEncodingException e) {
                throw new SystemException(e);//should never go here
            }
        } 

        return clearTxt;
    }
    
    public static void main(String[] args) {
        System.out.println(CommonUtils.sha1Base64("terry"));
    }
}
