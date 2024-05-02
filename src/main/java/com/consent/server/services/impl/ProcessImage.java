package com.consent.server.services.impl;

import com.consent.server.Config.AppLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Base64;

public class ProcessImage{

    private static final Logger logger = LoggerFactory.getLogger(AppLogger.class);

    public static String encodeImageToDatabase(byte[] imageFromRequest) throws IOException {
        logger.info("Starting the process of encoding image to database");
        if(!performImageChecks(imageFromRequest)){
            String e = "File size too large!";
            logger.error(e);
            throw new IOException(e);
        }
        String encodedString = Base64.getEncoder().encodeToString(imageFromRequest);
        logger.info(encodedString);

        return encodedString;
    }

    public static byte[] decodeImageFromDatabase(String encodedImage){
        return Base64.getDecoder().decode(encodedImage);
    }

    /**
     *
     * @param imageFromRequest
     * @return true if all checks have been passed
     */
    private static boolean performImageChecks(byte[] imageFromRequest){
        if(imageFromRequest.length>1000000){
            return false;
        }
        if(imageFromRequest.length<1024){
            return false;
        }
        return true;
    }

}
