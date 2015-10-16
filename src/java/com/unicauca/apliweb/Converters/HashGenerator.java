/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.apliweb.Converters;

import java.security.MessageDigest;

/**
 *
 * @author Miguel
 */
public class HashGenerator {

    public HashGenerator() {
    }
    
    
    private String convertByteArrayToHexString(byte[] arrayBytes) {
       StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < arrayBytes.length; i++) {
            stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return stringBuffer.toString();
    }
    
    private String hashString(String message, String algorithm)
    {

        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] hashedBytes = digest.digest(message.getBytes("UTF-8"));

            return convertByteArrayToHexString(hashedBytes);
        } 
        catch(Exception ex)
        {
            return null;
        }


    }
    
    public String generateMD5(String message){
        return hashString(message, "MD5");
    }
 
    public String generateSHA1(String message){
        return hashString(message, "SHA-1");
    }

    public String generateSHA256(String message){
        return hashString(message, "SHA-256");
    }
    
}
