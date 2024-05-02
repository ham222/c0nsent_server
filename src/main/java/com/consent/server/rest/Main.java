package com.consent.server.rest;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;

public class Main{
    public static void main(String[] args) throws IOException {
        byte[] fileContent = FileUtils.readFileToByteArray(new File("C:\\Users\\Jakub Nowak\\Desktop\\My files\\druzynaoom2022.jpg"));

        System.out.println(Arrays.toString(fileContent));
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        System.out.println(encodedString);

        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        FileUtils.writeByteArrayToFile(new File("decodedImage.jpg"), decodedBytes);
    }
}
