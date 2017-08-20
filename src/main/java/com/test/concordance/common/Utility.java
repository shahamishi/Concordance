package com.test.concordance.common;

/**
 * Created by ashah on 7/19/17.
 */
public class Utility {
    /**
     *
     * @param filePath from which the file name has to be extracted out
     * @return name of the file
     */
    public static String getFileName(String filePath){
        String fileName="";
        if(filePath.contains("/")){
            if(filePath.contains(".")){
                fileName = filePath.substring(filePath.lastIndexOf("/")+1, filePath.lastIndexOf("."));
            }else {
                fileName = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length());
            }
        }
        return fileName;
    }
}
